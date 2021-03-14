package com.example.demo;

import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DogApplication implements CommandLineRunner {
	@Autowired
	private DogRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(DogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//1.COUNT- recupera número total de elementos
		long dogNumber = repository.count();
		System.out.println("Dogs Number:" + dogNumber);

		// 2. CREATE - guardar datos en BBDD.
		Dog dog1 = new Dog("Laika","Sara","astronaut dog",true,8);
		Dog dog2 = new Dog("Scooby","Sara","funny dog",true,7);
		Dog dog3 = new Dog("Zispa","Sara","z named dog",true,2);
		Dog dog4 = new Dog("Abracadabra","Sara","magical dog",true,4);


		repository.save(dog1);
		repository.save(dog2);
		repository.save(dog3);
		repository.save(dog4);



		//3. RETRIEVE ALL
		List<Dog> dogs = repository.findAll();
		System.out.println("---------------------------------MOSTRAMOS TODOS LOS PERROS----------------------------------");

		System.out.println(dogs);

		//4. RETRIIEVE ONE BY ID
		Optional<Dog> laikaOpt = repository.findById(1L);
		if(laikaOpt.isPresent())
			System.out.println("---------------------------------PERRO ENCONTRADO POR ID-----------------------------------");

		System.out.println(laikaOpt.get());

		//5.RETRIEVE BY RACE

		Dog scooby = repository.findByRace("funny dog");
		System.out.println("---------------------------------------------PERRO ENCONTRADO POR RAZA-----------------------------------");
		System.out.println(scooby);

		//6.ACTUALIZAR
		System.out.println("--------------------------------ACTUALIZAMOS NOMBRE A SCOOBY--------------------------------");

		scooby.setName("scoobyNameChanged");
		repository.save(scooby);

		//7.DELETE
		System.out.println("--------------------------------BORRAMOS A SCOOBY--------------------------------");
		repository.delete(scooby);
		//repository.deleteById
		//repository.deleteAll

//		SORT
		Sort raceSort = Sort.by("race");
		System.out.println("--------------------------------ORDENAMOS PERROS POR RAZA--------------------------------");
		System.out.println(repository.findAll(raceSort));
	}
}
