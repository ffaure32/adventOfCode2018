package day10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LightPoint {
    private final Velocity velocity;
    private Position pos;
    Pattern pattern = Pattern.compile("position=< *(.*), *(.*)> velocity=< *(.*), *(.*)>");
    public LightPoint(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            pos = new Position(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            velocity = new Velocity(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        } else {
            throw new RuntimeException();
        }
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Position getPos() {
        return pos;
    }

    public void move() {
        pos = new Position(pos.x+velocity.xSpeed, pos.y+velocity.ySpeed);
    }
}
