package com.example.demo.service.impl;

import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import com.example.demo.service.DogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {
    private final Logger log = LoggerFactory.getLogger(DogServiceImpl.class);

    private DogRepository repository;

    public DogServiceImpl(DogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Dog> findAllDogs() {
        return repository.findAll();
    }

    @Override
    public Optional<Dog> findOneDog(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Dog> findByAgeGreater(Integer age) {
        return repository.findAllByAgeAfter(age);
    }

    @Override
    public Dog findDogByRace(String race) {

        return repository.findByRace(race);
    }

    @Override
    public List<Dog> findByIsVaccinated(Boolean isVaccinated) {

        return repository.findByIsVaccinated(isVaccinated);
    }

    @Override
    public Optional<Dog> findOnrByName(String name) {
        return Optional.empty();
    }

    @Override
    public Dog createDog(Dog dog) {

        return repository.save(dog);
    }

    @Override
    public Dog updateDog(Dog dog) {
        return repository.save(dog);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        if(!repository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Override
    public ResponseEntity<Void> deleteAllDogs() {
        log.debug("REST request to delete all dogs");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }



}
