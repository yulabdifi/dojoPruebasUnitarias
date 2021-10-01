package co.com.bancolombia.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.bancolombia.model.pet.Pet;
import co.com.bancolombia.model.pet.PetResponse;
import co.com.bancolombia.model.pet.Status;
import co.com.bancolombia.usecase.AddPetUseCase;
import co.com.bancolombia.usecase.ListPetsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

	private final ListPetsUseCase listPetsUseCase;
	private final AddPetUseCase addPetUseCase;

	public Mono<ServerResponse> findPets(ServerRequest serverRequest) {
		return ServerResponse.ok().body(listPetsUseCase.listPets(), PetResponse.class);
	}

	public Mono<ServerResponse> addPet(ServerRequest serverRequest) {
		Mono<Status> status = serverRequest.bodyToMono(Pet.class).flatMap(addPetUseCase::addPet)
				.map(s -> Status.builder().code(200).message(s).build());
		return ServerResponse.ok().body(status, Status.class);
	}
}
