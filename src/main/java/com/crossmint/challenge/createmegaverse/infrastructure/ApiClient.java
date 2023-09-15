package com.crossmint.challenge.createmegaverse.infrastructure;

import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;
import com.crossmint.challenge.createmegaverse.domain.entities.SpaceMap;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreatePolyanetPort;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.DeletePolyanetsPort;
import com.crossmint.challenge.createmegaverse.infrastructure.entities.SpaceMapGoalResponse;
import com.crossmint.challenge.createmegaverse.mapper.SpaceMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiClient implements CreatePolyanetPort, DeletePolyanetsPort {

    private final SpaceMapMapper spaceMapMapper;

    private final String baseUrl;

    private final String candidateId;

    private final WebClient webClient;

    @Autowired
    public ApiClient(@Value("${service.base.url}") String baseUrl, @Autowired SpaceMapMapper spaceMapMapper,
                     @Value("${service.candidate.id}") String candidateId) {
        this.baseUrl = baseUrl;
        this.spaceMapMapper = spaceMapMapper;
        this.candidateId = candidateId;
        this.webClient  = WebClient.builder().baseUrl(this.baseUrl).build();
    }

    public SpaceMap getMap() {
        SpaceMapGoalResponse spaceMapGoalResponse = webClient.get().uri("/" + candidateId + "/goal").retrieve()
                .bodyToMono(SpaceMapGoalResponse.class).block();
        return spaceMapMapper.spaceMapGoalResponseToSpaceMap(spaceMapGoalResponse);
    }

    @Override
    public void createPolyanet(Polyanet polyanet) {

        webClient.post().uri("/polyanets")
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(polyanet))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    @Override
    public void deletePolyanet(Polyanet polyanet) {

        webClient.method(HttpMethod.DELETE).uri("/polyanets")
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(polyanet))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    private BodyInserters.FormInserter<String> getJsonBody(Polyanet polyanet) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("row", polyanet.getRow().toString());
        bodyValues.add("column", polyanet.getColumn().toString());
        bodyValues.add("candidateId", candidateId);

        return BodyInserters.fromFormData(bodyValues);
    }

}
