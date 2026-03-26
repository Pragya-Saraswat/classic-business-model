package com.businessmodel.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "productlines")
public class ProductLine {

	@Id
	private String productLine;
	private String textDescription;

	@Column(columnDefinition = "mediumtext")
	private String htmlDescription;

	@OneToMany(mappedBy = "productLine")
	private List<Product> products;

	public ProductLine() {

	}

	public ProductLine(String productLine, String textDescription, String htmlDescription, List<Product> products) {

		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.products = products;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getTextDescription() {
		return textDescription;
	}

	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	public String getHtmlDescription() {
		return htmlDescription;
	}

	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}