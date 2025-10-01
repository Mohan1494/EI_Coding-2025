#  Builder Pattern: College Event Registration System

## About the Pattern
The **Builder Pattern** is a creational design pattern used to build complex objects step by step.  
It separates the construction process from the final representation, making object creation more flexible and readable.
Instead of creating an object in a single step with a long constructor, the builder pattern allows setting properties gradually and then assembling them into a final product


## Overview

This project demonstrates the Builder Design Pattern in Java.
It constructs complex registration forms for college events like Tech Fest, Workshops, and Cultural Nights.
The builder pattern allows step-by-step creation of forms, making the process flexible and readable.



## Project Structure

```
CollegeEventRegistration/
│
├── src/
│   ├── RegistrationForm.java     // Product class
│   ├── Builder.java              // Builder interface
│   ├── TechFestBuilder.java      // Concrete Builder
│   ├── RegistrationDirector.java // Director
│   └── Main.java                 // Client
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
Event Registration Form:
Event Name: Tech Fest
Team Size: 4
Fee: ₹500.0
Required Documents: [College ID]
Perks: [T-shirt, Lunch Coupon]
```
