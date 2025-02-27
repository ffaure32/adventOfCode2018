import day14.Recipes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFourteenTest {

    @Test
    public void improve1() {
        Recipes recipes = new Recipes("test");

        recipes.improve();

        assertThat(recipes.recipes).containsExactly(3,7,1,0);
        assertThat(recipes.elf1.position).isEqualTo(0);
        assertThat(recipes.elf2.position).isEqualTo(1);
    }


    @Test
    public void improve3() {
        Recipes recipes = new Recipes("test");

        recipes.improve();
        recipes.improve();
        recipes.improve();

        assertThat(recipes.recipes).containsExactly(3,7,1,0, 1, 0, 1);
        assertThat(recipes.elf1.position).isEqualTo(6);
        assertThat(recipes.elf2.position).isEqualTo(4);
    }

    @Test
    public void testSample() {
        Recipes recipes = new Recipes("test");

        for (int i = 0; i < 15; i++) {
            recipes.improve();
        }

        assertThat(recipes.recipes).containsExactly(3, 7, 1, 0, 1, 0, 1, 2, 4, 5, 1, 5, 8, 9, 1, 6, 7, 7, 9, 2);
        assertThat(recipes.elf1.position).isEqualTo(8);
        assertThat(recipes.elf2.position).isEqualTo(4);
    }

    @Test
    public void testBigSample() {
        assertThat(new DayFourteen(5).improvement()).isEqualTo("0124515891");
        assertThat(new DayFourteen(9).improvement()).isEqualTo("5158916779");
        assertThat(new DayFourteen(18).improvement()).isEqualTo("9251071085");
        assertThat(new DayFourteen(2018).improvement()).isEqualTo("5941429882");
    }

    @Test
    public void testBigSamplePart2() {
        assertThat(new DayFourteen("51589").findFirstOccurrence()).isEqualTo(9);
        assertThat(new DayFourteen("01245").findFirstOccurrence()).isEqualTo(5);
        assertThat(new DayFourteen("92510").findFirstOccurrence()).isEqualTo(18);
        assertThat(new DayFourteen("59414").findFirstOccurrence()).isEqualTo(2018);
    }

    @Test
    public void realInput() {
        assertThat(new DayFourteen(47801).improvement()).isEqualTo("1342316410");
    }

    @Test
    public void realInputPart2() {
        assertThat(new DayFourteen("047801").findFirstOccurrence()).isEqualTo(20235230);
    }
}
