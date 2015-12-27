package com.pos.orderfood.bean;

public class account2 {
private String accountid;//流水号
private String orderid;
private String date;
private String type;
private String paytype;
private String expenditure;//收支类型
private String Balance;//余额
public String getAccountid() {
	return accountid;
}
public void setAccountid(String accountid) {
	this.accountid = accountid;
}
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getPaytype() {
	return paytype;
}
public void setPaytype(String paytype) {
	this.paytype = paytype;
}
public String getExpenditure() {
	return expenditure;
}
public void setExpenditure(String expenditure) {
	this.expenditure = expenditure;
}
public String getBalance() {
	return Balance;
}
public void setBalance(String balance) {
	Balance = balance;
}
}
