# Iterator Pattern: Student Submission Portal

## About the Pattern
The **Iterator Pattern** is a behavioral design pattern that provides a standard way to traverse elements of a collection without exposing its underlying representation.  
It allows clients to access elements sequentially, supports multiple traversal strategies, and keeps the collection’s internal structure encapsulated.  


## Overview

This project demonstrates the Iterator Design Pattern in Java.
It allows professors to traverse student submissions (PDFs, Word docs, code files) consistently, with support for filtering ungraded submissions.
The internal structure of the collection remains hidden.



## Project Structure

```
AssignmentPortalIterator/
│
├── src/
│   ├── StudentSubmission.java      // Element class
│   ├── Iterator.java               // Generic Iterator interface
│   ├── Aggregate.java              // Generic Aggregate interface
│   ├── SubmissionIterator.java     // Concrete Iterator
│   ├── SubmissionCollection.java   // Concrete Aggregate
│   └── Main.java                   // Client
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
Ungraded Submissions:
Alice submitted a PDF file. Graded: false
Charlie submitted a ZIP file. Graded: false
```

