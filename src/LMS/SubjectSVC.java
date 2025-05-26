package LMS;

import java.sql.*;

public class SubjectSVC {
    Connection con;

    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
        } catch (SQLException se) {
            System.err.println("DB 연결 오류");
        }
    }

    public String getSubjectTitle(int subno) {
        connect();
        String title = null;
        String sql = "SELECT subnm, subyear, subterm FROM subinfo WHERE subno = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, subno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("subnm");
                int year = rs.getInt("subyear");
                int term = rs.getInt("subterm");

                title = year + "년 " + term + "학기 " + name + " 시험";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return title;
    }
}
