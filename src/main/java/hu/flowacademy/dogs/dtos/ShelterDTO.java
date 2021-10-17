package hu.flowacademy.dogs.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ShelterDTO {
    @NotNull
    private String address;

    @NotNull
    private Integer capacity;
}
