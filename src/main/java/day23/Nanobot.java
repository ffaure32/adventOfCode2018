package day23;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Nanobot {
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
        } else {
            throw new RuntimeException();
        }
    }

    public boolean inRange(Nanobot strongest) {
        long distance = 0;
        distance += Math.abs(this.position[0] - strongest.position[0]);
        distance += Math.abs(this.position[1] - strongest.position[1]);
        distance += Math.abs(this.position[2] - strongest.position[2]);
        return distance <= strongest.radius;
    }
}
