package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class App {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE seller " + "SET BaseSalary = BaseSalary + ? " + "WHERE (DepartmentId = ?)");

			st.setDouble(1, 20000.00);
			st.setInt(2, 2);
			
			
			int linhasAlteradas = st.executeUpdate();

			if (linhasAlteradas > 0) {
				System.out.println("Atualizado " + linhasAlteradas + " empregados  ");
			} else {
				System.out.println("Nenhum salário foi atualizado!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
