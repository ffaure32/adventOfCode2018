package day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Node {
    public static final int NODE_INFO_LENGTH = 2;
    private final List<Node> children;
    private int nbMetaData;
    private final List<Integer> metaData;

    public Node(String[] inputArray) {
        int nbChildren = Integer.parseInt(inputArray[0]);
        nbMetaData = Integer.parseInt(inputArray[1]);
        children = new ArrayList<>(nbChildren);
        metaData = new ArrayList<>(nbMetaData);

        addChildren(inputArray, nbChildren);
        addMetaData(inputArray);
    }

    private void addMetaData(String[] inputArray) {
        IntStream.range(0, nbMetaData).forEach(in -> {
            metaData.add(Integer.parseInt(inputArray[size()-nbMetaData+in]));
        });
    }

    private void addChildren(String[] inputArray, int nbChildren) {
        String[] childrenData = Arrays.copyOfRange(inputArray, NODE_INFO_LENGTH, inputArray.length);
        AtomicInteger currentIndex = new AtomicInteger();
        IntStream.range(0, nbChildren).forEach(in -> {
            Node childNode = new Node(Arrays.copyOfRange(childrenData, currentIndex.get(), inputArray.length));
            children.add(childNode);
            currentIndex.addAndGet(childNode.size());
        });
    }

    public int size() {
        Integer collect = children.stream().collect(Collectors.summingInt(n -> n.size()));
        return NODE_INFO_LENGTH+nbMetaData+collect;
    }

    public List<Node> getChildren() {
        return children;
    }

    public List<Integer> getMetaData() {
        return metaData;
    }

    private int totalNode() {
        return metaData.stream().collect(Collectors.summingInt(i -> i));
    }

    public int total() {
        int total = totalNode();
        total += children.stream().collect(Collectors.summingInt(n -> n.total()));
        return total;
    }

    public int value() {
        if(children.isEmpty()) {
            return total();
        } else {
            return metaData.stream().collect(Collectors.summingInt(data -> childValue(data)));
        }
    }

    private Integer childValue(Integer data) {
        int index = data-1;
        if(index<children.size()) {
            return children.get(index).value();
        }
        return 0;
    }
}
