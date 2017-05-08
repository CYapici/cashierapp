package com.cagatayyapici.cashierapp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.cagatayyapici.cashierapp.model.Types.ProductTypes;
import com.cagatayyapici.cashierapp.model.Types.Heat;

/**
 * 
 * @author cagatay yapici
 *
 */
public class Bill {

	private BigDecimal total, subTotal, totalDiscount, totalCharge;

	private List<Order> orders;

	public Bill() {
		total = subTotal = totalDiscount = totalCharge = BigDecimal.ZERO;
		orders = new ArrayList<Order>();
	}

	/**
	 * @param ApiLevel
	 *            Design
	 */
	public void addOrders(List<Order> orders) {
		for (Order item : orders) {
			addOrder(item);
		}
	}


	public void addOrder(Order order) {

		Validate.notNull(order, "please assign not null order");

		processOrder(order);
	}

	private synchronized void processOrder(Order order) {

		// 1
		orders.add(order);

		// 2
		updateTotal();

		// 3
		calculateCharge();

		// 4
		updateCharge();

	}

	private void updateTotal() {
		total = subTotal = orders.stream().map(Order::getTotal).reduce(BigDecimal::add).get();

	}

	private void calculateCharge() {

		boolean hasFoodItems = orders.stream().filter(x -> ProductTypes.FOOD == (x.getProduct().getProductType()))
				.count() > 0;
		BigDecimal servicePercentage;

		if (hasFoodItems) {

			boolean containsHotFood = orders.stream().filter(
					x -> ProductTypes.FOOD == x.getProduct().getProductType() && Heat.HOT == x.getTemperatureType())
					.count() > 0;

			if (containsHotFood) {
				BigDecimal maxServiceCharge = new BigDecimal("20.00");
				// if there exists hot food charge %20 with £20 limit
				servicePercentage = new BigDecimal("0.20");
				totalCharge = total.multiply(servicePercentage);
				if (totalCharge.compareTo(maxServiceCharge) > 0) {
					totalCharge = maxServiceCharge;
				}
				total = total.add(totalCharge);
				return;
			}

			servicePercentage = new BigDecimal("0.10");
			totalCharge = total.multiply(servicePercentage);
			total = total.add(totalCharge);

		}

	}

	private void updateCharge() {
		total = total.setScale(2, RoundingMode.CEILING);
		subTotal = subTotal.setScale(2, RoundingMode.CEILING);
		totalCharge = totalCharge.setScale(2, RoundingMode.CEILING);
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Bill))
			return false;

		Bill that = (Bill) o;

		if (total != null ? !total.equals(that.total) : that.total != null)
			return false;
		if (subTotal != null ? !subTotal.equals(that.subTotal) : that.subTotal != null)
			return false;
		if (totalDiscount != null ? !totalDiscount.equals(that.totalDiscount) : that.totalDiscount != null)
			return false;
		return orders != null ? orders.equals(that.orders) : that.orders == null;

	}

	@Override
	public int hashCode() {
		int result = total != null ? total.hashCode() : 0;
		result = 31 * result + (subTotal != null ? subTotal.hashCode() : 0);
		result = 31 * result + (totalDiscount != null ? totalDiscount.hashCode() : 0);
		result = 31 * result + (orders != null ? orders.hashCode() : 0);
		return result;
	}

}