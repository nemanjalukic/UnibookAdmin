package net.etfbl.ip.projektni.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Objava  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int likes;
	private int dislikes;
	private Date vrijemeKreiranja;
	private int opcija;
	
	
	
	public Objava() {
		super();
	}
	public Objava(int id, int likes, int dislikes, Date vrijemeKreiranja) {
		super();
		this.id = id;
		this.likes = likes;
		this.dislikes = dislikes;
		this.vrijemeKreiranja = vrijemeKreiranja;
		this.opcija=0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public Date getVrijemeKreiranja() {
		return vrijemeKreiranja;
	}
	public void setVrijemeKreiranja(Date vrijemeKreiranja) {
		this.vrijemeKreiranja = vrijemeKreiranja;
	}
	public int getOpcija() {
		return opcija;
	}
	public void setOpcija(int opcija) {
		this.opcija = opcija;
	}
	
public String getVrijemeKreiranjaString() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		return formatter.format(vrijemeKreiranja);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objava other = (Objava) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	
	
}
