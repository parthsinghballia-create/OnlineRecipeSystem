package ui.panels;


import java.awt.*;
import java.util.List;
import javax.swing.*;
import models.Recipe;
import service.RecipeService;
import ui.AddRecipeDialog;
import ui.MainFrame;


public class ChefPanel extends JPanel {
private RecipeService service = new RecipeService();
private MainFrame parent;
private JList<String> list;


public ChefPanel(MainFrame parent) {
this.parent = parent;
setLayout(new BorderLayout());
JPanel top = new JPanel();
JButton add = new JButton("Add Recipe");
add.addActionListener(a -> openAddDialog());
top.add(add);
add(top, BorderLayout.NORTH);


list = new JList<>();
add(new JScrollPane(list), BorderLayout.CENTER);


refreshList();
}


private void openAddDialog() {
AddRecipeDialog dlg = new AddRecipeDialog(SwingUtilities.getWindowAncestor(this));
dlg.setVisible(true);
if (dlg.isSaved()) refreshList();
}


private void refreshList() {
List<Recipe> recipes = service.getAll();
DefaultListModel<String> m = new DefaultListModel<>();
for (Recipe r : recipes) m.addElement(r.getName() + " (id:" + r.getId() + ")");
list.setModel(m);
}
}