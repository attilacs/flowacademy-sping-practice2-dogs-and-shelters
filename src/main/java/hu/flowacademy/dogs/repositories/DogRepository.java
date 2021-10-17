package hu.flowacademy.dogs.repositories;

import hu.flowacademy.dogs.entities.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
