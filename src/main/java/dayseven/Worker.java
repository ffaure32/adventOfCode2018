package dayseven;

public class Worker {
    private final StepData stepData;
    private int remainingWork = 0;
    private Step workingStep = null;

    public Worker(StepData stepData) {
        this.stepData = stepData;
    }

    public void addWork(Step step, int duration) {
        workingStep = step;
        remainingWork+=duration+1+step.getStepLetter().charAt(0)-'A';
    }

    public void doWork() {
        if(remainingWork>0) {
            remainingWork--;
        }
        if(remainingWork == 0 && workingStep != null) {
            stepData.doStep(workingStep);
            workingStep = null;
        }
    }

    public boolean isAvailable() {
        return remainingWork == 0;
    }
}
