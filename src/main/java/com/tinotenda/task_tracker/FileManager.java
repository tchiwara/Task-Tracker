package com.tinotenda.task_tracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class FileManager {

    //Extracts a String value from a raw JSON object string.
    public static String extractStringField(String data, String fieldName){
        String startMarker="\""+ fieldName+ "\":\""; //e.g "name":"
        int startIndex=data.indexOf(startMarker)+startMarker.length();
        int endIndex=data.indexOf("\"",startIndex);
        return data.substring(startIndex,endIndex);
    }

    //Extracts an int value from a raw JSON object string.
    public static int extractIntField(String data,String fieldName){
        String startMarker= "\""+fieldName+"\":";
        int startIndex=data.indexOf(startMarker)+startMarker.length();
        int endIndex=data.indexOf(",",startIndex);
        if(endIndex==-1) endIndex=data.length();   // last field has no trailing comma
        String value=data.substring(startIndex,endIndex);
        return Integer.parseInt(value);  //convert string value to int
    }

    public List<Task> readTasks(){
        //Defining the File Path based on the current working directory
        Path path= Paths.get(System.getProperty("user.dir"),"tasks.json");

        if(Files.exists(path)){
            try{
                //Reading the file into string
                String content=Files.readString(path);
                content=content.substring(1,content.length()-1);// strip outer [ ]
                String[] taskChunks=content.split("},\\{");// each chunk is one task

                // adding back the } { consumed by split
                for (int i = 0; i < taskChunks.length; i++) {
                    if (!taskChunks[i].startsWith("{")) {
                        taskChunks[i] = "{" + taskChunks[i];
                    }
                    if (!taskChunks[i].endsWith("}")) {
                        taskChunks[i] = taskChunks[i] + "}";
                    }
                }

                List<Task> tasks = new ArrayList<>();
                //extracting the appropriate data fields
                for (String chunk : taskChunks) {
                    int id = extractIntField(chunk, "id");
                    String description = extractStringField(chunk, "description");
                    String status = extractStringField(chunk, "status");
                    LocalDateTime createdAt = LocalDateTime.parse(extractStringField(chunk, "createdAt"));
                    LocalDateTime updatedAt = LocalDateTime.parse(extractStringField(chunk, "updatedAt"));
                    //creating a task object and initializing it
                    Task task = new Task(id, description, status, createdAt, updatedAt);
                    tasks.add(task);
                }


                return tasks;
            } catch (IOException e) {
                System.out.println("Error reading file.");
                return new ArrayList<>();
            }
        }else{
            return new ArrayList<>();
        }
    }

    public void writeTasks(List<Task> task){
        //write the logic for writing tasks here
    }
}