package co.com.bancolombia.usecase;

import co.com.bancolombia.commons.Util;
import co.com.bancolombia.model.pet.Pet;
import co.com.bancolombia.model.pet.gateways.PetServiceGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AddPetUseCase {

	private final PetServiceGateway gateway;

	public Mono<String> addPet(Pet pet) {
		String status = Util.getStatusByCategory(pet.getCategory());
		return gateway.addPet(pet, status);
	}

}
