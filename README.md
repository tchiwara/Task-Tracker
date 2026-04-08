# Task Tracker CLI

> Solution for the [Task Tracker](https://roadmap.sh/projects/task-tracker) project on roadmap.sh

A command-line interface (CLI) application to track and manage tasks, built with native Java — no external libraries.

## Features

- Add, update, and delete tasks
- Mark tasks as in-progress or done
- List all tasks or filter by status
- Tasks are persisted in a local `tasks.json` file

## Tech Stack

- Java 17
- Native `java.nio.file` for file I/O
- Manual JSON parsing (no external libraries)

## Project Structure

```
src/
└── main/
    └── java/
        └── com/tinotenda/task_tracker/
            ├── Main.java          # Entry point — reads args and routes commands
            ├── Task.java          # Data model — id, description, status, timestamps
            ├── TaskManager.java   # Business logic — add, update, delete, list
            └── FileManager.java   # Persistence — read/write tasks.json
```

## Getting Started

### Prerequisites

- Java 17 or higher installed
- Terminal / Command Prompt

### Compile

Navigate to the source directory:

```bash
cd src/main/java
javac com/tinotenda/task_tracker/*.java
```

### Run

```bash
java com.tinotenda.task_tracker.Main <command> [arguments]
```

## Usage

### Add a task

```bash
java com.tinotenda.task_tracker.Main add "Buy groceries"
# Output: Task added successfully (ID: 1)
```

### Update a task

```bash
java com.tinotenda.task_tracker.Main update 1 "Buy groceries and cook dinner"
# Output: Task updated successfully (ID: 1)
```

### Delete a task

```bash
java com.tinotenda.task_tracker.Main delete 1
# Output: Task deleted successfully (ID: 1)
```

### Mark a task as in-progress

```bash
java com.tinotenda.task_tracker.Main mark-in-progress 1
# Output: Task updated successfully (ID: 1)
```

### Mark a task as done

```bash
java com.tinotenda.task_tracker.Main mark-done 1
# Output: Task updated successfully (ID: 1)
```

### List all tasks

```bash
java com.tinotenda.task_tracker.Main list
```

### List tasks by status

```bash
java com.tinotenda.task_tracker.Main list todo
java com.tinotenda.task_tracker.Main list in-progress
java com.tinotenda.task_tracker.Main list done
```

## Task Properties

Each task stored in `tasks.json` has the following properties:

| Property | Type | Description |
|---|---|---|
| `id` | int | Unique identifier, auto-incremented |
| `description` | String | Task description |
| `status` | String | `todo`, `in-progress`, or `done` |
| `createdAt` | LocalDateTime | Timestamp when task was created |
| `updatedAt` | LocalDateTime | Timestamp when task was last updated |

## Known Limitations

- Manual JSON parsing is fragile — special characters or extra whitespace in task descriptions may cause parsing issues
- No input validation beyond null checks
- Linear search O(n) for task lookup by ID — acceptable for small task lists

## What I Learned Building This

- File I/O in Java using `java.nio.file`
- Manual JSON serialization and deserialization using `String`, `StringBuilder`, `indexOf()`, and `substring()`
- Single Responsibility Principle applied across a 4-class architecture
- Why libraries like Gson and Jackson exist — and what they're doing under the hood
- CLI argument handling via `String[] args`
- Safe list mutation using `Iterator.remove()`

## Author

Tinotenda — [@tchiwara](https://github.com/tchiwara/Task-Tracker)
