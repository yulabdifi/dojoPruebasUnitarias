package co.com.bancolombia.consumer;

import co.com.bancolombia.model.pet.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ObjectRequest {

	private long id;
	private String name;
	private Category category;
	private String status;

}
