package day14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Recipes {
    public Elf elf1;
    public Elf elf2;
    public List<Integer> recipes;
    public LinkedList<Integer> recipesTail;
    public List<Integer> patternList;
    public Recipes(String inputPattern) {
        recipes = new ArrayList<>();
        recipesTail = new LinkedList<>();
        elf1 = new Elf(0);
        elf2 = new Elf(1);
        initPatternList(inputPattern);
        initFirstRecipes();
    }

    private void initPatternList(String inputPattern) {
        IntStream stream = inputPattern.chars();
        patternList = stream.mapToObj(ic -> Character.getNumericValue(ic)).collect(Collectors.toList());
    }

    private void initFirstRecipes() {
        addRecipe(3);
        addRecipe(7);
    }

    private void addRecipe(int recipe) {
        recipes.add(recipe);
        updateRecipesTail(recipe);
    }

    private void updateRecipesTail(int recipe) {
        if(recipesTail.size()<patternList.size()+1) {
            recipesTail.add(recipe);
        } else {
            recipesTail.add(recipe);
            recipesTail.removeFirst();
        }
    }

    public boolean patternFound() {
        return Collections.indexOfSubList(recipesTail, patternList)>=0;
    }

    public int indexPattern() {
        return Collections.indexOfSubList(recipes, patternList);
    }

    public void improve() {
        int currentRecipeSum = recipes.get(elf1.position) + recipes.get(elf2.position);
        int newRecipe0 = currentRecipeSum / 10;
        int newRecipe1 = currentRecipeSum % 10;
        if(newRecipe0>0) {
            addRecipe(newRecipe0);
        }
        addRecipe(newRecipe1);
        updateElfPosition(elf1);
        updateElfPosition(elf2);

    }

    private void updateElfPosition(Elf elf) {
        elf.position += 1+recipes.get(elf.position);
        if(elf.position>=recipes.size()) {
            elf.position = (elf.position%recipes.size());
        }
    }
}
