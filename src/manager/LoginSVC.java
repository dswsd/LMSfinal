package manager;

import manager.User;

import java.sql.*;

public  class LoginSVC {
	static Connection con;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/lms";
			con = DriverManager.getConnection(url, "root", "1111");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	//로그인 , 나의 정보 메뉴
	public static User login(String id, String passwd) {
		System.out.println("Id:::::::" + id);
		System.out.println("passwd:::::::" + passwd);
		User user = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect();
			stmt = con.createStatement();
			String sql = "SELECT * FROM manager WHERE mid = '" + id
					+ "' AND " + "mpw = '" + passwd + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("mid");
				String pw = rs.getString("mpw");
				String job = rs.getString("mnm");
				String role = rs.getString("role");
				String state = rs.getString("state");
//				user = new User(id, passwd, name, tel);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try { rs.close(); stmt.close(); con.close(); }
			catch (Exception e) { e.printStackTrace();}
		}
		return user;
	}

	//회원가입 메뉴 (insert)
	public int insertMember(String id, String  passwd, String name) {
		connect();
		PreparedStatement ps = null;
		String sql = "insert into member (mid, mpw, mnm) values (?, ?, ?)";
		int cnt = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, passwd);
			ps.setString(3, name);
			cnt = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//회원탈퇴 (delete)
	public int deleteMember(String id) {
		connect();
		PreparedStatement ps = null;
		String sql = "delete from member where mid= ?";
		int cnt = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			cnt = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}