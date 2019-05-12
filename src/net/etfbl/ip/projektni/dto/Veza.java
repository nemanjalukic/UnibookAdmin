package net.etfbl.ip.projektni.dto;

import java.io.Serializable;

public class Veza implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user1;
	private int user2;
	private int status;
	
	public Veza() {}
	
	public Veza(int user1, int user2) {
		super();
		this.user1 = user1;
		this.user2 = user2;

	}
	public int getUser1() {
		return user1;
	}
	public void setUser1(int user1) {
		this.user1 = user1;
	}
	public int getUser2() {
		return user2;
	}
	public void setUser2(int user2) {
		this.user2 = user2;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	

}
