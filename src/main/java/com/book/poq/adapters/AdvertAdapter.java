package com.book.poq.adapters;

import com.book.poq.model.Advert;
import reactor.core.publisher.Mono;

public interface AdvertAdapter {
    Mono<Advert> getAdvert(int id);
}
