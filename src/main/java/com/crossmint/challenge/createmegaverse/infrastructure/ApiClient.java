package com.crossmint.challenge.createmegaverse.infrastructure;

import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;
import com.crossmint.challenge.createmegaverse.domain.entities.SpaceMap;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreatePolyanetPort;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.DeletePolyanetsPort;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.GetGoalMapPort;
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
public class ApiClient implements CreatePolyanetPort, DeletePolyanetsPort, GetGoalMapPort {

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

    @Override
    public void createPolyanet(Polyanet polyanet) {

        webClient.post().uri("/polyanets")
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(polyanet))
                .retrieve()
                .bodyToMono(Object.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1))
                        .filter(this::is429TooManyRequestsError)).block();
    }

    @Override
    public void deletePolyanet(Polyanet polyanet) {

        webClient.method(HttpMethod.DELETE).uri("/polyanets")
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(polyanet))
                .retrieve()
                .bodyToMono(Object.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1))
                        .filter(this::is429TooManyRequestsError)).block();
    }

    private BodyInserters.FormInserter<String> getJsonBody(Polyanet polyanet) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("row", polyanet.getRow().toString());
        bodyValues.add("column", polyanet.getColumn().toString());
        bodyValues.add("candidateId", candidateId);

        return BodyInserters.fromFormData(bodyValues);
    }

    private boolean is429TooManyRequestsError(Throwable throwable) {
        logger.error("Got 429 error, should be retried later");
        return throwable instanceof WebClientResponseException &&
                ((WebClientResponseException) throwable).getStatusCode().is4xxClientError();
    }

}
