package controller;

import DAO.UserDAO;
import model.NhanVien_m;
import view.LoginView;
import view.mainframe;

import javax.swing.*;
import java.sql.Connection;

public class LoginController {

    private LoginView view;
    private UserDAO userDAO;

    public LoginController(LoginView view, Connection conn) {
        this.view = view;
        this.userDAO = new UserDAO(conn);

        view.addLoginListener(e -> login());
    }

    private void login() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        NhanVien_m nv = userDAO.login(username, password);

        if (nv != null) {
            JOptionPane.showMessageDialog(view,
                    "Xin chào " + nv.getTenNV());

            view.dispose();
            new mainframe(nv.getChucVu());

        } else {
            JOptionPane.showMessageDialog(view,
                    "Sai tài khoản hoặc mật khẩu");
        }
    }
}
