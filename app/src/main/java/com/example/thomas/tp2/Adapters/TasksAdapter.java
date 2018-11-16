package com.example.thomas.tp2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.thomas.tp2.R;
import com.example.thomas.tp2.model.Task;

import java.util.ArrayList;

public class TasksAdapter extends ArrayAdapter<Task> {
    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    private int getStatusColor(View v, Task.STATUS s) {
        switch(s) {
            case TODO:
                return v.getResources().getColor(R.color.advancement_BAD);
            case DOING:
                return v.getResources().getColor(R.color.advancement_MEDIUM);
            default:
                return v.getResources().getColor(R.color.advancement_GOOD);
        }
    }

    private int getPriorityColor(View v, Task.PRIORITY t) {
        // todo use colors.xml
        switch(t) {
            case HIGH:
                return v.getResources().getColor(R.color.advancement_BAD);
            case MEDIUM:
                return v.getResources().getColor(R.color.advancement_MEDIUM);
            default:
                return v.getResources().getColor(R.color.advancement_GOOD);
        }
    }

    private void setAddButtonListener(final int position, final View convertView) {
        Button doneButton = convertView.findViewById(R.id.task_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = getItem(position);
                task.setStatus(Task.STATUS.DONE);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Task task = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView tvLabel = convertView.findViewById(R.id.tvLabel);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        TextView tvPriority = convertView.findViewById(R.id.tvPriority);
        TextView tvDate = convertView.findViewById(R.id.tvDate);

        // Populate the data into the template view using the data object
        tvLabel.setText(task.getLabel());
        tvStatus.setText(task.getStatusString());
        tvStatus.setTextColor(getStatusColor(convertView, task.status));

        tvPriority.setText(task.getPriorityString());
        tvPriority.setTextColor(getPriorityColor(convertView, task.priority));

        tvDate.setText(task.getDate());

        setAddButtonListener(position, convertView);

        return convertView;
    }
}
