package LMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StateSVC {

    public static StateSVC stateSVC;

    public int updateState(String id, int newState) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            LoginSVC.connect();
            conn = LoginSVC.con;

            String sql = "UPDATE manager SET state = ? WHERE mid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newState);
            pstmt.setString(2, id);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("상태 변경 중 오류 발생");
            e.printStackTrace();
        } finally {
            LoginSVC.close(pstmt, conn);
        }

        return result;
    }
}