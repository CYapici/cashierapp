package com.cagatayyapici.cashieapp.model;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.junit.Test;

import com.cagatayyapici.cashierapp.model.Bill;
import com.cagatayyapici.cashierapp.model.Order;
import com.cagatayyapici.cashierapp.model.Product;
import com.cagatayyapici.cashierapp.model.Types.ProductTypes;
import com.cagatayyapici.cashierapp.model.Types.Heat;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * 
 * @author cagatay yapici
 *
 */
public class BillTest {

	private Bill testBill;
	private Random random;

	@Before
	public void setup() {
		testBill = new Bill();
		random = new Random(1);
	}

	@Test(expected = NullPointerException.class)
	public void showNullCartProductCannotBeAddedToCart() {
		testBill.addOrder(null);
	}

	@Test(expected = NullPointerException.class)
	public void showNullCartProductProductTypeCannotBeAddedToCart() {
		testBill.addOrder(
				new Order(new Product("Chai Tea Latte", new BigDecimal("1.00"), "Coffee", null), 1, Heat.COLD));
	}

	@Test(expected = NullPointerException.class)
	public void showZeroCountProductCannotBeAddedToCart() {
		testBill.addOrder(new Order(null, 1, Heat.COLD));
	}

	@Test
	public void showAddToCartComputesTotals() {

		BigDecimal price = new BigDecimal("1.10");

		testBill.addOrder(new Order(new Product("Chai Tea Latte", price, "Cold Coffee", ProductTypes.DRINK), 2,
				Heat.COLD));

		BigDecimal total = price.multiply(new BigDecimal(2));

		assertTrue(total.equals(new BigDecimal("2.20")));

		assertTrue(testBill.getTotal().equals((total)));

		price = new BigDecimal("2.30");

		total = total.add(price.multiply(new BigDecimal(2)));
 
		testBill.addOrder(
				new Order(new Product("CheeseBurger", price, "Hot Sandwich", ProductTypes.FOOD), 2, Heat.HOT));

		assertTrue(testBill.getTotal().equals((new BigDecimal("8.16"))));

		printReceipt(testBill);
	}

	private void printReceipt(Bill customerBill) {
		VelocityEngine ve = new VelocityEngine();
		Properties props = new Properties();
		props.put("file.resource.loader.path", "src/test/resources");
		ve.init(props);
		Template t = ve.getTemplate("outputtemplate.vtl");

		VelocityContext context = new VelocityContext();
		context.put("date", new Date());
		context.put("table number", 1 + random.nextInt(10));
		context.put("checkNumber", 1 + random.nextInt(10));
		context.put("testBill", testBill);

		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		System.out.println(writer.toString());
	}

	public static Product createProduct(BigDecimal price) {
		return new Product("Coffee", price, "Hot Coffee", ProductTypes.DRINK);
	}
}
