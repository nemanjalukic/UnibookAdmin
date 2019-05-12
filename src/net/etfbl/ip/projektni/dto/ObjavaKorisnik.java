package net.etfbl.ip.projektni.dto;

import java.io.Serializable;
import java.util.Date;

public class ObjavaKorisnik extends Objava implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String sadrzaj;
	private int tip;
	
	
	public ObjavaKorisnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ObjavaKorisnik(int id, int likes, int dislikes, Date vrijemeKreiranja, User user, String sadrzaj, int tip) {
		super(id, likes, dislikes, vrijemeKreiranja);
		this.user = user;
		this.sadrzaj = sadrzaj;
		this.tip = tip;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	
	public String getTipString() {
		if(tip==1) {
			return "Youtube video";
		}
		else if(tip==2) {
			return "Link";
		}
		else
			return "Tekst";
	}
	
	
	

}
