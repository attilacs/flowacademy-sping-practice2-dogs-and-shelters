package hu.flowacademy.dogs.repositories;

import hu.flowacademy.dogs.entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
