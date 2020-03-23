package com.mykyta.testspring.adapters;

import com.mykyta.testspring.model.Advert;
import reactor.core.publisher.Mono;

public interface AdvertAdapter {
    Mono<Advert> getAdvert(int id);
}
