import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login {

    public static boolean authenticate(String email, String password) {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
