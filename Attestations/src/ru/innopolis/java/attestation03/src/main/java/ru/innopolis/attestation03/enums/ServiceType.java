package ru.innopolis.attestation03.enums;

import lombok.Getter;

@Getter
public enum ServiceType {
    ONLINE("Онлайн консультация"),
    LIVE("Очное посещение");

    private final String serviceType;

    ServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return serviceType;
    }
}
