package com.example.thomas.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.thomas.tp2.Adapters.TasksAdapter;
import com.example.thomas.tp2.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Task> tasks;

    private ArrayList<Task> getTaskInExtra() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("tasks");
        ArrayList<Task> tasks = new ArrayList<>();
        if(args != null) {
            Serializable s = args.getSerializable("ARRAYLIST");
            if(s != null) {
                tasks = (ArrayList<Task>) s;
            }
        }
        return tasks;
    }

    private ArrayList<Task> getInitTasks() {
        return new ArrayList<Task>() {{
            add(new Task("Finir le TP Android", Task.STATUS.DONE, Task.PRIORITY.MEDIUM, new Date()));
            add(new Task("S'entraîner au TOEIC", Task.STATUS.TODO, Task.PRIORITY.HIGH, new Date()));
        }};
    }

    private ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = getTaskInExtra();
        if(tasks.size() == 0) {
            tasks = getInitTasks();
        }
        return tasks;
    }

    private void setTasksToAdapter(TasksAdapter adapter, ArrayList<Task> tasks) {
        for(Task t : tasks) {
            adapter.add(t);
        }
    }

    private void setAdapterTasks() {
        tasks = new ArrayList<>();
        // Create the adapter to convert the array to views
        TasksAdapter adapter = new TasksAdapter(this, tasks);
        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.taskList);
        listView.setAdapter(adapter);

        setTasksToAdapter(adapter, getTasks());
    }

    private void addButtonListener() {
        FloatingActionButton addTask = findViewById(R.id.addTaskButton);
        addTask.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", tasks);
                intent.putExtra("tasks", args);

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAdapterTasks();
        addButtonListener();
    }
}