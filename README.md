# CS 3502 Project 2: CPU Scheduling Simulator

**Course:** CS 3502 (Operating Systems), Section W03 
**Professor:** Christopher Reagan
**Start Date:** March 17, 2025  
**Due Date:** April 25, 2025  

---

## Project Overview

This repository contains a Java-based console application that simulates six CPU scheduling algorithms for OwlTech Systems:

1. **First Come First Serve (FCFS)**
2. **Shortest Job First (SJF)**
3. **Round Robin (RR)** (quantum = 2)
4. **Priority Scheduling**
5. **Shortest Remaining Time First (SRTF)** *(new)*
6. **Multi-Level Feedback Queue (MLFQ)** *(new)*

Users can load a fixed test set of five processes, select an algorithm, and view each processes metrics (start, finish, waiting, turnaround, response) as well as performance metrics (average waiting time, turnaround time, response time, CPU utilization, throughput).

---

## Repository Structure

```
├── Process.java               # Process Control Block (PCB) class
├── SchedulingAlgorithms.java  # Methods for all 6 scheduling algorithms
├── Scheduler.java             # Main class - console menu & test data loader
├── README.md                  # This document
└── .gitignore                 
```

---

## Prerequisites

- **Java SE Development Kit (JDK) 8+**
- A command‑line terminal (Windows, macOS, or Linux)
- Git (optional, for version control)

---

## Build & Run Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/annikandersson/CS-3502-Project-2-CPU-Scheduling.git
   cd CS-3502-Project-2-CPU-Scheduling
   ```

2. **Compile all Java sources**
   ```bash
   javac Process.java SchedulingAlgorithms.java Scheduler.java
   ```

3. **Run the simulator**
   ```bash
   java Scheduler
   ```

4. **Choose from the on-screen menu:**
   1. Load the example five processes
   2. FCFS
   3. SJF
   4. Round Robin (Q = 2)
   5. Priority
   6. SRTF
   7. MLFQ
   0. Exit

---

## Functional Requirements

- Implement **at least two new algorithms** beyond the starter code: **SRTF** and **MLFQ**.
- Measure the following metrics for each algorithm:
  - Average Waiting Time (AWT)
  - Average Turnaround Time (ATT)
  - CPU Utilization (%)
  - Throughput (Processes per Second)
  - Response Time (RT)
- Compare algorithm performance under the same workload to determine trade‑offs in fairness, responsiveness, and efficiency.

---

## Testing

1. **Basic Tests** (3–5 processes): hand‑calculate expected waiting/turnaround times
2. **Edge Cases**: identical arrival times, zero‑burst processes, extreme burst time variance
3. **Random Workloads** (10–50 processes): verify stability, no errors, and reasonable performance metrics

Refer to **Report.pdf** for more detailed test cases and results.

---

## Deliverables

1. **Source Code** – `.java` files
2. **Public GitHub Repo** – this repository (MIT license)
3. **LaTeX Report (PDF)** – `Report.pdf` summarizing design and results
4. **Demo Video (3–5 min)** – walkthrough of menu, algorithms, and output

---

## License

This project is released under the **MIT License**.


