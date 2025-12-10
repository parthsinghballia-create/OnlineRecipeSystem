package models;


public class Recipe {
private int id;
private String name;
private String ingredients;
private String steps;
private int chefId;


public Recipe(int id, String name, String ingredients, String steps, int chefId) {
this.id = id;
this.name = name;
this.ingredients = ingredients;
this.steps = steps;
this.chefId = chefId;
}


public Recipe(String name, String ingredients, String steps, int chefId) {
this(-1, name, ingredients, steps, chefId);
}


// getters/setters
public int getId() { return id; }
public String getName() { return name; }
public String getIngredients() { return ingredients; }
public String getSteps() { return steps; }
public int getChefId() { return chefId; }
}