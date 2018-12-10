import dayseven.Step;
import dayseven.StepData;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaySeven {
    Pattern pattern = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin.");

    public StepData buildSteps(List<String> input) {
        Map<String, Step> stepsByLetter = new HashMap<>();
        for(String stepString : input) {
            Matcher matcher = pattern.matcher(stepString);
            if(matcher.find()) {
                String parent = matcher.group(1);
                String stepLetter = matcher.group(2);
                Step step = stepsByLetter.get(parent);
                if(step == null) {
                    step = new Step(parent);
                    stepsByLetter.put(parent, step);
                }
                step.addChild(stepLetter);

            }

        }
        return new StepData(stepsByLetter);
    }

}
