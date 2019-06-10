package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UsuarioBean;
import connection.SingleConnection;

public class UsuarioDAO {

	public Connection conexao;
	
	public UsuarioDAO() {
		
		conexao = SingleConnection.getConnection();
		
	}
	
	public void salvarUsuario(UsuarioBean usuario) {
		
		try {
			
			String sql = "INSERT INTO usuarios(usuario, senha) values(?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();
			conexao.commit();
			
		} catch (Exception e) {
			try {
				conexao.rollback();
				e.printStackTrace();
			} catch (SQLException e2) {
				
				e2.printStackTrace();
				
			}
		}
		
	}
	
	public List<UsuarioBean> listarUsuarios() throws SQLException {
		
		List<UsuarioBean> listaUsuarios = new ArrayList<UsuarioBean>();
		
		String sql = "SELECT * FROM usuarios";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(rs.getLong("id"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			listaUsuarios.add(usuario);
			
		}
		
		return listaUsuarios;
		
	}
	
	public void deletarUsuario(String usuario) {
		
		String sql = "DELETE FROM usuarios WHERE usuario = ?";
		try {

			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario);
			stmt.execute();
			conexao.commit();

		} catch (Exception e) {
			
			try {
				
				conexao.rollback();
				e.printStackTrace();
				
			} catch (Exception e2) {
				
				e2.printStackTrace();
				
			}
			
		}
		
	}
	
	public UsuarioBean buscarUsuario(Long id) {
		
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			UsuarioBean usuario = new UsuarioBean();
			
			while (rs.next()) {
				
				
				usuario.setId(rs.getLong("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				
			}
			
			return usuario;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	public void atualizar(UsuarioBean usuario) {
		
		try {
			
			String sql = "UPDATE usuarios set usuario = ?, senha = ? WHERE id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setLong(3, usuario.getId());
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();
			conexao.commit();
			
		} catch (Exception e) {
			
			try {
				
				e.printStackTrace();
				conexao.rollback();
				
			} catch (Exception e2) {
				
				e2.printStackTrace();
				
			}
			
		}
		
	}
	
}
