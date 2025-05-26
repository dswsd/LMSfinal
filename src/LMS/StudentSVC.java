package LMS;

import java.sql.*;

public class StudentSVC {
    Connection con;

    //초기화 블록, sql드라이버 설치
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("sql 드라이버 설치 오류");
        }
    }

    //root계정 접속
    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
        } catch (SQLException se) {
            System.err.println("sql 계정 접속 오류");
        }
    }

    //DB와 입력받은 id, pw 정보 confirm
    //없을 시 null값 보냄
    public User login(String sid, String password) {
        User user = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connect();
            stmt = con.createStatement();
            String sql = "SELECT * FROM student WHERE sid = '" + sid
                    + "' AND " + "spw = '" + password + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int sno = rs.getInt("sno");
                String snm = rs.getString("snm");
                user = new User(sno, snm);
                System.out.println(sno + snm);
            }
        } catch (SQLException se) {
            System.err.println("sql 데이터 조회 오류");
            //DB 종료 및 예외 확인
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
                System.err.println("DB가 정상적으로 종료되지 않음");;
            }
        }
        return user;//
    }

    //회원가입 메뉴 (insert)
    public int insertMember(String id, String passwd, String name) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }


}