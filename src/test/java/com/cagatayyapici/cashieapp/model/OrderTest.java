package com.cagatayyapici.cashieapp.model;

import org.junit.Test;

import com.cagatayyapici.cashierapp.model.Order;
import com.cagatayyapici.cashierapp.model.Types.Heat;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * 
 * @author cagatay yapici
 *
 */
public class OrderTest {
	private final BigDecimal price = new BigDecimal("1.20");

	@Test(expected = NullPointerException.class)
	public void showNullProductIsRejected() {
		new Order(null, 1, Heat.COLD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void showZeroCountIsRejected() {

		new Order(BillTest.createProduct(price), -1, Heat.COLD);
	}

	@Test(expected = NullPointerException.class)
	public void showNullTemperatureTypeIsRejected() {

		new Order(BillTest.createProduct(price), -1, null);
	}

	@Test
	public void showValidProductIsAddedTocart() {

		BigDecimal currentTotal = price.multiply(new BigDecimal(2));
		Order cartProduct = new Order(BillTest.createProduct(price), 2, Heat.HOT);
		assertNotNull(cartProduct);
		assertTrue(cartProduct.getTotal().equals((currentTotal)));
		assertTrue(cartProduct.getProduct().getPrice().equals((price)));
	}
}
