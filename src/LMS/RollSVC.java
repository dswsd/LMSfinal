package LMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RollSVC {


    public int updateRole(String id, String newRole) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            LoginSVC.connect();
            conn = LoginSVC.con;

            String sql = "UPDATE manager SET role = ? WHERE mid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newRole);
            pstmt.setString(2, id);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("역할 변경 중 오류 발생");
            e.printStackTrace();
        } finally {
            LoginSVC.close(pstmt, conn);
        }

        return result;
    }
}