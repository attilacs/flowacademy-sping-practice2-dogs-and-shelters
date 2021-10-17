package hu.flowacademy.dogs.services;

import hu.flowacademy.dogs.dtos.DogDTO;
import hu.flowacademy.dogs.entities.Dog;
import hu.flowacademy.dogs.entities.Shelter;
import hu.flowacademy.dogs.repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
    private final ShelterService shelterService;

    public Dog addDog(DogDTO dogDTO) {
        Shelter shelter = shelterService.findShelterById(dogDTO.getShelter_id());
        Dog dog = new Dog();
        dog.setChipId(dogDTO.getChipId());
        dog.setName(dogDTO.getName());
        dog.setGender(dogDTO.getGender());
        dog.setShelter(shelter);
        return dogRepository.save(dog);
    }
}
