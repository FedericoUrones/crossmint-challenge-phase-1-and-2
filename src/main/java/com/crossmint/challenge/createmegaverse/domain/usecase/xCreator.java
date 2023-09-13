package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.apiClient.ApiClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class xCreator {
    @Autowired
    private ApiClient apiClient;

    @Value("${size}")
    private Integer size; // it must be odd and higher than ??

    @Value("${margin}")
    private Integer margin; // must be higher or equal to 0

    public void createXonSpace() {
        Integer finalSize = size - margin * 2;
        for(int i = 0; i < finalSize; i++) {
            // TODO
        }
    }
}
