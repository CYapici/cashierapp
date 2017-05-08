package com.cagatayyapici.cashierapp.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

import com.cagatayyapici.cashierapp.model.Types.Heat;

/**
 * 
 * 
 * an extra drink is an order as well
 * 
 * @author cagatay yapici
 *
 */
public class Order {

	private int count;
	private Product product;
	private Heat temperatureType;
	private BigDecimal total;

	// lock the parameterless constructor
	@SuppressWarnings("unused")
	private Order() {
	}

	/**
	 * 
	 * @param product
	 * @param count
	 * @param temperatureType
	 */
	public Order(Product product, int count, Heat temperatureType) {

		Validate.notNull(product, "please assign  not null product");
		Validate.notNull(temperatureType, "please assign  not null temperatureType");
		Validate.isTrue(count > 0, "please assign  positive count ");

		total = BigDecimal.ZERO;
		this.product = product;
		this.count = count;
		this.temperatureType = temperatureType;
		computeTotal();
	}

	private void computeTotal() {
		total = product.getPrice().multiply(new BigDecimal(count));
	}

	public int getCount() {
		return count;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Heat getTemperatureType() {
		return temperatureType;
	}

	public void setTemperatureType(Heat temperatureType) {
		this.temperatureType = temperatureType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Order))
			return false;

		Order other = (Order) o;

		if (count != other.count)
			return false;
		if (product != null ? !product.equals(other.product) : other.product != null)
			return false;
		if (temperatureType != other.temperatureType)
			return false;
		return total != null ? total.equals(other.total) : other.total == null;

	}

	@Override
	public int hashCode() {
		int result = count;
		result = 31 * result + (product != null ? product.hashCode() : 0);
		result = 31 * result + (temperatureType != null ? temperatureType.hashCode() : 0);
		result = 31 * result + (total != null ? total.hashCode() : 0);
		return result;
	}

}
