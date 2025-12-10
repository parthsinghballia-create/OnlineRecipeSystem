package dao;

import exceptions.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.Recipe;
import util.DBConnection;

public class RecipeDAO {
    public void addRecipe(Recipe r) {
        String sql = "INSERT INTO recipes(name, ingredients, steps, chef_id) VALUES(?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getName());
            ps.setString(2, r.getIngredients());
            ps.setString(3, r.getSteps());
            ps.setInt(4, r.getChefId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding recipe", e);
        }
    }
    

    public List<Recipe> getAllRecipes() {
        String sql = "SELECT * FROM recipes";
        List<Recipe> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Recipe(rs.getInt("id"), rs.getString("name"), rs.getString("ingredients"),
                        rs.getString("steps"), rs.getInt("chef_id")));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error loading recipes", e);
        }
        
        return list;
    }

    public Optional<Recipe> findById(int id) {
        String sql = "SELECT * FROM recipes WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Recipe(rs.getInt("id"), rs.getString("name"), rs.getString("ingredients"),
                            rs.getString("steps"), rs.getInt("chef_id")));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding recipe", e);
        }
        return Optional.empty();
    }

    public List<Recipe> searchByName(String keyword) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        List<Recipe> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Recipe(rs.getInt("id"), rs.getString("name"), rs.getString("ingredients"),
                            rs.getString("steps"), rs.getInt("chef_id")));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error searching recipes", e);
        }
        return list;
    }
}