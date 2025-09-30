# Builder Pattern: College Event Registration System

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
