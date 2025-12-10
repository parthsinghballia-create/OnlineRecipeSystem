package util;


import java.sql.Connection;
import java.sql.Statement;


public class DBInit {
public static void init() {
String createUsers = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT, role TEXT);";
String createRecipes = "CREATE TABLE IF NOT EXISTS recipes (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, ingredients TEXT, steps TEXT, chef_id INTEGER, FOREIGN KEY(chef_id) REFERENCES users(id));";


try (Connection c = DBConnection.getConnection();
Statement s = c.createStatement()) {
s.execute(createUsers);
s.execute(createRecipes);


// Insert a default chef and customer if not exists
s.execute("INSERT OR IGNORE INTO users(id, username, password, role) VALUES(1,'chef','chef123','CHEF')");
s.execute("INSERT OR IGNORE INTO users(id, username, password, role) VALUES(2,'customer','cust123','CUSTOMER')");
} catch (Exception e) {
e.printStackTrace();
}
}
}