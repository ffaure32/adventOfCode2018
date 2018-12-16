package day15;

import day10.Position;

public class FightPath {
    public FightPath parentPosition;
    public Position position;

    public FightPath(Position pos, FightPath parent) {
        this.position = pos;
        this.parentPosition = parent;

    }
}
