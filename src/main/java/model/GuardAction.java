package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuardAction {
    static Pattern pattern = Pattern.compile("\\[(.*)\\] (.*)");
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm]");

    private final LocalDateTime dateTime;
    private final String action;

    public GuardAction(LocalDateTime dateTime, String action) {
        this.dateTime = dateTime;
        this.action = action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getAction() {
        return action;
    }

    public static GuardAction toGuardAction(String guardActionString) {
        Matcher matcher = pattern.matcher(guardActionString);
        if (matcher.find()) {
            LocalDateTime dateTime = LocalDateTime.parse(matcher.group(1), formatter);
            String action = matcher.group(2);
            return new GuardAction(dateTime, action);
        } else {
            throw new IllegalStateException("mauvais formattage");
        }
    }

    @Override
    public String toString() {
        return "GuardAction{" +
                "dateTime=" + dateTime +
                ", action='" + action + '\'' +
                '}';
    }
}
