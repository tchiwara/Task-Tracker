package com.tinotenda.task_tracker;

import java.time.LocalDateTime;
import java.util.List;

public class TaskManager {
    FileManager fileManager=new FileManager();

    public void add(String description) {
        // read existing tasks
        List<Task> tasks = fileManager.readTasks();

        // generate next id
        int newId = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;

        // create new task
        Task task = new Task(newId, description, "todo", LocalDateTime.now(), LocalDateTime.now());

        // add to list and save
        tasks.add(task);
        fileManager.writeTasks(tasks);

        System.out.println("Task added successfully (ID: " + newId + ")");
    }

}