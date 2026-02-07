import java.sql.*;
import java.util.Random;
public class Register {
    public static void register(String name, String email, String password) {
        try (Connection con = DBUtil.getConnection()) {
            String userSql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password); 
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int userId = rs.getInt(1);
            long accNo = 1000000000L + new Random().nextInt(900000000);
            String accSql = "INSERT INTO accounts(user_id,account_number,balance,secret_code) VALUES(?,?,?,?)";
            PreparedStatement ps2 = con.prepareStatement(accSql);
            ps2.setInt(1, userId);
            ps2.setLong(2, accNo);
            ps2.setDouble(3, 0.0);
            ps2.setString(4, "1234");
            ps2.executeUpdate();
            System.out.println("Registration successful. Account No: " + accNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
