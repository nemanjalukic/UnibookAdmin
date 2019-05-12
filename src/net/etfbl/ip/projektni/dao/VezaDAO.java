package net.etfbl.ip.projektni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.etfbl.ip.projektni.dto.User;

public class VezaDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_UPDATE_VEZA = "UPDATE veza set Status=?,idUser=?,idUser1=? WHERE (idUser = ? && idUser1=?) || (idUser1=? && idUser=?)";
	private static final String SQL_IS_VEZA_EXIST = "SELECT * FROM veza WHERE (idUser = ? && idUser1=?) || (idUser1=? && idUser=?)";
	private static final String SQL_INSERT_VEZA = "INSERT INTO veza VALUES (?,?)";
	public static boolean updateVeza(int status,int userID,int freindID) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = {status,userID,freindID,userID,freindID,userID,freindID};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_VEZA, false,
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
	
	public static boolean updateDeleteVeza(int userID,int freindID) {
		
		return updateVeza(0,userID,freindID);
	}
	
	public static boolean addFreind(int userID,int freindID) {
		if(isVezaExist(userID,freindID)) {
			System.out.println("postoji");
			return updateVeza(2,userID,freindID);
		}
		else {
			System.out.println("nepostoji");
			return insertVeza(userID,freindID);
		}
	}
	
	public static boolean isVezaExist(int userID,int freindID) {
		boolean result = false;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { userID,freindID,userID,freindID };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_IS_VEZA_EXIST, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean insertVeza(int userID,int freindID) {
		boolean result = false;
		Connection connection = null;
		Object values[] = {userID,freindID};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_VEZA, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
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
}
