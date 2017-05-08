package com.cagatayyapici.cashieapp.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.cagatayyapici.cashierapp.model.Product;
import com.cagatayyapici.cashierapp.model.Types.ProductTypes;

/**
 * 
 * @author cagatay yapici
 *
 */
public class ProductTest {

	@Test(expected = NullPointerException.class)
	public void showNullProductTypeIsRejected() {
		new Product("Espresso", new BigDecimal("1.00"), "Coffee", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void showEmptyDescriptionIsRejected() {
		new Product("Espresso", new BigDecimal("1.00"), "", ProductTypes.DRINK);
	}

	@Test(expected = NullPointerException.class)
	public void showNullPriceIsRejected() {
		new Product("Espresso", null, "Coffee", ProductTypes.DRINK);
	}

	@Test(expected = IllegalArgumentException.class)
	public void showEmptyNameIsRejected() {
		new Product("", new BigDecimal("1.00"), "Coffee", ProductTypes.DRINK);
	}

	@Test
	public void showValidProducthasValidFiels() {
		BigDecimal price = new BigDecimal("1.50");
		Product currentProduct = BillTest.createProduct(price);
		assertTrue(currentProduct.getPrice().equals((price))); 
		assertEquals(currentProduct.getProductType(), ProductTypes.DRINK);
		assertEquals(currentProduct.getName(), "Coffee");
		assertEquals(currentProduct.getDescription(), "Hot Coffee");
		
	}
}
