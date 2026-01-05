package ui;


import dao.UserDAO;
import models.User;
import service.RecipeService;
import ui.panels.ChefPanel;
import ui.panels.CustomerPanel;
import ui.panels.LoginPanel;


import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
private CardLayout cards = new CardLayout();
private JPanel container = new JPanel(cards);
private RecipeService recipeService = new RecipeService();


public MainFrame() {
setTitle("Online Recipe System");
setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(900,600);
setLocationRelativeTo(null);


LoginPanel login = new LoginPanel(this);
container.add(login, "login");


container.add(new ChefPanel(this), "chef");
container.add(new CustomerPanel(this), "customer");


add(container);
}


public void showRoleScreen(User user) {
if (user.getRole() == User.Role.CHEF) cards.show(container, "chef");
else cards.show(container, "customer");
}
}