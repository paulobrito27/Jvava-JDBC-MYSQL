package aplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbIntegrityException;


public class App {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		
		try {
			
			conn = DB.getConnection();
			
			conn.setAutoCommit(false); //Impede que sejam realizadas transações até o progtramador habilitar
			
			
			st = conn.createStatement();
			
			int a = st.executeUpdate("UPDATE seller SET BaseSalary = 2000 WHERE DepartmentId = 2");
			int b = st.executeUpdate("UPDATE seller SET BaseSalary = 1000 WHERE DepartmentId = 1");
			
			
			conn.commit();//Habilita a realização das transações, evitando que parte das transações não ocorra.
			
			
			System.out.println("a->" + a);
			System.out.println("b->" + b);
			
		}catch(SQLException e) {
			
			//Aqui é feito um tratamento para que o banco volte a operação caso algo já tenha sido alterado
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
