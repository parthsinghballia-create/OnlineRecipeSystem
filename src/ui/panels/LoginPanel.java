package ui.panels;


import models.User;
import dao.UserDAO;
import ui.MainFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class LoginPanel extends JPanel {
private JTextField username = new JTextField(15);
private JPasswordField password = new JPasswordField(15);
private MainFrame parent;


public LoginPanel(MainFrame parent) {
this.parent = parent;
setLayout(new GridBagLayout());
GridBagConstraints c = new GridBagConstraints();
c.insets = new Insets(5,5,5,5);


add(new JLabel("Username:"), c);
add(username, c);
add(new JLabel("Password:"), c);
add(password, c);
JButton loginBtn = new JButton("Login");
loginBtn.addActionListener(this::login);
add(loginBtn, c);
}


private void login(ActionEvent e) {
String user = username.getText().trim();
String pass = new String(password.getPassword());
UserDAO dao = new UserDAO();
dao.findByUsername(user).ifPresentOrElse(u -> {
if (u.getPassword().equals(pass)) parent.showRoleScreen(u);
else JOptionPane.showMessageDialog(this, "Invalid credentials");
}, () -> JOptionPane.showMessageDialog(this, "User not found"));
}
}