package co.com.bancolombia.consumer;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.bancolombia.model.pet.Pet;
import co.com.bancolombia.model.pet.gateways.PetServiceGateway;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Setter
@Service
@RequiredArgsConstructor
public class RestConsumer implements PetServiceGateway {

	private static final String STATUS_AVAILABLE = "available";

	@Value("${adapter.restconsumer.findByStatus}")
	private String urlFindByStatus;
	@Value("${adapter.restconsumer.add}")
	private String urlAdd;

	private final WebClient client;

	// these methods are an example that illustrates the implementation of
	// WebClient.
	// You should use the methods that you implement from the Gateway from the
	// domain.
	public Flux<Pet> findPetsAvailable() {

		return client.get().uri(urlFindByStatus).retrieve().bodyToFlux(Pet.class);
	}

	public Mono<String> addPet(Pet pet, String status) {

		ObjectRequest request = ObjectRequest.builder().id(ThreadLocalRandom.current().nextLong()).name(pet.getName())
				.category(pet.getCategory()).status(status).build();

		return client.post().uri(urlAdd).body(Mono.just(request), ObjectRequest.class).retrieve()
				.bodyToMono(ObjectRequest.class).filter(r -> STATUS_AVAILABLE.equals(r.getStatus())).map(p -> "Saved");
	}
}