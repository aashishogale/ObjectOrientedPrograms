package com.bridgelabz.programs;

public class AddressbookObject {
	private String firstname;
	
	private String lastname;
	
	private String address;
	
	private String city;
	private String state;
	
	private Integer zip;
	private Integer phno;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		System.out.println(city);
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public Integer getPhno() {
		return phno;
	}
	public void setPhno(Integer phno) {
		this.phno = phno;
	}

}
