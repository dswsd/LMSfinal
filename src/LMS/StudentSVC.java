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
    public int insertStudent(int sno, String snm, String sid, String spw) {
        connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cnt = 0;

        try {
            // 입력은 모두 다 받은 상태

            // 1. 아이디 중복 확인
            String checkSql = "SELECT sid FROM student WHERE sid = ?";
            ps = con.prepareStatement(checkSql);
            ps.setString(1, sid);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 이미 존재하는 아이디라면
                System.out.println("⚠ 아이디가 중복됩니다. 다른 아이디를 입력해주세요.");
                return 0;
            }

            // 자원 정리
            rs.close();
            ps.close();

            // 2. 학번과 이름이 맞고, 아직 가입 안 한 경우에만 등록
            String updateSql = "UPDATE student SET sid = ?, spw = ?, sdate = CURDATE(), state = 1 " +
                    "WHERE sno = ? AND snm = ? AND state = 0";

            ps = con.prepareStatement(updateSql);
            ps.setString(1, sid);
            ps.setString(2, spw);
            ps.setInt(3, sno);
            ps.setString(4, snm);

            cnt = ps.executeUpdate();

            if (cnt == 0) {
                System.out.println("학번과 이름이 맞지 않거나 이미 가입된 상태입니다.");
            }

        } catch (SQLException e) {
            System.out.println("회원가입 처리 중 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cnt;
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
    public void checkScore(int sno, int subyear, int subterm) {
        connect();

        String sql =
                "SELECT a.ENO, " +
                        "CASE WHEN d.DAB = a.ANS THEN 'O' ELSE 'X' END AS res " +
                        "FROM testdesc d " +
                        "JOIN testinfo i ON d.TNO = i.TNO " +
                        "JOIN answer a ON d.ENO = a.ENO " +
                        "WHERE i.SNO = ?";

        int score = 0;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sno);
            ResultSet rs = ps.executeQuery();

            System.out.printf("\n%d년 %d학기 성적확인\n", subyear, subterm);
            System.out.println("------------------------------");
            while (rs.next()) {
                int eno = rs.getInt("ENO");
                String result = rs.getString("res");

                if (result.equals("O")) score += 20;

                System.out.printf("%d번 문제: %s\n", eno, result);
            }

            System.out.println("총점: " + score + "점");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


