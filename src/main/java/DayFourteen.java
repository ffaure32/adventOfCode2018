import day14.Recipes;

import java.util.List;
import java.util.stream.Collectors;

public class DayFourteen {
    public static final int RECIPES_TO_KEEP = 10;
    private final int rounds;
    private final Recipes recipes;

    public DayFourteen(String rounds) {
        this.rounds = Integer.parseInt(rounds);
        recipes = new Recipes(rounds);
    }

    public DayFourteen(int rounds) {
        this.rounds = rounds;
        String pattern = String.valueOf(rounds);
        recipes = new Recipes(pattern);
    }

    public String improvement() {
        while(recipes.recipes.size()<rounds+ RECIPES_TO_KEEP) {
            recipes.improve();
        }
        List<Integer> lastRecipes = recipes.recipes.subList(rounds, rounds+RECIPES_TO_KEEP);
        return lastRecipes.stream().map(String::valueOf).collect(Collectors.joining());
    }
    
    public long findFirstOccurrence() {
        boolean patternFound = false;
        while(!patternFound) {
            recipes.improve();
            patternFound = recipes.patternFound();
        }
        return recipes.indexPattern();
    }
}
