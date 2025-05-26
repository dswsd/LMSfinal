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

    public void updateScore(int tno, int score) {
        connect();
        String sql = "UPDATE testinfo SET total = ? WHERE tno = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, score);
            ps.setInt(2, tno);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("점수 업데이트 오류");
            e.printStackTrace();
        }
    }


    // 시험 정보 저장 및 시험번호(tno) 반환
    public int createTestInfo(int sno, int subno, int total) {
        connect();
        int tno = 0;
        try {
            // 1. 현재 가장 큰 tno 값을 가져옴
            String getMaxSql = "SELECT IFNULL(MAX(tno), 0) + 1 AS nextTno FROM testinfo";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getMaxSql);

            if (rs.next()) {
                tno = rs.getInt("nextTno");
            }

            rs.close();
            stmt.close();

            // 2. 새 tno 값으로 insert
            String insertSql = "INSERT INTO testinfo (tno, sno, subno, total) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertSql);
            ps.setInt(1, tno);
            ps.setInt(2, sno);
            ps.setInt(3, subno);
            ps.setInt(4, total);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.err.println("시험 정보 저장 실패");
            e.printStackTrace();
        }

        return tno;
    }
}
