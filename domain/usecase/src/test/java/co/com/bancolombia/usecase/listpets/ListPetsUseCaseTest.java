package co.com.bancolombia.usecase.listpets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.bancolombia.model.pet.Pet;
import co.com.bancolombia.model.pet.PetResponse;
import co.com.bancolombia.model.pet.gateways.PetServiceGateway;
import co.com.bancolombia.usecase.ListPetsUseCase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ListPetsUseCaseTest {

	@Mock
	PetServiceGateway gateway;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void listPetsShouldReturnPetsAndStatus200() {

		Flux<Pet> pets = Flux.just(Pet.builder().name("test1").build());

		Mockito.when(gateway.findPetsAvailable()).thenReturn(pets);

		ListPetsUseCase useCase = new ListPetsUseCase(gateway);
		Mono<PetResponse> resp = useCase.listPets();

		StepVerifier.create(resp).expectNextMatches(r -> r.getData() != null && r.getStatus().getCode() == 200)
				.verifyComplete();
	}

}
