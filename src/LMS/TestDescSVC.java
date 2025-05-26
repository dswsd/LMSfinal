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

    public int getCorrectAnswer(int eno) {
        connect();
        int correct = -1;
        String sql = "SELECT ans FROM answer WHERE eno = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                correct = rs.getInt("ans");
            }
        } catch (SQLException e) {
            System.err.println("정답 조회 오류");
            e.printStackTrace();
        }
        return correct;
    }


    public int insertAnswer(int tno, int eno, int dab) {
        connect();
        int result = 0;
        String sql = "INSERT INTO testdesc (tno, eno, dab) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tno);
            ps.setInt(2, eno);
            ps.setInt(3, dab);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("답안 저장 오류");
            e.printStackTrace();
        }

        return result;
    }

}
