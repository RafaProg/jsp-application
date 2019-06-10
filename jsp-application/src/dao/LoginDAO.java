package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class LoginDAO {

	private Connection conexao;

	public LoginDAO() {

		conexao = SingleConnection.getConnection();

	}

	public boolean validarLogin(String usuario, String senha) throws Exception {
		
		String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, usuario);
		stmt.setString(2, senha);
		ResultSet resultSet = stmt.executeQuery();
		
		if (resultSet.next()) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

}
