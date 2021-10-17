package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.dtos.ShelterDTO;
import hu.flowacademy.dogs.dtos.ShelterResponse;
import hu.flowacademy.dogs.entities.Shelter;
import hu.flowacademy.dogs.exceptions.AddressAlreadyExistsException;
import hu.flowacademy.dogs.exceptions.CapacityFullException;
import hu.flowacademy.dogs.repositories.DogRepository;
import hu.flowacademy.dogs.repositories.ShelterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShelterService {
    private final ShelterRepository shelterRepository;
    private final DogRepository dogRepository;

    public ShelterResponse addShelter(ShelterDTO shelterDTO) {
        String address = shelterDTO.getAddress();
        checkIfAddressAlreadyExists(address);
        Shelter shelter = new Shelter();
        shelter.setAddress(address);
        shelter.setCapacity(shelterDTO.getCapacity());
        shelter.setDogs(new ArrayList<>());
        shelterRepository.save(shelter);
        return shelterToShelterResponse(shelter);
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

    public List<ShelterResponse> getAllShelters() {
        return shelterRepository
                .findAll()
                .stream()
                .map(this::shelterToShelterResponse)
                .collect(Collectors.toList());
    }

    public Shelter findShelterById(Long id) {
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isEmpty()) {
            throw new NoSuchElementException("A megadott id-val nem található shelter.");
        }
        return shelter.get();
    }

    public ShelterResponse getShelterById(Long id) {
        return shelterToShelterResponse(findShelterById(id));
    }

    private void validateShelter(Long id, ShelterDTO shelterDTO) {
        Shelter oldShelter = findShelterById(id);
        String oldAddress = oldShelter.getAddress();
        String newAddress = shelterDTO.getAddress();
        if (!oldAddress.equalsIgnoreCase(newAddress)) {
            if (shelterRepository
                    .findAll()
                    .stream()
                    .anyMatch(shelter -> shelter.getAddress().equalsIgnoreCase(newAddress))) {
                throw new AddressAlreadyExistsException();
            }
        }
        int oldCapacity = oldShelter.getCapacity();
        int newCapacity = shelterDTO.getCapacity();
        if (newCapacity < oldCapacity) {
            int dogCount = (int) dogRepository
                    .findAll()
                    .stream()
                    .filter(dog -> Objects.equals(dog.getShelter().getId(), id))
                    .count();
            if (newCapacity < dogCount) {
                throw new CapacityFullException();
            }
        }
    }

    public void updateShelter(Long id, ShelterDTO shelterDTO) {
        validateShelter(id, shelterDTO);
        Shelter shelterToUpdate = new Shelter();
        shelterToUpdate.setId(id);
        shelterToUpdate.setAddress(shelterDTO.getAddress());
        shelterToUpdate.setCapacity(shelterDTO.getCapacity());
        shelterRepository.save(shelterToUpdate);
    }
}