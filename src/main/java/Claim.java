import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Claim {

    public final int id;
    public final int leftEdge;
    public final int topEdge;
    public final int width;
    public final int height;


    public Claim(int id, int leftEdge, int topEdge, int width, int height) {
        this.id = id;
        this.leftEdge = leftEdge;
        this.topEdge = topEdge;
        this.width = width;
        this.height = height;
    }
    static Pattern pattern = Pattern.compile("#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)");

    public static Claim toClaim(String claimString) {
        Matcher matcher = pattern.matcher(claimString);
        if(matcher.find()) {
            return new Claim(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5))
            );
        } else {
            return null;
        }
    }

    public int totalWidth() {
        return leftEdge+width;
    }
    public int totalHeight() {
        return topEdge+height;
    }

}
