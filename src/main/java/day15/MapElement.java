package day15;

import day10.Position;

import java.util.Arrays;
import java.util.Optional;

public class MapElement {
    public ElementType type;
    public final Position position;

    public enum ElementType {
        GOBELIN('G'),
        ELF('E'),
        WALL('#'),
        OPEN('.');

        public char car;

        ElementType(char c) {
            this.car = c;
        }

        public static ElementType fromChar(char c) {
            return Arrays.stream(ElementType.values())
                    .filter(t -> t.car == c).findFirst()
                    .orElseThrow(RuntimeException::new);
        }
    }
    public MapElement(Position pos, Character c) {
        this.type = ElementType.fromChar(c);
        this.position = pos;

    }

    public boolean isUnit() {
        return type == ElementType.GOBELIN || type == ElementType.ELF;
    }
}
