import DB.DBConnection;
import controller.LoginController;
import view.LoginView;
import DB.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        LoginView view = new LoginView();
        new LoginController(view, conn);
    }
}
