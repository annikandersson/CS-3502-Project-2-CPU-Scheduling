// Scheduler - Main class with console menu

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scheduler {
    private static Scanner sc = new Scanner(System.in);
    private static List<Process> processes = new ArrayList<>();

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("CPU SCHEDULING MENU:");
            System.out.println("1. Load Example Processes");
            System.out.println("2. First Come First Serve (FCFS)");
            System.out.println("3. Shortest Job First (SJF)");
            System.out.println("4. Round Robin (RR) - Q = 2");
            System.out.println("5. Priority");
            System.out.println("6. Shortest Remaining Time First (SRTF)");
            System.out.println("7. Multi-Level Feedback Queue (MLFQ)");
            System.out.println("0. Exit");
            System.out.print("Enter Choice Here: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    load();
                    break;
                case 2:
                    SchedulingAlgorithms.FCFS(copy());
                    break;
                case 3:
                    SchedulingAlgorithms.SJF(copy());
                    break;
                case 4:
                    SchedulingAlgorithms.RoundRobin(copy(),2);
                    break;
                case 5:
                    SchedulingAlgorithms.Priority(copy());
                    break;
                case 6:
                    SchedulingAlgorithms.SRTF(copy());
                    break;
                case 7:
                    SchedulingAlgorithms.MLFQ(copy());
                    break;
                case 0:
                    run = false;
                    System.out.println("Exiting Program...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Load the fixed test set
    private static void load() {
        processes.clear(); // Empties anything in processes list before
        // Create processes with an ID, arrival time, burst time, and priority, then add to processes list
        processes.add(new Process("P1",0,5,2));
        processes.add(new Process("P2",2,3,1));
        processes.add(new Process("P3",4,1,3));
        processes.add(new Process("P4", 6,7,2));
        processes.add(new Process("P5",8,4,1));
        System.out.println("The 5 processes (P1, P2, P3, P4, & P5) have been loaded!");
    }

    // Copy of the original list of processes with all information
    private static List<Process> copy() {
        List<Process> processList = new ArrayList<>();
        for (Process process : processes) {
            processList.add(new Process(process.getId(), process.getArrivalTime(), process.getBurstTime(), process.getPriority()));
        }
        return processList;
    }
}
