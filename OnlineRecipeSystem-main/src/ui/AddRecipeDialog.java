package ui;


import java.awt.*;
import javax.swing.*;
import models.Recipe;
import service.RecipeService;


public class AddRecipeDialog extends JDialog {
private JTextField name = new JTextField(20);
private JTextArea ingredients = new JTextArea(5,20);
private JTextArea steps = new JTextArea(8,20);
private boolean saved = false;
private RecipeService service = new RecipeService();


public AddRecipeDialog(Window owner) {
super(owner, "Add Recipe", ModalityType.APPLICATION_MODAL);
setLayout(new BorderLayout());
JPanel form = new JPanel(new GridLayout(0,1));
form.add(new JLabel("Name:")); form.add(name);
form.add(new JLabel("Ingredients (comma separated):")); form.add(new JScrollPane(ingredients));
form.add(new JLabel("Steps:")); form.add(new JScrollPane(steps));
add(form, BorderLayout.CENTER);


JButton save = new JButton("Save");
save.addActionListener(a -> doSave());
add(save, BorderLayout.SOUTH);


pack();
setLocationRelativeTo(owner);
}


private void doSave() {
String n = name.getText().trim();
if (n.isEmpty()) { JOptionPane.showMessageDialog(this, "Provide a name"); return; }
Recipe r = new Recipe(n, ingredients.getText().trim(), steps.getText().trim(), 1); // chefId=1 default
service.addRecipe(r);
saved = true;
dispose();
}


public boolean isSaved() { return saved; }
}