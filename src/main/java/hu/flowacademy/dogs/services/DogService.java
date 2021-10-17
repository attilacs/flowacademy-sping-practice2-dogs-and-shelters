package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.dtos.DogDTO;
import hu.flowacademy.dogs.dtos.DogResponse;
import hu.flowacademy.dogs.entities.Dog;
import hu.flowacademy.dogs.entities.Shelter;
import hu.flowacademy.dogs.exceptions.CapacityFullException;
import hu.flowacademy.dogs.exceptions.ChipIdAlreadyExistsException;
import hu.flowacademy.dogs.repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
    private final ShelterService shelterService;

    public DogResponse addDog(DogDTO dogDTO) {
        Shelter shelter = shelterService.findShelterById(dogDTO.getShelter_id());
        checkIfChipIdExists(dogDTO);
        checkIfShelterIsFull(shelter.getId());
        Dog dog = new Dog();
        dog.setChipId(dogDTO.getChipId());
        dog.setName(dogDTO.getName());
        dog.setGender(dogDTO.getGender());
        dog.setShelter(shelter);
        dogRepository.save(dog);
        return dogToDogResponse(dog);
    }

    public DogResponse dogToDogResponse(Dog dog) {
        DogResponse dogResponse = new DogResponse();
        dogResponse.setId(dog.getId());
        dogResponse.setChipId(dog.getChipId());
        dogResponse.setName(dog.getName());
        dogResponse.setGender(dog.getGender());
        dogResponse.setShelter_id(dog.getShelter().getId());
        return dogResponse;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    private void checkIfChipIdExists(DogDTO dogDTO) {
        String chipId = dogDTO.getChipId();
        if (getAllDogs()
                .stream()
                .anyMatch(dog -> dog.getChipId().equalsIgnoreCase(chipId))) {
            throw new ChipIdAlreadyExistsException();
        }
    }

    private Long getDogCountByShelterId(Long shelterId) {
        return getAllDogs()
                .stream()
                .filter(dog -> Objects.equals(dog.getShelter().getId(), shelterId))
                .count();
    }

    private void checkIfShelterIsFull(Long shelterId) {
        Shelter shelter = shelterService.findShelterById(shelterId);
        Integer capacity = shelter.getCapacity();
        int dogCountInShelter = Math.toIntExact(getDogCountByShelterId(shelterId));
        if (dogCountInShelter >= capacity) {
            throw new CapacityFullException();
        }
    }
}
