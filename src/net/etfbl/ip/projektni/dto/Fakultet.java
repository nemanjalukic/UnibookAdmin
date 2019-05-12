package net.etfbl.ip.projektni.dto;

import java.io.Serializable;

public class Fakultet implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String naziv;
	private int id;
	
	public Fakultet() {}
	
	public Fakultet(int id,String naziv) {
		super();
		this.naziv = naziv;
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
