package day17;

import day10.Position;

public class GroundSquare {
    public final GroundType type;
    public final Position position;
    public GroundContent content;

    public GroundSquare(GroundType type, Position position) {
        this.type = type;
        this.content = GroundContent.EMPTY;
        this.position = position;
    }

    public void print() {
        if(content == GroundContent.EMPTY) {
            System.out.print(type);
        } else {
            System.out.print(content);
        }
    }
}
