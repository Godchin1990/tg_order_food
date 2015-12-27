package com.pos.orderfood.bean;

public class Order {
private String orderid;
private String data;
private String person;//下单人
private String statue;
private String type;//订单类型
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getPerson() {
	return person;
}
public void setPerson(String person) {
	this.person = person;
}
public String getStatue() {
	return statue;
}
public void setStatue(String statue) {
	this.statue = statue;
}
}
