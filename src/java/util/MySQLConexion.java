package util;
import java.sql.*;
public class MySQLConexion {
	public static Connection getConexion(){ 
		Connection con = null;
		try { 
                Class.forName("com.mysql.jdbc.Driver"); 
		//String url = "jdbc:mysql://servidorgoclass.mysql.database.azure.com:3306/goclass?useSSL=true&requireSSL=false"; 
		//String usr = "josue@servidorgoclass";
		//String psw = "Qwer5256"; 
                String url = "jdbc:mysql://localhost/goclass"; 
		String usr = "root";
		String psw = "";
		con = DriverManager.getConnection(url,usr,psw); 
		System.out.println("conexion ok");
		} catch (ClassNotFoundException ex)
		{ System.out.println("No hay Driver!!"+ex.getMessage()); } 
		catch (SQLException ex) { System.out.println("Error con la BD "); }
		return con; 
		}
}

