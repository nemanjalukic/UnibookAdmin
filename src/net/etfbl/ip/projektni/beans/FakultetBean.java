package net.etfbl.ip.projektni.beans;

import java.io.Serializable;
import java.util.ArrayList;

import net.etfbl.ip.projektni.dao.FakultetDAO;
import net.etfbl.ip.projektni.dto.Fakultet;


public class FakultetBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Fakultet fakultet= new Fakultet();
	
	
	public Fakultet getFakultet() {
		return fakultet;
	}
	public void setFakultet(Fakultet fakultet) {
		this.fakultet = fakultet;
	}
	
	public ArrayList<Fakultet> getAllFakulteti(){
		
		return FakultetDAO.selectAllFakutet();
		
		
	}
	
	public Fakultet getByName(String name) {
		Fakultet f=new Fakultet();
		for(Fakultet fa:getAllFakulteti()) {
			if(fa.getNaziv().equals(name))
				f=fa;
		}
		return f;

	}
	
	public Fakultet getById(int id) {
		Fakultet f=null;
		for(Fakultet fa:getAllFakulteti()) {
			if(fa.getId()==id)
				f=fa;
		}
		return f;

	}
	
	
	
	

}
