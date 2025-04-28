// Process class which represents a process control block (PCB)

public class Process {
    private String id; // Unique identification
    private int arrivalTime; // Process arrival time
    private int burstTime; // Total CPU time
    private int remTime; // Remaining time (preemptive schedulers)
    private int priority; // Priority level (lower val -> higher priority)
    private int startTime; // Time when process starts execution
    private int finishTime; // Time when process finishes execution

    // Process constructor to initialize values
    public Process(String id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remTime = burstTime; // Initially, no CPU time used, thus remaining time = burst time
        this.priority = priority;
    }

    // Getters & Setters for Process class
    public String getId() {
        return id;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getBurstTime() {
        return burstTime;
    }
    public int getRemTime() {
        return remTime;
    }
    public int getPriority() {
        return priority;
    }
    public int getStartTime() {
        return startTime;
    }
    public int getFinishTime() {
        return finishTime;
    }
    public void setStartTime(int time) {
        this.startTime = time;
    }
    public void setFinishTime(int time) {
        this.finishTime = time;
    }
    public void setRemTime(int time) {
        this.remTime = time;
    }
    // Turnaround time -> Finish time - arrival time
    public int getTurnaroundTime() {
        return finishTime - startTime;
    }
    // Waiting time -> Turnaround time - burst time
    public int getWaitingTime() {
        return getTurnaroundTime() - burstTime;
    }
    // Response time -> Start time - arrival time
    public int getResponseTime() {
        return startTime - arrivalTime;
    }
}
