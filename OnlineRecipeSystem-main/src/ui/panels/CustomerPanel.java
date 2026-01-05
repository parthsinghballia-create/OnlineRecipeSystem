package ui.panels;


import models.Recipe;
import service.RecipeService;


import javax.swing.*;
import java.awt.*;
import java.util.List;


public class CustomerPanel extends JPanel {
private RecipeService service = new RecipeService();
private JList<String> list;
private JTextField searchField = new JTextField(20);


public CustomerPanel(JFrame parent) {
setLayout(new BorderLayout());
JPanel top = new JPanel();
JButton searchBtn = new JButton("Search");
searchBtn.addActionListener(a -> doSearch());
top.add(searchField);
top.add(searchBtn);
add(top, BorderLayout.NORTH);


list = new JList<>();
add(new JScrollPane(list), BorderLayout.CENTER);


refreshList();
}


private void doSearch() {
String q = searchField.getText().trim();
List<Recipe> results = service.search(q);
DefaultListModel<String> m = new DefaultListModel<>();
for (Recipe r : results) m.addElement(r.getName() + " - " + r.getIngredients());
list.setModel(m);
}


private void refreshList() {
List<Recipe> recipes = service.getAll();
DefaultListModel<String> m = new DefaultListModel<>();
for (Recipe r : recipes) m.addElement(r.getName());
list.setModel(m);
}
}