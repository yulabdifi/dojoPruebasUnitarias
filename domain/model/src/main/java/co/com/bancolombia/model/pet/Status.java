package co.com.bancolombia.model.pet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Status {

	public int code;
	public String message;
}
