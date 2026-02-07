import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreditService {

    public static void credit(int accountId, double amount) {
        try (Connection con = DBUtil.getConnection()) {

            con.setAutoCommit(false);

            String update = "UPDATE accounts SET balance = balance + ? WHERE account_id=?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setDouble(1, amount);
            ps.setInt(2, accountId);
            ps.executeUpdate();

            String trans = "INSERT INTO transactions(account_id,transaction_type,amount) VALUES(?,?,?)";
            PreparedStatement ps2 = con.prepareStatement(trans);
            ps2.setInt(1, accountId);
            ps2.setString(2, "CREDIT");
            ps2.setDouble(3, amount);
            ps2.executeUpdate();

            con.commit();
            System.out.println("Amount credited successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
