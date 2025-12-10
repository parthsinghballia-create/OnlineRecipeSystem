package util;


import service.RecipeService;


public class RecipeLoader extends Thread {
private RecipeService service = new RecipeService();


@Override
public void run() {
synchronized (service) {
service.getAll(); // load recipes in background
try { Thread.sleep(200); } catch (InterruptedException ignored) {}
}
}
}