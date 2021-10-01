package co.com.bancolombia.model.pet;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PetResponse {

	public List<Pet> data;
	public Status status;

}
