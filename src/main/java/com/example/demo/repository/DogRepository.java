package com.example.demo.repository;

import com.example.demo.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog,Long> {
  Dog findByRace(String race);

  List<Dog> findByIsVaccinated(Boolean isVaccinated);

  List<Dog> findAllByAgeAfter(Integer age);
}

