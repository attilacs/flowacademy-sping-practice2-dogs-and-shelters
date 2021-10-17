package hu.flowacademy.dogs.controllers;

import hu.flowacademy.dogs.dtos.ShelterDTO;
import hu.flowacademy.dogs.entities.Shelter;
import hu.flowacademy.dogs.services.ShelterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shelter")
@AllArgsConstructor
public class ShelterController {
    private final ShelterService shelterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shelter addShelter(@RequestBody ShelterDTO shelterDTO) {
        return shelterService.addShelter(shelterDTO);
    }
}
