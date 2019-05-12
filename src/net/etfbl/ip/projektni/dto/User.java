package net.etfbl.ip.projektni.dto;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import org.primefaces.model.StreamedContent;

public class User implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private String ime;
	private String prezime;
	private String email;
	private String studijskiProgram;
	private int godinaStudija;
	private String slika;
	private String interesovanje;
	private Fakultet fakultet;
	private int status;
	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "0123456789";
	private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

	private static final Random random = new Random();
	private static final char[] dic = (ALPHA_CAPS + ALPHA + NUMERIC + SPECIAL_CHARS).toCharArray();

	
	
	
	public User() {
		super();
	}
	
	
	public User(int id, String username, String password, String ime, String prezime, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
	}


	public User(int id, String username, String password, String ime, String prezime, String email,
			String studijskiProgram, int godinaStudija, String slika, String interesovanje, Fakultet fakultet,
			int status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.studijskiProgram = studijskiProgram;
		this.godinaStudija = godinaStudija;
		this.slika = slika;
		this.interesovanje = interesovanje;
		this.fakultet = fakultet;
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStudijskiProgram() {
		return studijskiProgram;
	}
	public void setStudijskiProgram(String studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}
	public int getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public String getInteresovanje() {
		return interesovanje;
	}
	public void setInteresovaanje(String interesovaanje) {
		this.interesovanje = interesovaanje;
	}
	public Fakultet getFakultet() {
		return fakultet;
	}
	public void setFakultet(Fakultet fakultet) {
		this.fakultet = fakultet;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public String getStatusName() {
		
		if(status==0) {
			return "Registrovan";
		}
		else if(status==1) {
			return "Aktivan";
		}
		else if(status==2)
			return "Blokiran";
		else
			return "";
		
	}

	public static String generatePassword(int len) { 

	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < len; i++) {
	        sb.append(dic[random.nextInt(dic.length)]);
	    }
	    return sb.toString();
	}
	

	

}
