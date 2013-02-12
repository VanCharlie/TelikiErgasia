import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class WareHouseFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
try{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
}
catch (Exception e){
	e.printStackTrace();
}
Connection conn=null;
Statement stmt=null;
ResultSet rs=null;
try{
	//setup driver
	String connectionUrl="jdbc:mysql://localhost:3306/warehouse";
	String username="root";
	String password="12345";
	conn=DriverManager.getConnection(connectionUrl,username,password);
	stmt=conn.createStatement();
	rs=stmt.executeQuery("select * from warehousefactory");
	
	while(rs.next()){
		String id = rs.getString("itemID");
		String title = rs.getString("itemTitle");
		String desc = rs.getString("itemDesc");
		String price = rs.getString("itemPrice");
		System.out.println("ID: " + id + ", Title: " + title
		+ ", Description: " + desc + "Price:"+price);
	}
}
catch(Exception e){
	e.printStackTrace();
}
finally{
	try{
		if(rs !=null)
			rs.close();
	}
	catch (SQLException e){
		e.printStackTrace();
	}
	try{
		if(stmt !=null)
			stmt.close();
	}
	catch (SQLException e){
		e.printStackTrace();
	}
	try{
		if(extracted(conn) !=null)
			extracted(conn).close();
	}
	catch (SQLException e){
		e.printStackTrace();
	}
}
	}

	private static Connection extracted(Connection conn) {
		return conn;
	}

}
