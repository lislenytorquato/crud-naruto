package com.crud.naruto.gateway;

import com.crud.naruto.dto.AldeiaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AldeiaClient {

    private final RestTemplate restTemplate;

    @Value("${aldeia.api.url}")
    private String apiAldeiauUri;

    public AldeiaClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public AldeiaDto buscarAldeiaPorNomeDoPersonagem(String nomePersonagem){
        String url = apiAldeiauUri + "/aldeia/" + nomePersonagem;
        return restTemplate.getForObject(url, AldeiaDto.class);
    }
}
