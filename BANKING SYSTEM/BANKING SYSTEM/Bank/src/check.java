import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class check {

    public static void checkBalance(int accountId) {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT balance FROM accounts WHERE account_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Current Balance: " + rs.getDouble("balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
