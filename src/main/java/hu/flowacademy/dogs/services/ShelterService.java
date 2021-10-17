package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.dtos.ShelterDTO;
import hu.flowacademy.dogs.entities.Shelter;
import hu.flowacademy.dogs.exceptions.DuplicateAddressException;
import hu.flowacademy.dogs.repositories.ShelterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ShelterService {
    private final ShelterRepository shelterRepository;

    public Shelter addShelter(ShelterDTO shelterDTO) {
        Shelter shelter = new Shelter();
        shelter.setAddress(shelterDTO.getAddress());
        shelter.setCapacity(shelterDTO.getCapacity());
        shelter.setDogs(new ArrayList<>());
        shelterRepository.save(shelter);
        return shelter;
    }

    private void checkIfAddressAlreadyExists(String address) {
        if (shelterRepository
                .findAll()
                .stream()
                .anyMatch(shelter -> shelter.getAddress().equalsIgnoreCase(address))) {
            throw new DuplicateAddressException();
        }
    }

}
