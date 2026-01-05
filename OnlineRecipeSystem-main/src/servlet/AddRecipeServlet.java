package servlet;

import dao.RecipeDAO;
import models.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addRecipe")
public class AddRecipeServlet extends HttpServlet {

    private RecipeDAO recipeDAO;

    @Override
    public void init() throws ServletException {
        recipeDAO = new RecipeDAO(); // DAO reused from GUI project
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try {
            String name = request.getParameter("name");
            String ingredients = request.getParameter("ingredients");
            String steps = request.getParameter("steps");

            Recipe recipe = new Recipe(name, ingredients, steps);
            recipeDAO.addRecipe(recipe);

            out.println("Recipe added successfully via Servlet");

        } catch (Exception e) {
            out.println("Error while adding recipe");
            e.printStackTrace();
        }
    }
}
