# Mediator Pattern: College Department Communication System

## About the Mediator Pattern
The **Mediator Pattern** is a behavioral design pattern that defines an object that encapsulates how a set of objects interact.   
This is especially useful in systems where many components communicate in complex ways, reducing dependencies and simplifying maintenance.

## Overview

This project demonstrates the Mediator Design Pattern in Java.
It centralizes communication between college departments like Admin, Library, Hostel, and Academics, avoiding direct dependencies and tight coupling.



## Project Structure

```
CollegeDepartmentMediator/
│
├── src/
│   ├── Department.java           // Colleague Interface
│   ├── AdminDepartment.java      // Concrete Colleague 1
│   ├── LibraryDepartment.java    // Concrete Colleague 2
│   ├── CollegeMediator.java      // Mediator Interface
│   ├── DepartmentMediator.java   // Concrete Mediator
│   └── Main.java         // Main (Client)
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
Mediator routing Admin's request to Library...
Library Department received: Clearance requested: Student ID 2025A001 - Transfer Certificate

Mediator routing Library's response to Admin...
Admin Department received: Clearance status: Clearance granted for Student ID 2025A001
```

::
