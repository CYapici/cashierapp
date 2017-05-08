package com.cagatayyapici.cashierapp.model;

import org.apache.commons.lang3.Validate;

import com.cagatayyapici.cashierapp.model.Types.ProductTypes;

import java.math.BigDecimal;

/**
 * 
 * @author cagatay yapici
 *
 */
public class Product {

	private String name;
	private BigDecimal price;
	private String description;
	private ProductTypes productType;

	@SuppressWarnings("unused")
	private Product() {
	}

	public Product(String name, BigDecimal price, String description, ProductTypes productType) {

		Validate.notBlank(name, "please assign not null name");
		Validate.notNull(price, "please assign  not null price");
		Validate.notBlank(description, "please assign  not null description");
		Validate.notNull(productType, "please assign  not null productType");

		this.name = name;
		this.description = description;
		this.productType = productType;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductTypes getProductType() {
		return productType;
	}

	public void setProductType(ProductTypes productType) {
		this.productType = productType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Product))
			return false;

		Product product = (Product) o;

		if (name != null ? !name.equals(product.name) : product.name != null)
			return false;
		if (price != null ? !price.equals(product.price) : product.price != null)
			return false;
		if (description != null ? !description.equals(product.description) : product.description != null)
			return false;
		return productType == product.productType;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (productType != null ? productType.hashCode() : 0);
		return result;
	}

}
