package jdbcProject;
import java.sql.*;
public class Test {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/cashify","root","Root@123");
		
		

	}

}
