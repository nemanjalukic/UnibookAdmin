package net.etfbl.ip.projektni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.ip.projektni.dto.Fakultet;

public class FakultetDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_FAKULTET = "SELECT * FROM fakultet";
	public static ArrayList<Fakultet> selectAllFakutet() {
		ArrayList<Fakultet> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;

		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_FAKULTET, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new Fakultet(rs.getInt(1), rs.getString(2)));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

}
