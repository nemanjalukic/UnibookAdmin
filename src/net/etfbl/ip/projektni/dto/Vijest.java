package net.etfbl.ip.projektni.dto;

import java.io.Serializable;
import java.util.Date;

public class Vijest extends Objava implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String naslov;
	private String sadrzaj;
	
	public Vijest(int id, int likes, int dislikes, Date vrijemeKreiranja, String naslov, String sadrzaj) {
		super(id, likes, dislikes, vrijemeKreiranja);
		this.naslov = naslov;
		this.sadrzaj = sadrzaj;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	

}
