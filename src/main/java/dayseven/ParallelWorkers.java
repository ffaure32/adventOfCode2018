package dayseven;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelWorkers {
    private final List<Worker> workers;
    private final int stepBaseDuration;
    private final StepData stepData;

    public ParallelWorkers(StepData stepData, int workersNumber, int stepBaseDuration) {
        this.stepData = stepData;
        workers = new ArrayList<>();
        IntStream.range(0, workersNumber).forEach(i -> workers.add(new Worker(stepData)));
        this.stepBaseDuration = stepBaseDuration;
    }

    public int doWork() {
        int seconds = 0;
        do {
            workers.stream().filter(Worker::isAvailable).forEach(this::giveStepToWorker);
            workers.stream().forEach(Worker::doWork);
            seconds++;
        } while(!stepData.isComplete());
        return seconds;
    }

    private void giveStepToWorker(Worker worker) {
        Step step = stepData.nextChildStep();
        if (step.isValidStep()) {
            worker.addWork(step, stepBaseDuration);
        }
    }

}
