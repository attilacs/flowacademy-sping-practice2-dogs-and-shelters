package hu.flowacademy.dogs.controllers;

import hu.flowacademy.dogs.services.ShelterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shelter")
@AllArgsConstructor
public class ShelterController {
    private final ShelterService shelterService;
}
