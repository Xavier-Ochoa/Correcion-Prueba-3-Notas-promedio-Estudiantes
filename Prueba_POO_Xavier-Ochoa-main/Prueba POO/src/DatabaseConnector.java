import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://bx8je5t9vejvifix4i2d-mysql.services.clever-cloud.com:3306/bx8je5t9vejvifix4i2d";
    private static final String USER = "undfrhfskgmrrxaj";
    private static final String PASSWORD = "cBkTasrc64TFr10R3eyO";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean validarUsuarioPorCorreo(String username, String password) {
        String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
