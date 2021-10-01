package co.com.bancolombia.consumer;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bancolombia.model.pet.Category;
import co.com.bancolombia.model.pet.Pet;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RestConsumerTest {

	public static MockWebServer mockBackEnd;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);

		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
	}

	@After
	public void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@Test
	public void findPetsAvailableShouldReturnNotNull() throws JsonProcessingException {
		// Arrange
		String namepet = "test";
		Pet petMock = Pet.builder().name(namepet).category(Category.builder().id(0).name("cat1").build()).build();

		WebClient client = WebClient.create();
		// Act
		RestConsumer consumer = new RestConsumer(client);
		consumer.setUrlFindByStatus(mockBackEnd.url("test").toString());

		ObjectMapper mapper = new ObjectMapper();
		mockBackEnd.enqueue(new MockResponse().setResponseCode(HttpStatus.OK.value())
				.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.setBody(mapper.writeValueAsString(petMock)));

		Flux<Pet> pets = consumer.findPetsAvailable();
		// Assert
		StepVerifier.create(pets).expectNextMatches(p -> namepet.equals(p.getName())).verifyComplete();
	}

}
