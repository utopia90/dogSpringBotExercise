package com.example.demo.controller;


import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import com.example.demo.service.DogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DogController {

    private final Logger log = LoggerFactory.getLogger(DogController.class);

    private DogService dogService;

    public DogController(DogService dogService){
        this.dogService = dogService;
    }

    //CRUD:

    //1.retrieve all
    @GetMapping("/dogs")
    public List<Dog> findDogs(){
        log.debug("REST request to find all dogs");
        return dogService.findAllDogs();
    }
    //2.retrieve one
    @GetMapping("/dogs/{id}")
    public ResponseEntity<Dog> findOne(@PathVariable("id") Long id){
        log.info("REST request to find one employe by id:[]", id);
        Optional<Dog> dogOpt =dogService.findOneDog(id);

        if(dogOpt.isPresent())
            return ResponseEntity.ok().body(dogOpt.get());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //3.create new dog
    @PostMapping("/dogs")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) throws URISyntaxException {
        log.debug("REST request to save a Dog: {}", dog);
        if (dog.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Dog result = dogService.createDog(dog);
        return ResponseEntity
                .created(new URI("/api/dogs/" + result.getId())).body(result);

    }
    //4. update one dog
    @PutMapping("dogs")
    public ResponseEntity<Dog> updateDog(@RequestBody Dog dog){
        log.debug("REST request to update an dog{}", dog);
        if (dog.getId() == null) {
            log.warn("updating dog without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Dog dogDB = dogService.updateDog(dog);
        return ResponseEntity.ok().body(dogDB);
    }
    //5. delete one dog by id
    @DeleteMapping("/dogs/{id}")
    public ResponseEntity<Void> deleteDogs(@PathVariable Long id){
        log.debug("REST request to delete an dog by Id{} ", id);
       return dogService.deleteById(id);
    }
    //5.1 delete all dogs
    @DeleteMapping("/dogs")
    public ResponseEntity<Void> deleteAllDogs(@PathVariable Long id) {
        return dogService.deleteAllDogs();
    }
    //6.retrieve dogs by race
    @GetMapping("/dogs/race/{race}")
    public ResponseEntity<Dog>findDogByRace(@PathVariable String race){
        Dog dogOpt = dogService.findDogByRace(race);
        if (dogOpt.getId() != null)
            return ResponseEntity.ok().body(dogOpt);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //7. retrieve multiple results by property - dogs that are vaccinated
    @GetMapping("/dogs/isVaccinated/{isVaccinated}")
    public ResponseEntity<List<Dog>> filterbyIsVaccinated(@PathVariable Boolean isVaccinated) {
        log.debug("Filter all dogs by vaccine status: {}", isVaccinated);
        List<Dog> dogs = dogService.findByIsVaccinated(isVaccinated);
        if (dogs.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(dogs);
    }
    //8.Filter - filter dogs with age greater than -
    @GetMapping("/dogs/age/age-greater/{age}")
    public ResponseEntity<List <Dog>> filterByAgeGreater(@PathVariable Integer age) {
        log.debug("REST request to filter age {}", age);
        List<Dog> dogs = dogService.findByAgeGreater(age);
        if(dogs.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(dogs);
    }
}
