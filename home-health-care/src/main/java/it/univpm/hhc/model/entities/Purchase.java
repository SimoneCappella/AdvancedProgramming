package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="purchase")
public class Purchase implements Serializable {

	private Long id_purchase;
	private Long cart_code;
	private String pay_method;
	private String date;
	private Long user_code;
	private Long add_code;
	private double total;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PURCHASE")
	public Long getId_purchase() {
		return id_purchase;
	}

	public void setId_purchase(Long id_purchase){
		this.id_purchase = id_purchase;
	}
	@Column(name = "CART_CODE")
	public Long getCart_code() {
		return cart_code;
	}

	public void setCart_code(Long cart_code){
		this.cart_code = cart_code;
	}
	
	@Column(name = "PAY_METHOD")
	public String getPay_methos() {
		return pay_method;
	}

	public void setPay_method(String pay_method){
		this.pay_method = pay_method;
	}

@Column(name = "DATE")
	public String getDate() {
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}
@Column(name = "USER_CODE")
	public Long getUser_code() {
		return user_code;
	}

	public void setUser_code(Long user_code){
		this.user_code = user_code;
	}
@Column(name = "ADD_CODE")
	public Long getAdd_code() {
		return add_code;
	}

	public void setAdd_code(Long add_code){
		this.add_code = add_code;
	}
@Column(name = "TOTAL")
	public double getTotal() {
		return total;
	}

	public void setAdd_code(double total){
		this.total = total;
	}

	}
