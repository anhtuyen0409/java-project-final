package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLServerService {
	//kết nối sql
    Connection conn = null;
	PreparedStatement prestatement = null; //dùng để thực hiện insert, update, delete
	ResultSet result = null; //dùng để truyền dữ liệu
	public SQLServerService() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //thư viện để tương tác với sql server
			String connectionUrl=
					"jdbc:sqlserver://DESKTOP-17461AU:1433;databaseName=DoAnJava;integratedSecurity=true;"; //dường dẫn
			conn= DriverManager.getConnection(connectionUrl); //trả về đối tượng đang kết nối csdl
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
