package com.bridgelabz.programs;

public class Doctor {
	private String name;
	private String special;
	private String avail;
	private Long id;
	private Integer patcount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getAvail() {
		return avail;
	}
	public void setAvail(String avail) {
		this.avail = avail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPatcount() {
		return patcount;
	}
	public void setPatcount(Integer patcount) {
		this.patcount = patcount;
	}

}
