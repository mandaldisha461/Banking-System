import java.sql.Connection;
import java.sql.PreparedStatement;
public class transfer {
    public static void Transfer(int fromId, int toId, double amount) {
        try (Connection con = DBUtil.getConnection()) {

            con.setAutoCommit(false);

            String debit = "UPDATE accounts SET balance = balance - ? WHERE account_id=?";
            String credit = "UPDATE accounts SET balance = balance + ? WHERE account_id=?";

            PreparedStatement ps1 = con.prepareStatement(debit);
            ps1.setDouble(1, amount);
            ps1.setInt(2, fromId);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(credit);
            ps2.setDouble(1, amount);
            ps2.setInt(2, toId);
            ps2.executeUpdate();

            String trans = "INSERT INTO transactions(account_id,transaction_type,amount,related_account) VALUES(?,?,?,?)";
            PreparedStatement ps3 = con.prepareStatement(trans);
            ps3.setInt(1, fromId);
            ps3.setString(2, "TRANSFER");
            ps3.setDouble(3, amount);
            ps3.setInt(4, toId);
            ps3.executeUpdate();

            con.commit();
            System.out.println("Transfer successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
