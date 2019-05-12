package net.etfbl.ip.projektni.beans;

import java.io.Serializable;

import net.etfbl.ip.projektni.dao.VezaDAO;
import net.etfbl.ip.projektni.dto.Veza;

public class VezaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Veza veza =new Veza();
	public Veza getVeza() {
		return veza;
	}
	public void setVeza(Veza veza) {
		this.veza = veza;
	}
	
	public void deleteFreind(int userID,int freindID) {
		
		VezaDAO.updateDeleteVeza(userID, freindID);
	}
	
public void addFreind(int userID,int freindID) {
		
		VezaDAO.addFreind(userID, freindID);
	}

public void acceptFreind(int userID,int freindID) {
	
	VezaDAO.updateVeza(1,userID, freindID);
}

public void rejectFreind(int userID,int freindID) {
	
	VezaDAO.updateVeza(0,userID, freindID);
}
	
	
	

}
