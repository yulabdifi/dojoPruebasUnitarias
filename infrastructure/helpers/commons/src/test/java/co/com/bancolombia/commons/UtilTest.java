package co.com.bancolombia.commons;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.com.bancolombia.model.pet.Category;

public class UtilTest {

	@Test
	public void getStatusAvailableWhenCategoryIsCat1() {
		// Arrange
		Category category = Category.builder().name("cat1").build();
		// Act
		String status = Util.getStatusByCategory(category);

		Assert.assertEquals("available", status);
	}
	
	@Test
	public void getStatusGivenWhenCategoryIsCat2() {
		// Arrange
		Category category = Category.builder().name("cat2").build();
		// Act
		String status = Util.getStatusByCategory(category);

		Assert.assertEquals("given", status);
	}

}
