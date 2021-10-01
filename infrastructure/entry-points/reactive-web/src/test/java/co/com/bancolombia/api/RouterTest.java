package co.com.bancolombia.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import co.com.bancolombia.model.pet.Pet;
import co.com.bancolombia.model.pet.PetResponse;
import co.com.bancolombia.model.pet.Status;
import co.com.bancolombia.usecase.AddPetUseCase;
import co.com.bancolombia.usecase.ListPetsUseCase;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RouterTest {

	@MockBean
	ListPetsUseCase listPetsUseCase;
	@MockBean
	AddPetUseCase addPetUseCase;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void findPetsShouldReturnHttpStatusOK() {

		List<Pet> data = new ArrayList<>();
		data.add(Pet.builder().name("test").build());
		Status status = Status.builder().code(200).build();

		Mono<PetResponse> resp = Mono.just(PetResponse.builder().data(data).status(status).build());

		Mockito.when(listPetsUseCase.listPets()).thenReturn(resp);

		webTestClient.get().uri("/api/findPets").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.is2xxSuccessful();

	}

}
