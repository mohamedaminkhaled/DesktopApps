package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBinfo {
	
	private static String username="mohamed";
	private static String password="amin";
	private static String con_string="jdbc:mysql://localhost/pharmacy";
	
	public static Connection connDB() throws SQLException {
		return DriverManager.getConnection(con_string, username, password);
	}
}
