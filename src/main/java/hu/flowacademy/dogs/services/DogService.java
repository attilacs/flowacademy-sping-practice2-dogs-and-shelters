package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
}
