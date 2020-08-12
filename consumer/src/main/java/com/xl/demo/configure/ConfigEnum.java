package com.xl.demo.configure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConfigEnum {

    TTL("order-ttl-exchange","order-ttl-quene","ttl-key"),

    ORDER("order-exchange","order-quene","order-key");

    private String exchange;

    private String name;

    private String key;
}
