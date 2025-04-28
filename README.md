CS 3502 Project 2: CPU Scheduling Simulator

Course: CS 3502 (Operating Systems)University: Kennesaw State University, Department of Computer ScienceStart Date: March 17, 2025Due Date: April 25, 2025

Project Overview

This repository contains a Java-based console application that simulates six CPU scheduling algorithms for OwlTech Systems:

First Come First Serve (FCFS)

Shortest Job First (SJF)

Round Robin (RR) (quantum = 2)

Priority Scheduling

Shortest Remaining Time First (SRTF) (new)

Multi-Level Feedback Queue (MLFQ) (new)

Users can load a fixed test workload of five processes, select an algorithm, and view per-process metrics (start, finish, waiting, turnaround, response) as well as summary performance metrics (average waiting time, turnaround time, response time, CPU utilization, throughput).

Repository Structure

├── Process.java               # Process Control Block (PCB) class
├── SchedulingAlgorithms.java  # Static methods for all 6 scheduling algorithms
├── Scheduler.java             # Main class: console menu & test data loader
├── README.md                  # This document
└── .gitignore                 # Exclude compiled binaries and metadata

Prerequisites

Java SE Development Kit (JDK) 8+

A command‑line terminal (Windows, macOS, or Linux)

Git (optional, for version control)

Build & Run Instructions

Clone the repository

git clone https://github.com/annikandersson/CS-3502-Project-2-CPU-Scheduling.git
cd CS-3502-Project-2-CPU-Scheduling

Compile all Java sources

javac Process.java SchedulingAlgorithms.java Scheduler.java

Run the simulator

java Scheduler

Choose from the on-screen menu:

1 – Load the example five processes

2 – FCFS

3 – SJF

4 – Round Robin (Q = 2)

5 – Priority

6 – SRTF

7 – MLFQ

0 – Exit

Functional Requirements

Implement at least two new algorithms beyond the starter code: SRTF and MLFQ.

Measure the following metrics for each algorithm:

Average Waiting Time (AWT)

Average Turnaround Time (ATT)

CPU Utilization (%)

Throughput (Processes per Second)

Response Time (RT)

Compare algorithm performance under the same workload to determine trade-offs in fairness, responsiveness, and efficiency.

Testing

Basic Tests (3–5 processes): hand-calc expected waiting/turnaround

Edge Cases: identical arrival times, zero‑burst, extreme burst mix

Random Workloads (10–50 processes): verify no errors and reasonable metrics

Refer to the Report.pdf for detailed test cases and results.

Deliverables

Source Code – .java files (no compiled .class or IDE metadata)

Public GitHub Repo – this repository (MIT license)

LaTeX Report (PDF) – Report.pdf summarizing design, results, and recommendations

Demo Video (3–5 min) – walkthrough of menu, algorithms, and output

License

This project is released under the MIT License. See LICENSE for details.
