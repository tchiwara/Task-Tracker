package com.tinotenda.task_tracker;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager=new TaskManager();

        if(args.length==0){
            System.out.println("No command provided..");
            return;
        }

        switch (args[0]){

            case "add":
                taskManager.add(args[1]);
                break;

            case "update":
                taskManager.update(Integer.parseInt(args[1]),args[2]);
                break;

            case "delete":
                taskManager.delete(Integer.parseInt(args[1]));
                break;

            case "mark-in-progress":
                taskManager.markInProgress(Integer.parseInt(args[1]));
                break;

            case "mark-done":
                taskManager.markDone(Integer.parseInt(args[1]));
                break;

            case "list":
                if (args.length > 1) {
                    taskManager.list(args[1]); // filtered
                } else {
                    taskManager.list(null); // all tasks
                }
                break;

            default:
                System.out.println("Unknown command.");
        }
    }
}