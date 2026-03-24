package com.tinotenda.task_tracker;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileManager {
    public List<Task> readTasks(){
        //Defining the File Path
        Path path= Paths.get(System.getProperty("user.dir"),"tasks.json");

        //Checking if the file exists
        if(Files.exists(path)){
            try{
                //Reading the file into string
                String content=Files.readString(path);

                Gson gson=new Gson();
                Type taskListType =new TypeToken<List<Task>>(){}.getType();

                List<Task> tasks=gson.fromJson(content,taskListType);
                return tasks != null?tasks:new ArrayList<>();
            } catch (IOException e) {
                System.out.println("Error reading file.");
                return new ArrayList<>();
            }
        }else{
            return new ArrayList<>();
        }
    }

    public void writeTasks(List<Task> task){

    }
}