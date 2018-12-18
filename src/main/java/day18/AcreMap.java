package day18;

import com.google.common.base.Objects;

import java.util.*;

public class AcreMap {
    public Map<Integer, Map<Integer, Acre>> map;
    public AcreMap(List<String> lines) {
        buildMapType(lines);
        buildMapAdjacents();
    }

    private void buildMapAdjacents() {
        for (int i = 0; i < map.size(); i++) {
            Map<Integer, Acre> line = map.get(i);
            for (int j = 0; j < line.size(); j++) {
                Acre t = line.get(j);
                List<AcreType> adjacents = new ArrayList<>();
                if (i > 0) {
                    if (j > 0) {
                        adjacents.add(from(i - 1, j - 1));
                    }
                    adjacents.add(from(i - 1, j));
                    if(j+1<line.size()) {
                        adjacents.add(from(i - 1, j + 1));
                    }
                }
                if (j > 0) {
                    adjacents.add(from(i, j - 1));
                }
                if(j+1<line.size()) {
                    adjacents.add(from(i, j + 1));
                }
                if(i+1 < map.size()) {
                    if (j > 0) {
                        adjacents.add(from(i + 1, j - 1));
                    }
                    adjacents.add(from(i + 1, j));
                    if (j + 1 < line.size()) {
                        adjacents.add(from(i + 1, j + 1));
                    }
                }
                t.fillAdjacents(adjacents);
            }

        }
    }

    public AcreMap change() {
        List<String> newLines = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            Map<Integer, Acre> line = map.get(i);
            char[] lineContent = new char[line.size()];
            for (int j = 0; j < line.size(); j++) {
                Acre acre = line.get(j);
                lineContent[j] = acre.change().typeChar;

            }
            newLines.add(String.valueOf(lineContent));
        }
        return new AcreMap(newLines);
    }

    public void print() {
        for (int i = 0; i < map.size(); i++) {
            Map<Integer, Acre> lines = map.get(i);
            for (int j = 0; j < lines.size(); j++) {
                System.out.print(from(i, j).typeChar);
            }
            System.out.println();
        }
    }

    private AcreType from(int i, int j) {
        return map.get(i).get(j).acreType;
    }

    private void buildMapType(List<String> lines) {
        map = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            char[] lineChars = lines.get(i).toCharArray();
            Map<Integer, Acre> mapLine = new HashMap<>();
            map.put(i, mapLine);
            for (int j = 0; j < lineChars.length; j++) {
                AcreType a = AcreType.from(lineChars[j]);
                mapLine.put(j, new Acre(a));
            }

        }
    }

    public long totalResourceValue() {
        return countType(AcreType.TREE) * countType(AcreType.LUMBERYARD);
    }

    private long countType(AcreType type) {
        return map.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(a -> {
                    return a.acreType == type;
                }).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcreMap acreMap = (AcreMap) o;
        return Objects.equal(map, acreMap.map);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(map);
    }
}
