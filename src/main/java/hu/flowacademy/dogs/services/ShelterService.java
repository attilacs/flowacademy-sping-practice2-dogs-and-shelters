package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.repositories.ShelterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShelterService {
    private final ShelterRepository shelterRepository;

}
