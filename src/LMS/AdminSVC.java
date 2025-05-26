package LMS;

import java.sql.*;
import java.time.LocalDate;
public class AdminSVC {
    static Connection con;

    public static void connect(){
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
        } catch (SQLException se){
            se.printStackTrace();
        }
    }

    public static void close(PreparedStatement pstmt, Connection conn) {
    }

    //1. 학생 등록
    public int insertStudent(int sno, String snm, String sid, String spw){
        connect();
        String sql = "INSERT INTO student (SNO, SNM, SID, SPW, SDATE, STATE) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, sno);
            ps.setString(2, snm);
            ps.setString(3, sid);
            ps.setString(4, spw);
            ps.setDate(5, Date.valueOf(LocalDate.now())); // 현재 날짜
            ps.setInt(6, 0); // 기본 상태값
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //2. 학생 목록
    public void printStudentList(){
        connect();
        String sql = "SELECT * FROM student";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("[학생 목록]");
            while (rs.next()) {
                System.out.printf("학번: %d, 이름: %s, 아이디: %s, 상태: %d, 등록일: %s\n",
                        rs.getInt("SNO"),
                        rs.getString("SNM"),
                        rs.getString("SID"),
                        rs.getInt("STATE"),
                        rs.getDate("SDATE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.관리자 목록
    public void printManagerList() {
        connect();
        String sql = "SELECT * FROM manager ORDER BY MID";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("[관리자 목록]");
            while (rs.next()) {
                System.out.printf("ID: %s, 이름: %s, 역할: %s, 상태: %d\n",
                        rs.getString("MID"),
                        rs.getString("MNM"),
                        rs.getString("ROLE"),
                        rs.getInt("STATE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}