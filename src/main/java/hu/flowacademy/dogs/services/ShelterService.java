package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.dtos.ShelterDTO;
import hu.flowacademy.dogs.entities.Shelter;
import hu.flowacademy.dogs.repositories.ShelterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShelterService {
    private final ShelterRepository shelterRepository;

    public Shelter addShelter(ShelterDTO shelterDTO) {
        Shelter shelter = new Shelter();
        shelter.setAddress(shelter.getAddress());
        shelter.setCapacity(shelterDTO.getCapacity());
        shelterRepository.save(shelter);
        return shelter;
    }

}
