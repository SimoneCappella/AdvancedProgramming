package it.univpm.hhc.support;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.Item;

public class Myorder {
	private Address address;
	private Double total;
	private LocalDate date;
	private String paymeth;
	private Integer quantity;
	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	private List<Item> items = new ArrayList<Item>();
	
	public Myorder() {
		
	}
	
	
	public Myorder(Address address, Double total, LocalDate date, String paymeth) {
		this.address = address;
		this.total = total;
		this.date = date;
		this.paymeth = paymeth;
		this.quantity = null;
		this.items = null;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Double getTotal() {
		return total;
	}



	public void setTotal(Double total) {
		this.total = total;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public String getPaymeth() {
		return paymeth;
	}



	public void setPaymeth(String paymeth) {
		this.paymeth = paymeth;
	}



	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
}
