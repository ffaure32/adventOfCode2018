import dayseven.ParallelWorkers;
import dayseven.Step;
import dayseven.StepData;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DaySevenTest {

    private List<String> input;
    private DaySeven daySeven;

    @Before
    public void setUp() throws Exception {
        input = Lists.newArrayList(
                "Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin."
        );
        daySeven = new DaySeven();
    }

    @Test
    @Ignore
    public void nextStep() {
        StepData steps = daySeven.buildSteps(input);

        Step step = steps.nextStep();
        assertEquals("C", step.getStepLetter());
    }

    @Test
    @Ignore
    public void secondStep() {
        StepData steps = daySeven.buildSteps(input);

        Step step = steps.nextStep();
        step = steps.nextStep();
        assertEquals("A", step.getStepLetter());
    }

    @Test
    @Ignore
    public void thirdStep() {
        StepData steps = daySeven.buildSteps(input);

        steps.nextStep();
        steps.nextStep();
        Step step = steps.nextStep();
        assertEquals("B", step.getStepLetter());
    }

    @Test
    public void sequence() {
        StepData steps = daySeven.buildSteps(input);

        String sequence = steps.sequence();
        assertEquals("CABDFE", sequence);
    }

    @Test
    public void sequenceReal() {
        List<String> realInput = InputLoader.loadInputList("inputDay7.txt");
        StepData steps = daySeven.buildSteps(realInput);

        String sequence = steps.sequence();
        assertEquals("AEMNPOJWISZCDFUKBXQTHVLGRY", sequence);
    }

    @Test
    public void parallelWorkersDuration() {
        StepData steps = daySeven.buildSteps(input);
        ParallelWorkers pw = new ParallelWorkers(steps, 2, 0);
        int timer = pw.doWork();
        assertEquals(15, timer);
    }

    @Test
    public void parallelWorkersDurationForRealData() {
        List<String> realInput = InputLoader.loadInputList("inputDay7.txt");
        StepData steps = daySeven.buildSteps(realInput);
        ParallelWorkers pw = new ParallelWorkers(steps, 5, 60);
        int timer = pw.doWork();
        assertEquals(15, timer);
    }

}
