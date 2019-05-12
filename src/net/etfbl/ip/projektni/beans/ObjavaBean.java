package net.etfbl.ip.projektni.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.etfbl.ip.projektni.dao.ObjavaDAO;
import net.etfbl.ip.projektni.dao.UserDAO;
import net.etfbl.ip.projektni.dto.Dogadjaj;
import net.etfbl.ip.projektni.dto.Objava;
import net.etfbl.ip.projektni.dto.ObjavaKorisnik;
import net.etfbl.ip.projektni.dto.User;
import net.etfbl.ip.projektni.dto.Vijest;
@ManagedBean(name = "objavaBean")
@SessionScoped
public class ObjavaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Objava objava= new Objava();
	private List<ObjavaKorisnik> objaveKorisnik;
	private List<Dogadjaj> dogadjaji;
	private List<Vijest> vijesti;
	
	public ObjavaBean(){
		objaveKorisnik=ObjavaDAO.selectAllObjavaKorisnik();
		dogadjaji=ObjavaDAO.selectAllDogadjaj();
		vijesti=ObjavaDAO.selectAllVijest();
	}
	
	public Objava getObjava() {
		return objava;
	}
	public void setObjava(Objava objava) {
		this.objava = objava;
	}
	public List<ObjavaKorisnik> getObjaveKorisnik() {
		return objaveKorisnik;
	}
	public void setObjaveKorisnik(List<ObjavaKorisnik> objaveKorisnik) {
		this.objaveKorisnik = objaveKorisnik;
	}
	public List<Dogadjaj> getDogadjaji() {
		return dogadjaji;
	}
	public void setDogadjaji(List<Dogadjaj> dogadjaji) {
		this.dogadjaji = dogadjaji;
	}
	public List<Vijest> getVijesti() {
		return vijesti;
	}
	public void setVijesti(List<Vijest> vijesti) {
		this.vijesti = vijesti;
	}
	public String obrisiObjavu() {
		
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("idObjava")) {
			int id = Integer.parseInt(reqMap.get("idObjava"));
			ObjavaDAO.updateObjavaDelete(id);
		
		for(ObjavaKorisnik o:objaveKorisnik) {
			if(o.getId()==id) {
				objaveKorisnik.remove(o);
				break;
			}
		}
		for(Dogadjaj d:dogadjaji) {
			if(d.getId()==id) {
				dogadjaji.remove(d);
				break;
			}
		}
		for(Vijest v: vijesti) {
			if(v.getId()==id) {
				vijesti.remove(v);
				break;
			}
		}
		}
		return null;
	}
	public String updateObjave(){
		objaveKorisnik=ObjavaDAO.selectAllObjavaKorisnik();
		return "#";
	}
	public String updateDogadjaji(){
		dogadjaji=ObjavaDAO.selectAllDogadjaj();
		return "#";
	}
	public String updateVijesti(){
		vijesti=ObjavaDAO.selectAllVijest();
		return "#";
	}
}
