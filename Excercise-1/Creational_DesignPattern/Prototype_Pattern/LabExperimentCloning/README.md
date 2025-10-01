# Prototype Pattern: Lab Experiment Template Cloning


## About the Pattern
The **Prototype Pattern** is a creational design pattern that allows creating new objects by copying (cloning) existing ones.  
It is useful when object creation is costly or repetitive, as it enables reuse of pre-built templates.

## Overview

This project demonstrates the Prototype Design Pattern in Java.
It allows cloning of lab experiment templates (e.g., titration, electrolysis) for reuse across multiple student batches, reducing repetitive creation of experiment objects.


## Project Structure

```
LabExperimentCloning/
│
├── src/
│   ├── LabExperiment.java         // Prototype Interface
│   ├── ChemistryExperiment.java   // Concrete Prototype
│   ├── ExperimentClient.java      // Client
│   └── Main.java      // Main
```



## How to Run

### Compile:

```bash
javac -d out src/*.java
```

### Run:

```bash
java -cp out Main
```


## Sample Output

```
Experiment Title: Acid-Base Titration
Objective: To determine the concentration of an unknown acid using NaOH.
Materials: [Burette, Pipette, Indicator, NaOH, Unknown Acid]
Procedures: [Fill burette with NaOH, Add indicator to acid, Titrate until color changes]

Experiment Title: Acid-Base Titration
Objective: To determine the concentration of an unknown acid using NaOH.
Materials: [Burette, Pipette, Indicator, NaOH, Unknown Acid]
Procedures: [Fill burette with NaOH, Add indicator to acid, Titrate until color changes]
```
