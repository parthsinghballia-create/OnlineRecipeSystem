package service;


import dao.RecipeDAO;
import models.Recipe;


import java.util.List;


public class RecipeService implements Searchable<Recipe> {
private RecipeDAO dao = new RecipeDAO();


@Override
public List<Recipe> search(String keyword) {
return dao.searchByName(keyword);
}


public void addRecipe(Recipe r) { dao.addRecipe(r); }
public List<Recipe> getAll() { return dao.getAllRecipes(); }
}