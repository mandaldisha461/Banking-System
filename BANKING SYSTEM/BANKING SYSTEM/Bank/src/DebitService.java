import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DebitService {

    public static void debit(int accountId, double amount) {
        try (Connection con = DBUtil.getConnection()) {

            String check = "SELECT balance FROM accounts WHERE account_id=?";
            PreparedStatement ps = con.prepareStatement(check);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            rs.next();

            if (rs.getDouble("balance") < amount) {
                System.out.println("Insufficient balance");
                return;
            }

            con.setAutoCommit(false);

            String update = "UPDATE accounts SET balance = balance - ? WHERE account_id=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setDouble(1, amount);
            ps2.setInt(2, accountId);
            ps2.executeUpdate();

            String trans = "INSERT INTO transactions(account_id,transaction_type,amount) VALUES(?,?,?)";
            PreparedStatement ps3 = con.prepareStatement(trans);
            ps3.setInt(1, accountId);
            ps3.setString(2, "DEBIT");
            ps3.setDouble(3, amount);
            ps3.executeUpdate();

            con.commit();
            System.out.println("Amount debited");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
