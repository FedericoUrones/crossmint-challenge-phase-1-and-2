package com.crossmint.challenge.createmegaverse.infrastructure;

import com.crossmint.challenge.createmegaverse.domain.entities.*;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.*;
import com.crossmint.challenge.createmegaverse.infrastructure.entities.SpaceMapGoalResponse;
import com.crossmint.challenge.createmegaverse.infrastructure.mapper.SpaceMapInfraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class ApiClient implements CreatePolyanetPort, DeletePolyanetsPort, GetGoalMapPort,
        CreateComethPort, CreateSoloonPort, DeleteComethPort, DeleteSoloonPort {

    Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final SpaceMapInfraMapper spaceMapInfraMapper;

    private final String candidateId;

    private final WebClient webClient;

    @Autowired
    public ApiClient(@Value("${service.base.url}") String baseUrl, @Autowired SpaceMapInfraMapper spaceMapInfraMapper,
                     @Value("${service.candidate.id}") String candidateId) {
        this.spaceMapInfraMapper = spaceMapInfraMapper;
        this.candidateId = candidateId;
        this.webClient  = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public SpaceMap getGoalMap() {
        SpaceMapGoalResponse spaceMapGoalResponse = webClient.get().uri("/map/" + candidateId + "/goal").retrieve()
                .bodyToMono(SpaceMapGoalResponse.class).block();
        return spaceMapInfraMapper.spaceMapGoalResponseToSpaceMap(spaceMapGoalResponse);
    }

    private String getPath(SpaceElement spaceElement) {
        if (spaceElement instanceof Polyanet) {
            return "/polyanets";
        } else if (spaceElement instanceof Cometh){
            return "/comeths";
        } else {
            return "/soloons";
        }
    }

    public void create(SpaceElement spaceElement) {
        String currentPath = getPath(spaceElement);

        webClient.post().uri(currentPath)
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(spaceElement, true))
                .retrieve()
                .bodyToMono(Object.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1))
                        .filter(this::is429TooManyRequestsError)).block();
    }

    public void delete(SpaceElement spaceElement) {
        String currentPath = getPath(spaceElement);

        webClient.method(HttpMethod.DELETE).uri(currentPath)
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(spaceElement, false))
                .retrieve()
                .bodyToMono(Object.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1))
                        .filter(this::is429TooManyRequestsError)).block();
    }

    @Override
    public void createPolyanet(Polyanet polyanet) {
        create(polyanet);
    }

    @Override
    public void deletePolyanet(Polyanet polyanet) {
        delete(polyanet);
    }

    private BodyInserters.FormInserter<String> getJsonBody(SpaceElement spaceElement, Boolean isCreation) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("row", spaceElement.getRow().toString());
        bodyValues.add("column", spaceElement.getColumn().toString());
        bodyValues.add("candidateId", candidateId);

        if (isCreation) {
            if (spaceElement instanceof Cometh) {
                bodyValues.add("direction", ((Cometh) spaceElement).getDirection().toString());
            } else if (spaceElement instanceof Soloon) {
                bodyValues.add("color", ((Soloon) spaceElement).getColor().toString());
            }
        }

        return BodyInserters.fromFormData(bodyValues);
    }

    private boolean is429TooManyRequestsError(Throwable throwable) {
        logger.error("Got 429 error, should be retried later");
        return throwable instanceof WebClientResponseException &&
                ((WebClientResponseException) throwable).getStatusCode().is4xxClientError();
    }

    @Override
    public void createCometh(Cometh cometh) {
        create(cometh);
    }

    @Override
    public void createSoloon(Soloon soloon) {
        create(soloon);
    }

    @Override
    public void deleteCometh(Cometh cometh) {
        delete(cometh);
    }

    @Override
    public void deleteSoloon(Soloon soloon) {
        delete(soloon);
    }
}
