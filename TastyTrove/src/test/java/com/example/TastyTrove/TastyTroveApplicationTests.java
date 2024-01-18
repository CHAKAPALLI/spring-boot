package com.example.TastyTrove;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testing Recipe Application")
@SpringBootTest
class TastyTroveApplicationTests {
    String recipeType = "";
    String ingredientType = "";
    Recipe myRecipe;
    private String randomRecipeCall() {
        ArrayList<String> recipes = new ArrayList<>();
        recipes.add("northIndian");
        recipes.add("southIndian");
        recipes.add("chinese");
        Random random = new Random();
        return recipes.get(random.nextInt(recipes.size()));
    }
    private String randomIngredientCall() {
        ArrayList<String> ingredient = new ArrayList<>();
        ingredient.add("Lentils");
        ingredient.add("Rice");
        ingredient.add("Wheat");
        Random random = new Random();
        return ingredient.get(random.nextInt(ingredient.size()));
    }
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");


    @BeforeAll
    public void setup() {
        recipeType = randomRecipeCall();
        System.out.println("Selected recipe is -> " + recipeType);
        ingredientType = randomIngredientCall();
        System.out.println("Selected ingredient is -> "+ingredientType);
    }

    @Order(1)
    @DisplayName("Testing Bean Creation and Setter Injection for RecipeType")
    @Test
    void RecipeBeanGeneration() {
        try {
            myRecipe = (Recipe) context.getBean(recipeType + ingredientType);
        } catch (RuntimeException e) {
            throw new RuntimeException("Bean of type " + recipeType + ingredientType + " could not be found, check bean Id, it should be of the form: " + recipeType+ingredientType);
        }
    }

    @Order(2)
    @DisplayName("Testing the Recipe output based on the type  of selections")
    @Test
    void testOutput(){
        Assertions.assertNotNull(myRecipe, " Recipe Bean is not generated properly to display the output");
        myRecipe.setUserName("John Doe");
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream outStream = System.out;
        System.setOut(new PrintStream(out));
        myRecipe.getDetails();
        System.setOut(outStream);
        System.out.println(out.toString());
        Assertions.assertTrue(validateOutput("John Doe",out.toString()),"getDetails() method doesn't have user name.");
        Assertions.assertFalse(out.toString().contains("null"), " Error in the getDetails() function");
    }

    boolean validateOutput(String name ,String output){
        return !output.isBlank() && output.contains(name);
    }

}
