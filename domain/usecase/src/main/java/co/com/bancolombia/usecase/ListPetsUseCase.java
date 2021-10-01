package co.com.bancolombia.usecase;

import co.com.bancolombia.model.pet.PetResponse;
import co.com.bancolombia.model.pet.Status;
import co.com.bancolombia.model.pet.gateways.PetServiceGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ListPetsUseCase {

	private final PetServiceGateway gateway;

	public Mono<PetResponse> listPets() {
		return gateway.findPetsAvailable().collectList().map(
				ls -> PetResponse.builder().data(ls).status(Status.builder().code(200).message("OK").build()).build());
	}
}
