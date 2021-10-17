package hu.flowacademy.dogs.controllers;

import hu.flowacademy.dogs.services.DogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dog")
@AllArgsConstructor
public class DogController {
    private final DogService dogService;
}
