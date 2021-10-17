package hu.flowacademy.dogs.dtos;

import lombok.Data;

@Data
public class DogResponse {
    private Long id;
    private String chipId;
    private String name;
    private String gender;
    private Long shelter_id;
}
