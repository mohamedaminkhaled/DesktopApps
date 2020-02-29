package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadData {

	public static void main(String[] args){
		
		Connection conn;
		ResultSet rs;
		Statement st;
		String username="mohamed";
		String password="amin";
		String con_string_1="jdbc:mysql://localhost/muhammedessa";
		String con_string_2="jdbc:mysql://localhost/muhammedessahameed";
		
		try {
			conn=DriverManager.getConnection(con_string_2, 
					username, password);
			st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs=st.executeQuery("SELECT `employeeId`,`firstName`,`lastName` FROM `employees`");
			//rs.last();
		
			System.out.println("Connected!\n");
			rs.absolute(0);
			System.out.println(rs.getRow());
			
			StringBuilder strb=new StringBuilder();
			
//				System.out.println("job: "+rs.getInt("jobsId")+
//						"-> "+rs.getString("jobsTitle")+
//						" -> "+rs.getInt("salary"));
			
			//using outer class
//			ShowData.showData(rs, strb);
//			System.out.println("using outer class\n");					
//			System.out.println(strb.toString());	
//			
////				//using StringBuilder
////				show(rs, strb);
////				System.out.println("using StringBuilder\n");
////				System.out.println(strb.toString());
//			
			System.out.println("------------------------\n");
			rs.first();
			System.out.println("First employee is: "+rs.getString("firstName"));
			rs.last();
			System.out.println("Last employee is: "+rs.getString("lastName"));
			rs.absolute(2);
			System.out.println("Employee at row 2 is: "+rs.getString("firstName"));
			
		} catch (SQLException e) {
			errException(e);
		}
	}

//	private static void show(ResultSet rs, StringBuilder strb) throws SQLException {
//		while(rs.next()) {
//		strb.append("job: "+rs.getInt("jobsId"));
//		strb.append("-> "+rs.getString("jobsTitle"));
//		strb.append(" -> "+rs.getInt("salary")+"\n\n");
//		}
//	}
//	
	public static void errException(SQLException e) {
		System.out.println("Error: "+e.getMessage());
		System.out.println("code: "+e.getErrorCode());
		System.out.println("state: "+e.getSQLState());
		System.out.println("message: "+e.getLocalizedMessage());
		
	}

	
	
	
}
