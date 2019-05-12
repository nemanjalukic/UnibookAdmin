package net.etfbl.ip.projektni.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dogadjaj extends Objava implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String naziv;
	private String opis;
	private String slika;
	private Date datumOdrzavanja;
	private String kategorija;
	
	public Dogadjaj(int id, int likes, int dislikes, Date vrijemeKreiranja,String naziv, String opis, String slika,Date datumOdrzavanja, String kategorija) {
		super(id, likes, dislikes, vrijemeKreiranja);
		this.naziv = naziv;
		this.opis = opis;
		this.slika = slika;
		this.kategorija = kategorija;
		this.setDatumOdrzavanja(datumOdrzavanja);
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}


	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public Date getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(Date datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}
	
	public String getVrijemeOdrzavanja() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		return formatter.format(datumOdrzavanja);
	}
	
	
	
	
	
	

}
