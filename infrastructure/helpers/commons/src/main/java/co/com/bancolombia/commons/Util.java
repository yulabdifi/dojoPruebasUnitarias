package co.com.bancolombia.commons;

import co.com.bancolombia.model.pet.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

	public static String getStatusByCategory(Category category) {
		String status = null;
		if (category != null && category.getName() != null) {
			switch (category.getName()) {
			case "cat1":
				status = "available";
				break;
			case "cat2":
				status = "given";
				break;
			default:
				status = "pending";
				break;
			}
		}
		return status;
	}

}
