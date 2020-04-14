package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Statement;

import db.DB;

public class App {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			
			//Inserindo um vendedor e recebendo o numero de ID criado no banco
			/*
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId )"
					+ "VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Davi José");
			st.setString(2, "Davi@email.com");
			st.setDate(3, new java.sql.Date(sdf.parse("27/10/1980").getTime()));
			st.setDouble(4, 4500.00);
			st.setInt(5, 2);
	       */
			
			//teste de criar dois novos departamentos
			st = conn.prepareStatement(
					"INSERT into department (Name) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS
					);
						
			int linhasAlteradas = st.executeUpdate();
			
			if(linhasAlteradas > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Alterado vendedor com id= "+ id);
				}
			}else {
				System.out.println("Não foram alterados nenhuma linha do banco");
			}
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
