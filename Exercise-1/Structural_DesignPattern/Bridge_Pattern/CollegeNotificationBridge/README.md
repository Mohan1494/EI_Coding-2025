# Bridge Pattern: College Event Notification System

## About the Pattern
The **Bridge Pattern** is a structural design pattern that decouples an abstraction from its implementation.  
It is especially useful when you want to avoid a permanent binding between abstraction and implementation,  
or when both abstraction and implementation need to be extended through inheritance.


## Overview

This project demonstrates the Bridge Design Pattern in Java.
The college app needs to send notifications for events like Tech Fest, Exams, and Holidays through multiple channels: Email, SMS, and Push Notifications.
The bridge pattern allows event types and delivery methods to evolve independently.



## Project Structure

```
CollegeNotificationBridge/
│
├── src/
│   ├── NotificationSender.java   // Implementer Interface
│   ├── EmailSender.java          // Concrete Implementer 1
│   ├── SMSSender.java            // Concrete Implementer 2
│   ├── EventNotification.java    // Abstraction
│   ├── ExamAlert.java            // Refined Abstraction 1
│   ├── FestivalReminder.java     // Refined Abstraction 2
│   └── Main.java                 // Main (Client)
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
Sending Email: Reminder: Your semester exam starts tomorrow at 9 AM.
Sending SMS: Celebrate Diwali with us! Join the cultural night at 6 PM.

```

