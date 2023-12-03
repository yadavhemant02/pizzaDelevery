package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class orderInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderid;
	private int userid;
	private String pizzatype;
	private int quntity;
	private String status;
	private String address;
	private int totalprice;
	public orderInfo(int orderid, int userid, String pizzatype, int quntity, String status, String address,
			int totalprice) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.pizzatype = pizzatype;
		this.quntity = quntity;
		this.status = status;
		this.address = address;
		this.totalprice = totalprice;
	}
	public orderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPizzatype() {
		return pizzatype;
	}
	public void setPizzatype(String pizzatype) {
		this.pizzatype = pizzatype;
	}
	public int getQuntity() {
		return quntity;
	}
	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	@Override
	public String toString() {
		return "orderInfo [orderid=" + orderid + ", userid=" + userid + ", pizzatype=" + pizzatype + ", quntity="
				+ quntity + ", status=" + status + ", address=" + address + ", totalprice=" + totalprice + "]";
	}
	
	
	

}
