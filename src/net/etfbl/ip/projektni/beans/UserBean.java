package net.etfbl.ip.projektni.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.etfbl.ip.projektni.dao.UserDAO;
import net.etfbl.ip.projektni.dto.User;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	private static final String OSNOVNI_URL = "http://localhost:8080/ProjektniGA/rest/usersonline";
	private String username;
	private String password;
	private User user;
	private int idUserGroup=3;
	private List<User> userList;

	public int getIdUserGroup() {
		return idUserGroup;
	}

	public void setIdUserGroup(int idUserGroup) {
		this.idUserGroup = idUserGroup;
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

	public String getByUserGroup() {
		if (idUserGroup == 3)
			setUserList(UserDAO.getAllUsers());
		else
			setUserList(UserDAO.getByUserGroup(idUserGroup));
		return "#";
	}

	public String changeUserStatus() {
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("id")) {
			int id = Integer.parseInt(reqMap.get("id"));
			System.out.println("Id"+id);
		User user1 = UserDAO.selectById(id);

		System.out.println("user"+user1.getIme());
		if (user1.getStatus() == 0 || user1.getStatus() == 1) {
			UserDAO.updateUserStatus(user1.getId(), 2);
			userList.remove(user1);
			
		} else if (user1.getStatus() == 2 && user1.getFakultet() != null) {
			UserDAO.updateUserStatus(user1.getId(), 1);
			userList.remove(user1);
		} else {
			UserDAO.updateUserStatus(user1.getId(), 0);
			userList.remove(user1);
		}
		}
		return null;

	}
	
	public String resetPassword() {
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("id")) {
			int id = Integer.parseInt(reqMap.get("id"));
			System.out.println("Id"+id);

		UserDAO.updateUserPassword(id, User.generatePassword(10));
		
		}
		return null;

	}
	public String login() {
		user = UserDAO.selectByUsernameAndPasswordSimple(username, password);
		if (user != null && user.getStatus() == 3) {
			System.out.println(user.getUsername());
			HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sesija.setAttribute("user", user);
			return "pages/podaci.xhtml?faces-redirect=true";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("login-form:login-btn", new FacesMessage("Pogresni parametri"));
		return "";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";

	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getNumberOfLogedUsers() {

		String usersonline = "Nema podataka";
		try {
			URL url = new URL(OSNOVNI_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			if ((output = br.readLine()) != null) {
				usersonline = output;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return usersonline;
	}

	public String getAllUsersNumber() {
		return String.valueOf(UserDAO.getAllUsers().size());
	}

	public int getUsersPerHourCount(String date1, String date2) {
		return UserDAO.selectUserPerHourCount(date1, date2);
	}

	public String getActionByStatus(int status) {
		if (status == 0 || status == 1) {
			return "Blokiraj";
		} else if (status == 2)
			return "Oblokiran";
		else
			return "";

	}
	


}