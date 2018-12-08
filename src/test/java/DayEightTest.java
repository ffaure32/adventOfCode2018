import day8.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayEightTest {
    @Test
    public void buildNodeWhithoutChildren() {
        String input = "0 3 11 12 13";
        String[] inputArray = input.split(" ");

        Node node = new Node(inputArray);

        assertEquals(0, node.getChildren().size());
        assertEquals(3, node.getMetaData().size());
        assertEquals(36, node.total());
    }

    @Test
    public void buildNodeWhith1Children() {
        String input = "1 3 0 1 15 11 12 13";
        String[] inputArray = input.split(" ");

        Node node = new Node(inputArray);

        assertEquals(1, node.getChildren().size());
        assertEquals(3, node.getMetaData().size());
        assertEquals(51, node.total());
    }


    @Test
    public void buildNodeSample() {

        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        String[] inputArray = input.split(" ");

        Node node = new Node(inputArray);

        assertEquals(138, node.total());
        assertEquals(66, node.value());
    }

    @Test
    public void buildNodeRealInput() {
        String input = InputLoader.loadInputAsString("inputDay8.txt");
        String[] inputArray = input.split(" ");

        Node node = new Node(inputArray);

        assertEquals(47647, node.total());
        assertEquals(23636, node.value());

    }

}
