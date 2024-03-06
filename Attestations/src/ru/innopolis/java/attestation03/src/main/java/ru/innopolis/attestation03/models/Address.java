package ru.innopolis.attestation03.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String residentialAddress;
    private String permanentAddress = "";

    @Override
    public String toString() {
        return "Address [residentialAddress=" + residentialAddress + ", permanentAddress=" + permanentAddress + "]";
    }
}
