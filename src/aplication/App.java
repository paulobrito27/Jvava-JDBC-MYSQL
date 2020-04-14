package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class App {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId )"
					+ "VALUES (?,?,?,?,?)");
			
			st.setString(1, "Paulo José");
			st.setString(2, "Paulo@email.com");
			st.setDate(3, new java.sql.Date(sdf.parse("27/10/1980").getTime()));
			st.setDouble(4, 4500.00);
			st.setInt(5, 2);

			int linhasAlteradas = st.executeUpdate();

			System.out.println("Feito, alteradas " + linhasAlteradas + " linhas");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
