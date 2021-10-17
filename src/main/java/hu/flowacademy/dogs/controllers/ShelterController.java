package hu.flowacademy.dogs.controllers;

import hu.flowacademy.dogs.dtos.ShelterDTO;
import hu.flowacademy.dogs.dtos.ShelterResponse;
import hu.flowacademy.dogs.services.ShelterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/shelter")
@AllArgsConstructor
public class ShelterController {
    private final ShelterService shelterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShelterResponse addShelter(@RequestBody @Valid ShelterDTO shelterDTO) {
        return shelterService.addShelter(shelterDTO);
    }

    @GetMapping
    public List<ShelterResponse> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @GetMapping("{id}")
    public ShelterResponse getShelter(@PathVariable Long id) {
        return shelterService.getShelterById(id);
    }
}
