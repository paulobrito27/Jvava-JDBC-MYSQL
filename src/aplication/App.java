package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;


public class App {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			
			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE from department WHERE Id = ?");
			
			st.setInt(1, 2);
			int linhasAfetadas = st.executeUpdate();
			System.out.println("linhas afetadas = "+ linhasAfetadas);
			
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
