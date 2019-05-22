package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String banco = "jdbc:postgresql://localhost:5432/crudjsp?autoReconnect=true";
	private static String usuario = "postgres";
	private static String senha = "123456";
	private static Connection connection = null;
	
	static {
		
		conectar();
		
	}
	
	private SingleConnection() {
		
		conectar();
		
	}
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, usuario, senha);
				connection.setAutoCommit(false);
				
			}
			
		} catch (Exception e) {
			
			throw new RuntimeException("Erro ao conectar com banco de dados!");
			
		}
		
	}
	
	public static Connection getConnection() {
		
		return connection;
		
	}
	
}
