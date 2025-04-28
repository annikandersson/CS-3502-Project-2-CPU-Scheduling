// SchedulingAlgorithms class which contains methods for each of the scheduling algorithms

import java.util.*;

public class SchedulingAlgorithms {
    // 1. First Come First Serve (FCFS)
    public static void FCFS(List<Process> processList) {
        System.out.println("\nFirst Come First Serve (FCFS)");
        // Sort processes by arrival time
        processList.sort(Comparator.comparingInt(Process::getArrivalTime));
        int time = 0; // Tracks current CPU time
        // Iterate in arrival order
        for (Process p : processList) {
            // If CPU is idle until process arrives, go forward
            if (time < p.getArrivalTime()) {
                time = p.getArrivalTime();
            }
            p.setStartTime(time); // Record start time for this process
            time += p.getBurstTime(); // Run process to completion
            p.setFinishTime(time); // Record finish time for this process
        }
        // Print the performance metrics for processes
        printPerformance(processList);
    }

    // 2. Shortest Job First (SJF) - Non-preemptive
    public static void SJF(List<Process> processList) {
        System.out.println("\nShortest Job First (SJF) - Non-preemptive");
        List<Process> ready = new ArrayList<>(); // List to hold processes that arrived
        int time = 0; // Tracks current CPU time
        int completed = 0; // Number of completed processes
        int n = processList.size(); // Total # of processes
        // Sort by arrival time to push into ready queue in order
        processList.sort(Comparator.comparingInt(Process::getArrivalTime));
        // Loop until each process has run
        while (completed < n) {
            // Add arrived to ready
            for (Process p : processList) {
                if (p.getArrivalTime() <= time && p.getFinishTime() == 0 && !ready.contains(p)) {
                    ready.add(p); // Enqueue
                }
            }
            // If no processes are ready, advance time forward
            if (ready.isEmpty()) {
                time++;
                continue;
            }
            // Pick the process with the shortest burst time
            ready.sort(Comparator.comparingInt(Process::getBurstTime));
            Process p = ready.remove(0); // Dequeue the shortest

            // If CPU was idle until processes arrive, go forward
            if (time < p.getArrivalTime()) {
                time = p.getArrivalTime();
            }

            p.setStartTime(time); // Record start
            time += p.getBurstTime(); // Run until completed
            p.setFinishTime(time); // Record finish time
            completed++; // Completed
        }
        // Print the performance metrics for processes
        printPerformance(processList);
    }

    // 3. Round Robin (RR) - Preemptive
    public static void RoundRobin(List<Process> processList, int quantum) {
        System.out.println("\nRound Robin (RR) - Preemptive: Quantum = " + quantum);
        Queue<Process> queue = new LinkedList<>(); // Ready queue
        int time = 0; // Track CPU time
        int completed = 0; // Count completed
        int n = processList.size(); // Total # of processes

        // Sort by arrival time
        processList.sort(Comparator.comparingInt(Process::getArrivalTime));
        int index = 0; // Index of next arrival

        // Loop until all are done
        while (completed < n) {
            // Enqueue newly arrived processes
            while (index < n && processList.get(index).getArrivalTime() <= time) {
                queue.add(processList.get(index++)); // Enqueue arrivals
            }

            // If none are ready
            if (queue.isEmpty()) {
                time++; // Advance time
                continue;
            }

            Process p = queue.poll(); // Dequeue next
            if (p.getStartTime() == -1) { // If first run...
                p.setStartTime(time); // Record start
            }
            int run = Math.min(quantum, p.getRemTime()); // Compute run
            p.setRemTime(p.getRemTime() - run); // Decrement remaining time
            time += run; // Advance time

            // Enqueue newly arrived processes during quantum
            while (index < n && processList.get(index).getArrivalTime() <= time) {
                queue.add(processList.get(index++)); // Enqueue arrivals
            }
            if (p.getRemTime() > 0) { // If not finished...
                queue.add(p); // Re-enqueue
            } else {
                p.setFinishTime(time); // Record finish
                completed++; // Increment complete
            }
        }
        printPerformance(processList);
    }

    // 4. Priority Scheduling - Non-preemptive
    public static void Priority(List<Process> processList) {
        System.out.println("\nPriority Scheduling (PR) - Non-Preemptive");
        List<Process> ready = new ArrayList<>(); // Ready queue
        int time = 0; // Track CPU time
        int complete = 0; // Count of completed
        int n = processList.size(); // Total # of processes

        processList.sort(Comparator.comparingInt(Process::getArrivalTime)); // Sort by arrival time
        while (complete < n) { // Loop until done
            for (Process p : processList) { // Enqueue arrived
                if (p.getArrivalTime() <= time && p.getFinishTime() == 0 && !ready.contains(p)) {
                    ready.add(p); // Enqueue
                }
            }
            if (ready.isEmpty()) { // If ready is empty...
                time++; // Advance time
                continue;
            }
            // Pick the highest priority/lowest #
            ready.sort(Comparator.comparingInt(Process::getPriority));
            Process p = ready.remove(0); // Dequeue

            if (time < p.getArrivalTime()) { // If CPU is idle...
                time = p.getArrivalTime(); // Move forward
            }

            p.setStartTime(time); // Record start
            time += p.getBurstTime(); // Run until completed
            p.setFinishTime(time); // Record finish
            complete++; // Complete
        }
        printPerformance(processList);
    }

    // 5. Shortest Remaining Time First (SRTF) - Preemptive
    public static void SRTF(List<Process> processList) {
        System.out.println("\nShortest Remaining Time First (SRTF) - Preemptive");
        int time = 0;
        int complete = 0;
        int n = processList.size();
        processList.sort(Comparator.comparingInt(Process::getArrivalTime)); // Sort by arrival time
        List<Process> ready = new ArrayList<>(); // Ready queue
        int index = 0; // Next arrival index
        while (complete < n) {
            while (index < n && processList.get(index).getArrivalTime() <= time) {
                ready.add(processList.get(index++)); // Enqueue arrivals
            }
            if (ready.isEmpty()) { // If none are ready...
                time++; // Advance time
                continue;
            }
            ready.sort(Comparator.comparingInt(Process::getRemTime)); // Sort by shortest remaining time
            Process p = ready.get(0); // Find shortest
            if (p.getStartTime() == -1) { // If it is the first run...
                p.setStartTime(time); // Record start
            }
            // Run for one time unit
            p.setRemTime(p.getRemTime() - 1);
            time++; // Advance time
            // If finished...
            if (p.getRemTime() == 0) { // If completed...
                p.setFinishTime(time); // Record finish
                ready.remove(p); // Remove from ready queue
                complete++; // Increment and complete
            }
        }
        printPerformance(processList);
    }

    // 6. Multi-Level Feedback Queue (MLFQ) - 3 levels
    public static void MLFQ(List<Process> processList) {
        System.out.println("\nMulti-Level Feedback Queue (MLFQ)");

        int time = 0;
        int complete = 0;
        int n = processList.size();

        // Three queues with different quantums
        Queue<Process>[] queues = new Queue[3];
        int[] quantum = {2, 4, 8};
        for (int i = 0; i < 3; i++) {
            queues[i] = new LinkedList<>();
        }
        // Sort each process by arrival time
        processList.sort(Comparator.comparingInt(Process::getArrivalTime));
        int index = 0; // Next process in processList to arrive

        // Continue until each process has finished
        while (complete < n) {
            // Enqueue arriving processes into highest priority queue
            while (index < n && processList.get(index).getArrivalTime() <= time) {
                queues[0].add(processList.get(index));
                index++;
            }

            boolean ran = false; // Checks if any process ran this cycle

            // Find the highest priority, non-empty queue
            for (int level = 0; level < 3; level++) {
                if (!queues[level].isEmpty()) {
                    Process p = queues[level].poll(); // Take the next process
                    if (p.getStartTime() == -1) {
                        p.setStartTime(time); // Record the first run
                    }

                    // Run up to the quantum or until finishes
                    int runTime = Math.min(quantum[level], p.getRemTime());
                    p.setRemTime(p.getRemTime() - runTime);
                    time += runTime;

                    // Enqueue newly arrived processes during run at level 0
                    while (index < n && processList.get(index).getArrivalTime() <= time) {
                        queues[0].add(processList.get(index++));
                    }

                    // If not done, demote it (unless already at lowest level)
                    if (p.getRemTime() > 0) {
                        // Demote if not the lowest
                        int nextLevel = Math.min(level + 1, 2);
                        queues[nextLevel].add(p);
                    } else {
                        // Mark as finished
                        p.setFinishTime(time);
                        complete++;
                    }

                    ran = true;
                    break; // Restart at highest priority after running each process
                }
            }
            if (!ran) {
                time++; // CPU idle if all queues are empty
            }
        }

        printPerformance(processList);
    }

    // Helper method to print performance metrics for each process
    private static void printPerformance(List<Process> processes) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalResponseTime = 0;
        int totalBurstTime = 0;

        // Performance header for each process metrics
        System.out.println("\nProcess\tArrival\tBurst\tStart\tFinish\tWait\tTurnaround\tResponse");

        // Loop through processes & determine metrics
        for (Process p : processes) {
            int burstTime = p.getBurstTime();
            int waitingTime = p.getWaitingTime(); // Turnaround time - burst time
            int turnaroundTime = p.getTurnaroundTime(); // Finish time - arrival time
            int responseTime = p.getResponseTime(); // Start time - arrival time

            totalBurstTime += burstTime;
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
            totalResponseTime += responseTime;

            // Print a line of metrics
            System.out.printf(
                    "%s\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d%n",
                    p.getId(),
                    p.getArrivalTime(),
                    burstTime,
                    p.getStartTime(),
                    p.getFinishTime(),
                    waitingTime,
                    turnaroundTime,
                    responseTime
            );
        }

        int n = processes.size();
        double makespan = processes.get(n - 1).getFinishTime(); // Total time from start to finish

        // Compute the averages
        double avgWaitingTime = totalWaitingTime / n;
        double avgTurnaroundTime = totalTurnaroundTime / n;
        double avgResponseTime = totalResponseTime / n;

        // CPU Usage (%) -> Total time CPU was busy / makespan
        double cpuUsage = (totalBurstTime / makespan) * 100;

        // Throughput -> # of processes completed (per unit time)
        double throughput = n / makespan;

        // Print performance metrics
        System.out.printf(
                "\nAverage Waiting Time: %.2f%n" + "Average Turnaround Time: %.2f%n" + "Average Response Time: %.2f%n" + "CPU Usage: %.2f%%%n" + "Throughput: %.2f processes/unit time%n", avgWaitingTime, avgTurnaroundTime, avgResponseTime, cpuUsage, throughput);
    }
}
