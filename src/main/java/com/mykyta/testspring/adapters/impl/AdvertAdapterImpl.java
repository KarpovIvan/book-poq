package com.mykyta.testspring.adapters.impl;

import com.mykyta.testspring.adapters.AdvertAdapter;
import com.mykyta.testspring.model.Advert;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AdvertAdapterImpl implements AdvertAdapter {

    private WebClient webClient;

    public AdvertAdapterImpl(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8099/advert").build();
    }

    public Mono<Advert> getAdvert(int id){
        return webClient
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(Advert.class);
    }

    public Flux<Advert> findAllAdverts(){
        return webClient
                .get()
                .uri("")
                .retrieve()
                .bodyToFlux(Advert.class);
    }
}
