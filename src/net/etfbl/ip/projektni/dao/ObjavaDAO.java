package net.etfbl.ip.projektni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import net.etfbl.ip.projektni.dto.Dogadjaj;
import net.etfbl.ip.projektni.dto.Fakultet;
import net.etfbl.ip.projektni.dto.Objava;
import net.etfbl.ip.projektni.dto.ObjavaKorisnik;
import net.etfbl.ip.projektni.dto.User;
import net.etfbl.ip.projektni.dto.Vijest;


public class ObjavaDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM projektni.objava join objavakorisnik on objava.idObjava=objavakorisnik.Objava_idObjava join user on user.idUser=objavakorisnik.User_idUser join fakultet on user.idFakultet=fakultet.idFakultet where Obrisano=0";
	private static final String SQL_SELECT_ALL_LIKED ="SELECT * FROM objava join projektni.lajkovano on objava.idObjava=lajkovano.Objava_idObjava where Obrisano=0 and User_idUser_liked=?";
	private static final String SQL_SELECT_ALL_DOGADJAJI="SELECT * FROM  objava join dogadjaj on dogadjaj.Objava_idObjava=objava.idObjava join kategorija on dogadjaj.Kategorija_idKategorija=kategorija.idKategorija where Obrisano=0";
	private static final String SQL_SELECT_ALL_VIJESTI ="SELECT * FROM projektni.objava JOIN vijest on objava.idObjava=vijest.Objava_idObjava where Obrisano=0";
	private static final String SQL_UPDATE_LIKE_DISLIKE = "UPDATE objava set Likes=(Likes+1), Dislikes=(Dislikes-1) WHERE idObjava = ?";
	private static final String SQL_UPDATE_DISLIKE_LIKE = "UPDATE objava set Likes=(Likes-1), Dislikes=(Dislikes+1) WHERE idObjava = ?";
	private static final String SQL_SELECT_LAJKOVANO = "SELECT Opcija FROM lajkovano WHERE Objava_idObjava = ? AND User_idUser_liked=?";
	private static final String SQL_UPDATE_LAJKOVANO = "UPDATE lajkovano set Opcija=? WHERE Objava_idObjava = ? AND User_idUser_liked=?";
	private static final String SQL_INSERT_LAJKOVANO= "INSERT INTO lajkovano (Objava_idObjava,User_idUser_liked,Opcija) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE_UNLIKE = "UPDATE objava set Likes=(Likes-1) WHERE idObjava = ?";
	private static final String SQL_UPDATE_LIKE = "UPDATE objava set Likes=(Likes+1) WHERE idObjava = ?";
	private static final String SQL_UPDATE_UNDISLIKE = "UPDATE objava set Dislikes=(Dislikes-1) WHERE idObjava = ?";
	private static final String SQL_UPDATE_DISLIKE = "UPDATE objava set  Dislikes=(Dislikes-1) WHERE idObjava = ?";
	private static final String SQL_INSERT_OBJAVA= "INSERT INTO objava () VALUES ()";
	private static final String SQL_INSERT_OBJAVA_KORISNIK= "INSERT INTO objavakorisnik (Objava_idObjava,User_idUser,Sadrzaj,TipObjave) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE_DELETE = "UPDATE objava set Obrisano=1 WHERE idObjava = ?";

	
	public static ArrayList<ObjavaKorisnik> selectAllObjavaKorisnik() {
		ArrayList<ObjavaKorisnik> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new ObjavaKorisnik(rs.getInt("idObjava"),rs.getInt("Likes"),rs.getInt("Dislikes"), formatter.parse(rs.getString("vrijemeKreiranja")),new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"), rs.getString("StudijskiProgram"),rs.getInt("GodinaStudija"),rs.getString("Slika"),rs.getString("Interesovanje"),new Fakultet(rs.getInt("idFakultet"),rs.getString("Naziv")),rs.getInt("Status")),rs.getString("Sadrzaj"),rs.getInt("TipObjave")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} catch (ParseException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static HashMap<Integer,Integer> selectAllKorisnikLiked(int idKorisnik) {
		
		HashMap<Integer,Integer> retVal = new HashMap<>();
		Connection connection = null;
		ResultSet rs = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Object values[] = {idKorisnik};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_LIKED, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.put(rs.getInt("idObjava"),rs.getInt("Opcija"));
				}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static ArrayList<ObjavaKorisnik> selectAllByKorisnik(User korisnik) {
		ArrayList<ObjavaKorisnik> retVal = new ArrayList<>();
		ArrayList<User> users= new ArrayList<>();
		users=UserDAO.getFreinds(korisnik.getId());
		users.add(korisnik);
		ArrayList<ObjavaKorisnik> sve= new ArrayList<>();
		sve = selectAllObjavaKorisnik();
		for(ObjavaKorisnik o:sve) {
			for(User u: users) {
				if(o.getUser().equals(u))
					retVal.add(o);
			}
		
		
		}
		return retVal;
	}
	
	public static ArrayList<Dogadjaj> selectAllDogadjaj() {
		ArrayList<Dogadjaj> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_DOGADJAJI, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new Dogadjaj(rs.getInt("idObjava"),rs.getInt("Likes"),
						rs.getInt("Dislikes"),formatter.parse(rs.getString("vrijemeKreiranja")),
						rs.getString("Naziv"),rs.getString("Opis"),rs.getString("Slika")
						,formatter.parse(rs.getString("VrijemeOdrzavanja")),rs.getString("Kategorija")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} catch (ParseException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	
	public static ArrayList<Vijest> selectAllVijest() {
		ArrayList<Vijest> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_VIJESTI, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			retVal.add(new Vijest(rs.getInt("idObjava"),rs.getInt("Likes"),
						rs.getInt("Dislikes"),formatter.parse(rs.getString("vrijemeKreiranja")),
						rs.getString("Naslov"),rs.getString("Sadrzaj")
						));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} catch (ParseException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static ArrayList<Objava> getAllObjave() {
	
		ArrayList<Objava> retVal = new ArrayList<>();
		retVal.addAll(selectAllDogadjaj());
		retVal.addAll(selectAllVijest());
		retVal.addAll(selectAllObjavaKorisnik());
		return retVal;
		
	}
	
	public static boolean updateLike(int id,int userID) {
		boolean retVal = false;
		int opcija=selectOpcijaLajkovano(id,userID);
		System.out.println(opcija);
		if(opcija==2) {
			retVal=updateObjavaSQL(id,userID,SQL_UPDATE_LIKE_DISLIKE,1);
		}
		else if(opcija==0) {		
			retVal=updateObjavaSQL(id,userID,SQL_UPDATE_LIKE,0);
		}
		else if(opcija==3) {
			retVal=updateObjavaSQL(id,userID,SQL_UPDATE_LIKE,1);
			
		}

		return retVal;
	}
	
	public static boolean updateDislike(int id,int userID) {
		boolean retVal = false;
		int opcija=selectOpcijaLajkovano(id,userID);
		if(opcija==2) {
			retVal=updateObjavaSQL(id,userID,SQL_UPDATE_DISLIKE_LIKE,1);
		}
		else if(opcija==0) {		
			retVal=updateObjavaSQL(id,userID,SQL_UPDATE_DISLIKE,0);
		}
		else if(opcija==3) {
			retVal=updateObjavaSQL(id,userID,SQL_UPDATE_DISLIKE,1);
			
		}

		return retVal;
	}
	
	public static boolean updateUnLike(int id,int userID) {
		boolean retVal = false;
		retVal=updateObjavaSQL(id,userID,SQL_UPDATE_UNLIKE,3);
		return retVal;
	}
	public static boolean updateUnDislike(int id,int userID) {
		boolean retVal = false;
		retVal=updateObjavaSQL(id,userID,SQL_UPDATE_UNDISLIKE,3);
		return retVal;
	}

	public static boolean insertLajkovano(int id,int userID,int opcija) {
		boolean result = false;
		Connection connection = null;
		Object values[] = {id,userID,opcija};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_LAJKOVANO, true, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean updateLajkovano(int id,int userID,int opcija) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = {opcija,id,userID};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_LAJKOVANO, false,
					values);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	
	
	public static int selectOpcijaLajkovano(int id,int userID) {
		int retVal = 0;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id,userID};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_LAJKOVANO, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal=rs.getInt("Opcija");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static boolean updateObjavaSQL(int id,int userID,String SQL,int opcija) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL, false,
					values);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		if(opcija==0) {
			retVal=insertLajkovano(id,userID,1);
		}
		else {
		retVal=updateLajkovano(id,userID,opcija);
		}
		return retVal;
	}
	
	public static boolean updateObjavaDelete(int id) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_DELETE, false,
					values);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	public static int insert(Objava objava) {
		int result = 0;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_OBJAVA, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next())
				objava.setId(generatedKeys.getInt(1));
			pstmt.close();
			result=objava.getId();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean insertObajvaKorisnik(ObjavaKorisnik objava) {
		boolean result = false;
		Connection connection = null;
		Object values[] = {objava.getId(), objava.getUser().getId(),objava.getSadrzaj(),objava.getTip()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_OBJAVA_KORISNIK, true, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}


	
}
