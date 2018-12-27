package day23;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Nanobot {
    public final Long distanceToZero;
    //"pos=<64962278,33699509,27360131>, r=60979411"
    Pattern pattern = Pattern.compile("pos=\\<(.*)\\>, r=([0-9]*)");

    public long radius;
    public Long[] position;
    public Nanobot(String input) {
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            String coords = matcher.group(1);
            String[] coordArray = coords.split(",");
            position = Stream.of(coordArray).map(Long::valueOf).toArray(Long[]::new);
            radius = Long.parseLong(matcher.group(2));
            this.distanceToZero = computeDistanceToZero();
        } else {
            throw new RuntimeException();
        }
    }

    public Nanobot(Long x, Long y, Long z, long radius) {
        this.radius = radius;
        this.position = new Long[3];
        this.position[0] = x;
        this.position[1] = y;
        this.position[2] = z;
        this.distanceToZero = computeDistanceToZero();
    }

    public Nanobot(Long[] position, long radius) {
        this.radius = radius;
        this.position = position;
        this.distanceToZero = computeDistanceToZero();
    }


    public boolean inRange(Nanobot strongest) {
        long distance = computeDistance(strongest.position);
        return distance <= strongest.radius;
    }

    public boolean inRange(Long[] position) {
        long distance = computeDistance(position);
        return distance <= this.radius;
    }

    public long computeDistance(Long[] position) {
        return computeDistance(this.position, position);
    }

    Long[] zero = {0l,0l,0l};
    public long computeDistanceToZero() {
        return computeDistance(this.position, zero);
    }

    public static long computeDistance(Long[] position1, Long[] position2) {
        long distance = 0;
        distance += Math.abs(position1[0] - position2[0]);
        distance += Math.abs(position1[1] - position2[1]);
        distance += Math.abs(position1[2] - position2[2]);
        return distance;
    }

}
