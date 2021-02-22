package it.univpm.hhc.support;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.Item;

public class Myorder {
	private Address address;
	private Double total;
	private LocalDate date;
	private String paymeth;
	private Integer quantity;
	private HashMap<Item, Integer> items = new HashMap<Item, Integer>();
	
	public Myorder() {
		
	}
	
	
	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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



	public HashMap<Item, Integer> getItems() {
		return items;
	}



	public void setItems(HashMap<Item, Integer> items) {
		this.items = items;
	}
	
	
}
