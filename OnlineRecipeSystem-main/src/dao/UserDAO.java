package dao;


import models.User;
import util.DBConnection;
import exceptions.DatabaseException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserDAO {
public Optional<User> findByUsername(String username) {
String sql = "SELECT * FROM users WHERE username = ?";
try (Connection c = DBConnection.getConnection();
PreparedStatement ps = c.prepareStatement(sql)) {
ps.setString(1, username);
try (ResultSet rs = ps.executeQuery()) {
if (rs.next()) {
User u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), User.Role.valueOf(rs.getString("role")));
return Optional.of(u);
}
}
} catch (SQLException e) {
throw new DatabaseException("Error fetching user", e);
}
return Optional.empty();
}
}