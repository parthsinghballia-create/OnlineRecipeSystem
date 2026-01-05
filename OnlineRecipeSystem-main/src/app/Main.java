package app;


import javax.swing.*;
import ui.MainFrame;
import util.DBInit;


public class Main {
public static void main(String[] args) {
// Initialize DB (create tables if needed)
DBInit.init();


SwingUtilities.invokeLater(() -> {
new MainFrame().setVisible(true);
});
}
}