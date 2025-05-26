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

    public List<Question> getQuestions() {
        connect();
        List<Question> list = new ArrayList<>();
        String sql = "SELECT ENO, EMUN, EJIMUN, ESAMP FROM exeinfo LIMIT 5"; // ← 여기 바뀐 부분

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int eno = rs.getInt("ENO");
                String emun = rs.getString("EMUN");
                String ejimun = rs.getString("EJIMUN");
                String esamp = rs.getString("ESAMP");

                list.add(new Question(eno, emun, ejimun, esamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
