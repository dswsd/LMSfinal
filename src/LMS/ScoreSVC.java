package LMS;

import java.sql.*;

public class ScoreSVC {
    Connection con;

    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
        } catch (SQLException e) {
            System.err.println("DB 연결 오류");
            e.printStackTrace();
        }
    }

    // 시험 응시 이력 확인
    public boolean hasTakenTest(int sno, int subno) {
        connect();
        String sql = "SELECT COUNT(*) FROM testinfo WHERE sno = ? AND subno = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sno);
            ps.setInt(2, subno);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // 이미 응시했다면 true 반환
                }
            }
        } catch (SQLException e) {
            System.err.println("시험 응시 이력 확인 중 오류 발생");
            e.printStackTrace();
        }

        return false;
    }

    // 시험 정보 저장 및 시험번호(tno) 반환
    public int createTestInfo(int sno, int subno, int total) {
        connect();
        int tno = 0;
        String sql = "INSERT INTO testinfo (sno, subno, total) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, sno);
            ps.setInt(2, subno);
            ps.setInt(3, total);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tno = rs.getInt(1);  // 자동 생성된 시험번호 반환
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("시험 정보 저장 실패");
            e.printStackTrace();
        }

        return tno;
    }
}
