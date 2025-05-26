package LMS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestSVC {
    Connection con;

    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
        } catch (SQLException se) {
            System.err.println("DB 연결 오류");
        }
    }

    public List<Question> getQuestionsWithAnswer(int subno) {
        connect();
        List<Question> list = new ArrayList<>();
        String sql = "SELECT eno, emun, ejimun, esamp, eans FROM exeinfo WHERE subno = ? LIMIT 5";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, subno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("eno"),
                        rs.getString("emun"),
                        rs.getString("ejimun"),
                        rs.getString("esamp"),
                        rs.getString("eans") // 정답 포함
                );
                list.add(q);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

