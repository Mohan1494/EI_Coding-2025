# Smart Office - Console Application (Plain Java)

This project implements a console-based Smart Office facility manager in plain Java (no Maven).
It demonstrates Singleton, Observer, and Command patterns + additional best practices (logging, defensive coding, simple testing).

## About the Patterns

### Singleton
The **Singleton Pattern** ensures that only one instance of a class exists in the system and provides a global point of access to it.  
In this project, the configuration manager (`OfficeConfig`) follows Singleton, so the same configuration is shared across the application.

### Observer
The **Observer Pattern** defines a one-to-many dependency between objects, so when one object (subject) changes state, all its dependents (observers) are notified automatically.  
Here, room occupancy changes trigger observers like the `LightController` and `ACController` to turn devices on/off.

### Command
The **Command Pattern** encapsulates a request as an object, allowing parameterization, queuing, and undo/redo functionality.  
In this project, user operations such as booking a room, releasing it, or adding occupants are implemented as commands. This decouples the request from its execution logic and keeps the `Main` driver clean.


### Structure
- `src/com/smartoffice` - application code (models, services, observers, commands)
- `tests` - a simple TestRunner to exercise core functionality
- `smartoffice.log` - runtime log file (created when app runs)

### How to compile (Java 8+)
From the `smart-office` directory:

```
javac -d out src/com/smartoffice/util/LoggerUtil.java src/com/smartoffice/config/OfficeConfig.java src/com/smartoffice/model/Room.java src/com/smartoffice/model/Booking.java src/com/smartoffice/exceptions/BookingException.java src/com/smartoffice/observer/OccupancyObserver.java src/com/smartoffice/observer/LightController.java src/com/smartoffice/observer/ACController.java src/com/smartoffice/service/BookingManager.java src/com/smartoffice/sensors/Sensor.java src/com/smartoffice/commands/Command.java src/com/smartoffice/commands/*.java src/com/smartoffice/app/Main.java
```

Or compile all Java files under src:
```
dir /s /b *.java > sources.txt
javac -d out @sources.txt
```

### Run the app
```
java -cp out com.smartoffice.app.Main
```

### Run tests
```
javac -d out @sources.txt tests/TestRunner.java
java -cp out TestRunner
```

### GitHub / Upload
I cannot directly push to GitHub on your behalf. To upload:
1. `git init`
2. `git add .`
3. `git commit -m "Initial commit - Smart Office"`
4. Create a GitHub repo and follow instructions to `git remote add origin <url>` and `git push -u origin master`

### Notes
- Auto-release window is configurable; default is 5 minutes.
- Notification/email is stubbed via logging; you may connect a real email/SMS provider.
- The included TestRunner is a minimal custom test harness to avoid external dependencies.
