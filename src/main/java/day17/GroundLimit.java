package day17;

import day10.Position;

public class GroundLimit {
    public final Position position;
    public final GroundLimitType limitType;


    public GroundLimit(Position position, GroundLimitType limitType) {
        this.position = position;
        this.limitType = limitType;
    }
}
