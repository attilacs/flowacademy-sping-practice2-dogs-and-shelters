package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.dtos.ShelterDTO;
import hu.flowacademy.dogs.dtos.ShelterResponse;
import hu.flowacademy.dogs.entities.Shelter;
import hu.flowacademy.dogs.exceptions.AddressAlreadyExistsException;
import hu.flowacademy.dogs.repositories.ShelterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShelterService {
    private final ShelterRepository shelterRepository;

    public Shelter addShelter(ShelterDTO shelterDTO) {
        String address = shelterDTO.getAddress();
        checkIfAddressAlreadyExists(address);
        Shelter shelter = new Shelter();
        shelter.setAddress(address);
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
            throw new AddressAlreadyExistsException();
        }
    }

    private ShelterResponse shelterToShelterResponse(Shelter shelter) {
        ShelterResponse shelterResponse = new ShelterResponse();
        shelterResponse.setId(shelter.getId());
        shelterResponse.setAddress(shelter.getAddress());
        shelterResponse.setCapacity(shelter.getCapacity());
        return shelterResponse;
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

}
