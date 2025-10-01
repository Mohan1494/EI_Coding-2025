#  Educational Initiatives Coding Exercise 2025-26

This repository contains **Java implementations and demos** for classic software design patterns and a mini-project (**Smart Office Facility**).  
The content is organized into two main sections: **Exercise 1: Design Patterns** and **Exercise 2: Smart Office Facility**.



##  Exercise 1: Design Patterns

Explore six software design patterns with clear, practical Java examples.

### Behavioural Design Patterns
1. **Iterator Pattern** – Provides a way to access elements of a collection sequentially without exposing its internal structure.  
2. **Mediator Pattern** – Defines an object that centralizes communication between multiple objects to reduce coupling.  

### Creational Design Patterns
3. **Builder Pattern** – Separates the construction of a complex object from its representation.  
4. **Prototype Pattern** – Creates new objects by cloning existing ones instead of creating from scratch.  

### Structural Design Patterns
5. **Adapter Pattern** – Allows incompatible interfaces to work together by acting as a bridge.  
6. **Bridge Pattern** – Decouples abstraction from its implementation so both can vary independently.  



##  Exercise 2: Smart Office Facility (Console App)

A console-based Smart Office Facility manager implemented in Java.  
Demonstrates **Singleton**, **Observer**, and **Command** patterns,plus logging and simple testing.
 

### Key Features
- Manage meeting rooms, bookings, occupancy and device control (lights, AC).  
- Auto-release of unoccupied bookings.  
- Role-based CLI with Admin and User roles (Admin can change configuration).  

### Design pattern Usage

- **Command Pattern** for CLI commands (book, cancel, config, status, stats, logout, exit).  
- **Observer Pattern**: occupancy changes notify device controllers.  
- **Singleton Pattern**: centralized and single instance for Room Configuration.  


## How to Run 

#### For Coding Exercise:
From the project root directory:

### Compile

```bash
dir /s /b *.java > sources.txt
javac -d out @sources.txt
```
### Run Application
```bash
java -cp out com.smartoffice.app.Main
```

#### For design patterns:
- See each pattern's README.md for details.

## Purpose

- Demonstrate practical use of core software design patterns in Java.  
- Build a modular and maintainable console-based Smart Office system.  
- Apply object-oriented principles to simulate real-world office management scenarios.




