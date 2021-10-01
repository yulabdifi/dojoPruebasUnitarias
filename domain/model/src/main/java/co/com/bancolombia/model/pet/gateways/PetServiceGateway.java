package co.com.bancolombia.model.pet.gateways;

import co.com.bancolombia.model.pet.Pet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetServiceGateway {
	
	public Flux<Pet> findPetsAvailable();
	
	public Mono<String> addPet(Pet pet, String status);
	
}
