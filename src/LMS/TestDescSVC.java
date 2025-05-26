package LMS;

import java.sql.*;

public class TestDescSVC {
    Connection con;

    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
        } catch (SQLException se) {
            System.err.println("DB 연결 오류");
        }
    }

    public int insertAnswer(int tno, int eno, String answer) {
        connect();
        int result = 0;
        String sql = "INSERT INTO testdesc (tno, eno, answer) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tno);
            ps.setInt(2, eno);
            ps.setString(3, answer);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("답안 저장 오류");
            e.printStackTrace();
        }

        return result;
    }
}
