package hu.flowacademy.dogs.dtos;

import lombok.Data;

@Data
public class ShelterResponse {
    private Long id;
    private String address;
    private Integer capacity;
}
