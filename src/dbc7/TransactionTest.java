package dbc7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {
	Connection con;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}

	public void connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/hi";
			con = DriverManager.getConnection(url, "root", "1111");
			
			//자동커밋여부 제어
			con.setAutoCommit(false);
			System.out.println("Connection Success!");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void insert() {
		connect();
		Statement stmt = null;
		boolean isOrderSuccess = false;
		try {
			stmt = con.createStatement();
			String sql = "INSERT INTO pay VALUES ('ccc', '001', 1000)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO pay VALUES ('aaa', '001', 1000)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO orderList VALUES ('001', 'p2014001')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO pay VALUES ('ccc', '001', 1000)";
			stmt.executeUpdate(sql);
			isOrderSuccess = true;
		} catch (SQLException se) {
			System.out.println("추가 실패");
			se.printStackTrace();
		} finally {
			System.out.println("isOrderSuccess: "+isOrderSuccess);
			if (isOrderSuccess) {
				try {
					con.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					con.rollback();
				} catch (Exception e) {
				}
			}
			try {
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		TransactionTest tt = new TransactionTest();
		tt.insert();
	}
}