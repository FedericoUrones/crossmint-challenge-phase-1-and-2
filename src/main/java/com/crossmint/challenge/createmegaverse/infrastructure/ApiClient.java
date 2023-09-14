package com.crossmint.challenge.createmegaverse.infrastructure;

import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;
import com.crossmint.challenge.createmegaverse.domain.entities.SpaceMap;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreateXPort;
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
public class ApiClient implements CreateXPort {


    @Autowired
    private SpaceMapMapper spaceMapMapper;

    @Value("${service.base.url}")
    private String baseUrl;

    @Value("${service.candidate.id}")
    private String candidateId;

    private final WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

    public SpaceMap getMap() {
        SpaceMapGoalResponse spaceMapGoalResponse = webClient.get().uri("/" + candidateId + "/goal").retrieve()
                .bodyToMono(SpaceMapGoalResponse.class).block();
        return spaceMapMapper.spaceMapGoalResponseToSpaceMap(spaceMapGoalResponse);
    }

    public void createPolyanet(Polyanet polyanet) {

        webClient.post().uri("/polyanets")
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(polyanet))
                .retrieve()
                .bodyToMono(SpaceMapGoalResponse.class).block();
    }

    public void deletePolyanet(Polyanet polyanet) {

        webClient.method(HttpMethod.DELETE).uri("/polyanets")
                .contentType(MediaType.APPLICATION_JSON)
                .body(getJsonBody(polyanet))
                .retrieve()
                .bodyToMono(SpaceMapGoalResponse.class).block();
    }

    private BodyInserters.FormInserter<String> getJsonBody(Polyanet polyanet) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("row", polyanet.getRow().toString());
        bodyValues.add("column", polyanet.getColumn().toString());
        bodyValues.add("candidateId", candidateId);

        return BodyInserters.fromFormData(bodyValues);
    }

    @Override
    public void createX(Polyanet polyanet) {

    }
}
