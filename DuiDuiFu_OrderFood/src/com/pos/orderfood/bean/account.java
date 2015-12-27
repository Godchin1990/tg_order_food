package com.pos.orderfood.bean;

public class account {
private String id;//申请编号
private String cardid;//卡号
private String statue;//申请状态
private String money;//体现金额
private String fee;//手续费
private String data;//体现时间
private String person;//操作人
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCardid() {
	return cardid;
}
public void setCardid(String cardid) {
	this.cardid = cardid;
}
public String getStatue() {
	return statue;
}
public void setStatue(String statue) {
	this.statue = statue;
}
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}
public String getFee() {
	return fee;
}
public void setFee(String fee) {
	this.fee = fee;
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
}
