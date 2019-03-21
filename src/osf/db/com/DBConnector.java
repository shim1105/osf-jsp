package osf.db.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnector {
	private static final String URL=":jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="osfu";
	private static final String PASSWORD="12345678";
	private static final String CLASS_NAME= "oracle.jdbc.OracleDriver";
	private static Connection con=null;
	public static Connection open() {
		if(con==null) {
			try {
				Class.forName(CLASS_NAME);
				con= DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	public static void close() {
		if(con!=null) {
			try {
				if(!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con= null;
		}
	}

}
