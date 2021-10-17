package hu.flowacademy.dogs.controllers;

import hu.flowacademy.dogs.dtos.DogDTO;
import hu.flowacademy.dogs.dtos.DogResponse;
import hu.flowacademy.dogs.services.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/dog")
@AllArgsConstructor
public class DogController {
    private final DogService dogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DogResponse addDog(@RequestBody @Valid DogDTO dogDTO) {
        return dogService.addDog(dogDTO);
    }

    @GetMapping
    public List<DogResponse> getAllDogs() {
        return dogService.getAllDogsToReturn();
    }
}
