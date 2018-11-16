package com.example.thomas.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thomas.tp2.model.Task;

import java.util.ArrayList;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private void goToMainPage(ArrayList<Task> tasks) {
        Intent newIntent = new Intent(AddTaskActivity.this, MainActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", tasks);
        newIntent.putExtra("tasks", args);

        startActivityForResult(newIntent, 0);
    }

    private ArrayList<Task> getTasks() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("tasks");
        return (ArrayList<Task>) args.getSerializable("ARRAYLIST");
    }
    
    private void setPreviousButtonListener() {
        Button button = findViewById(R.id.previous);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainPage(getTasks());
            }
        });
    }

    private void initializeFields() {
        EditText label = findViewById(R.id.etLabel);
        label.setText("");

        // Statut
        RadioButton rbTodo = findViewById(R.id.rbTodo);
        RadioButton rbDoing = findViewById(R.id.rbDoing);
        RadioButton rbDone = findViewById(R.id.rbDone);
        rbTodo.setChecked(false);
        rbDoing.setChecked(false);
        rbDone.setChecked(false);

        // Priority
        RadioButton rbLow = findViewById(R.id.rbLow);
        RadioButton rbMedium = findViewById(R.id.rbMedium);
        RadioButton rbHigh = findViewById(R.id.rbHigh);
        rbLow.setChecked(false);
        rbMedium.setChecked(false);
        rbHigh.setChecked(false);
    }

    private void setClearButtonListener() {
        Button button = findViewById(R.id.clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFields();
            }
        });
    }

    private boolean isValidTask() {
        boolean isValid = true;
        EditText label = findViewById(R.id.etLabel);
        if(label.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Le label doit être renseigné", Toast.LENGTH_LONG).show();
            isValid = false;
        } else {
            RadioGroup rgStatus = findViewById(R.id.rgStatus);
            if(rgStatus.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), "Le statut doit être renseigné", Toast.LENGTH_LONG).show();
                isValid = false;
            } else {
                RadioGroup rgPriority = findViewById(R.id.rgPriority);
                if(rgPriority.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "La priorité doit être renseignée", Toast.LENGTH_LONG).show();
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    private Task.STATUS getCheckedStatut() {
        RadioButton rbTodo = findViewById(R.id.rbTodo);
        if(rbTodo.isChecked()) {
            return Task.STATUS.TODO;
        } else {
            RadioButton rbDoing = findViewById(R.id.rbDoing);
            if(rbDoing.isChecked()) {
                return Task.STATUS.DOING;
            } else {
                return Task.STATUS.DONE;
            }
        }
    }

    private Task.PRIORITY getCheckedPriority() {
        RadioButton rbLow = findViewById(R.id.rbLow);
        if(rbLow.isChecked()) {
            return Task.PRIORITY.LOW;
        } else {
            RadioButton rbMedium = findViewById(R.id.rbMedium);
            if(rbMedium.isChecked()) {
                return Task.PRIORITY.MEDIUM;
            } else {
                return Task.PRIORITY.HIGH;
            }
        }
    }

    private void setCreateButtonListener() {
        Button button = findViewById(R.id.create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidTask()) {
                    EditText label = findViewById(R.id.etLabel);

                    Task newTask = new Task(label.getText().toString(), getCheckedStatut(), getCheckedPriority(), new Date());

                    ArrayList<Task> tasks = getTasks();
                    tasks.add(newTask);

                    goToMainPage(tasks);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        setPreviousButtonListener();
        setClearButtonListener();
        setCreateButtonListener();
    }
}
