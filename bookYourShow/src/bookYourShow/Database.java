package bookYourShow;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Database {

    Connection con = null;

    public static Connection dataConnector() {
        try {
            String url = "jdbc:mysql://localhost:3309/bookyourshow?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "MyCatLikesToJumpHigh1234";
            Connection con = DriverManager.getConnection(url, user, password);
            return con;

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error in Database Program -> " + e);
        }
        return null;
    }
}
