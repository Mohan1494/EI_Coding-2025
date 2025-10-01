# Adapter Pattern: Legacy Attendance System Integration

## Overview

This project demonstrates the Adapter Design Pattern in Java.
The college upgraded to a modern portal that expects attendance data in JSON, while the legacy system outputs XML.
The adapter converts XML data to JSON, allowing seamless integration without changing the legacy system.



## Project Structure

```
LegacyAttendanceAdapter/
|
├── src/
│   ├── Attendance.java             // Target Interface
│   ├── LegacyAttendanceSystem.java // Adaptee (XML-based)
│   ├── AttendanceAdapter.java      // Adapter (XML to JSON)
│   └── Main.java                   // Main (Client)
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
Legacy Attendance Format (XML):
<attendance><student id='054'>Absent</student><student id='051'>Absent</student></attendance>

Modern Attendance Format (JSON):
{ "attendance": [{ "id": "054", "status": "false" },{ "id": "051", "status": "false" }] }
```
