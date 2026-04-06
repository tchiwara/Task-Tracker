package com.tinotenda.task_tracker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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

    public void update(int id, String newDescription){
        //read existing tasks
        List<Task> tasks=fileManager.readTasks();
        boolean found=false;
        for(Task task:tasks){
            if(task.getId()==id){
                task.setDescription(newDescription);
                task.setUpdatedAt(LocalDateTime.now());
                //saving updated tasks
                fileManager.writeTasks(tasks);
                System.out.println("Task updated successfully (ID: " + id + ")");
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("Task with ID " + id + " not found.");
            return;
        }
    }
    public void delete(int id){
        List<Task> tasks=fileManager.readTasks();
        Iterator<Task> iterator=tasks.iterator();
        boolean found=false;


        while(iterator.hasNext()){
            Task task=iterator.next();
            if(task.getId()==id){
                iterator.remove();
                //saving updated tasks
                fileManager.writeTasks(tasks);
                System.out.println("Task deleted successfully (ID: " + id + ")");
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("Task with ID " + id + " not found.");
            return;
        }
    }

    public void markInProgress(int id){
        //read existing tasks
        List<Task> tasks=fileManager.readTasks();
        boolean found=false;
        for(Task task:tasks){
            if(task.getId()==id){
                task.setStatus("in-progress");
                task.setUpdatedAt(LocalDateTime.now());
                //saving updated tasks
                fileManager.writeTasks(tasks);
                System.out.println("Task updated successfully (ID: " + id + ")");
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("Task with ID " + id + " not found.");
            return;
        }
    }

    public void markDone(int id){
        //read existing tasks
        List<Task> tasks=fileManager.readTasks();
        boolean found=false;
        for(Task task:tasks){
            if(task.getId()==id){
                task.setStatus("done");
                task.setUpdatedAt(LocalDateTime.now());
                //saving updated tasks
                fileManager.writeTasks(tasks);
                System.out.println("Task updated successfully (ID: " + id + ")");
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("Task with ID " + id + " not found.");
            return;
        }
    }
    public void list(String status) {
        List<Task> tasks = fileManager.readTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            if (status == null || task.getStatus().equals(status)) {
                System.out.println("[" + task.getId() + "] " + task.getDescription() + " - " + task.getStatus());
            }
        }
    }
}