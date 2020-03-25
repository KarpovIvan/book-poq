package com.book.poq.configurations.advert;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("poq.advert")
public class PoqAdvertProperties {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
