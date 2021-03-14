package com.example.demo.service;

import com.example.demo.model.Dog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface DogService {
    List<Dog> findAllDogs();

    Optional<Dog> findOneDog(Long id);

    List<Dog> findByAgeGreater(Integer age);

    Dog findDogByRace(String race);

    List<Dog> findByIsVaccinated(Boolean isVaccinated);


    Optional<Dog> findOnrByName(String name);

    Dog createDog(Dog dog);


    // UPDATE
    Dog updateDog(Dog dog);

    // DELETE
    ResponseEntity<Void> deleteById(Long id);
    ResponseEntity<Void> deleteAllDogs();

}
