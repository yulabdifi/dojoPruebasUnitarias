package co.com.bancolombia.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

	@Bean
	public RouterFunction<ServerResponse> routerFunction(Handler handler) {
		return route(GET("/api/findPets"), handler::findPets).andRoute(POST("/api/addPet"), handler::addPet);
	}
}
