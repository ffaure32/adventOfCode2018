import java.time.LocalTime;

public class Action {
    private final LocalTime time;
    private final String type;

    public Action(LocalTime time, String type) {
        this.time = time;
        this.type = type;
    }
}
