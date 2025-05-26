package LMS;

import java.sql.*;

public class LoginSVC {
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

    public static void close(PreparedStatement pstmt, Connection conn) {
    }

    //로그인
    public User login(String id, String passwd) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect();
            String sql = "SELECT * FROM manager WHERE mid = ? AND mpw = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, passwd);
            rs = ps.executeQuery();

            if (rs.next()) {
                int state = rs.getInt("state");

                // 상태가 0이면 로그인 불가
                if (state == 0) {
                    return null;
                }

                String name = rs.getString("mnm");
                String role = rs.getString("role");
                user = new User(id, passwd, name, role, state);
            }

        } catch (SQLException se) {
            System.out.println("로그인 중 오류 발생");
            se.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }


    // 회원가입 기능
    // LoginSVC.java 내부
    public int insertMember(String id, String passwd, String name) {
        PreparedStatement ps = null;
        int cnt = 0;

        try {
            connect();
            String defaultRole = "SUB";  // 역할은 무조건 SUB로 저장
            String sql = "INSERT INTO manager (mid, mpw, mnm, role, state) VALUES (?, ?, ?, ?, 1)";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, passwd);
            ps.setString(3, name);
            ps.setString(4, defaultRole);

            cnt = ps.executeUpdate();

            if (cnt > 0) {
                // MySQL 사용자 생성 및 권한 부여
                Statement stmt = con.createStatement();

                String mysqlUser = id;
                String createUser = "CREATE USER IF NOT EXISTS '" + mysqlUser + "'@'localhost' IDENTIFIED BY '" + passwd + "';";
                String grantSql = "GRANT SELECT, INSERT ON lms.* TO '" + mysqlUser + "'@'localhost';";

                stmt.execute(createUser);
                stmt.execute(grantSql);

                System.out.println("MySQL 사용자 및 권한 설정 완료 (SUB 권한): " + mysqlUser);
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("이미 존재하는 아이디입니다.");
        } catch (SQLException se) {
            System.out.println("회원가입 또는 권한 부여 중 오류 발생");
            se.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cnt;
    }

}