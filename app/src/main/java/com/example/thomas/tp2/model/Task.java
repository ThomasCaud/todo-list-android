package com.example.thomas.tp2.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
    public enum STATUS {
        TODO, DOING, DONE
    }
    public enum PRIORITY {
        LOW, MEDIUM, HIGH
    }
    public String label;
    public STATUS status;
    public PRIORITY priority;
    public Date deadline;

    public Task(String l, STATUS s, PRIORITY p, Date d) {
        this.label = l;
        this.status = s;
        this.priority = p;
        this.deadline = d;
    }

    public Task() {
        this.label = "no label";
        this.status = STATUS.TODO;
        this.priority = PRIORITY.MEDIUM;
        this.deadline = new Date();
    }

    public String getLabel() {
        return label;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getStatusString() {
        switch (this.status) {
            case TODO:
                return "A faire";
            case DOING:
                return "En cours";
            case DONE:
                return "Fait";
            default:
                return "Undefined status";
        }
    }

    public String getPriorityString() {
        switch (this.priority) {
            case LOW:
                return "Basse";
            case MEDIUM:
                return "Moyenne";
            case HIGH:
                return "Haute";
            default:
                return "Undefined priority";
        }
    }

    public String getDate() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(this.deadline);
    }

    public void setStatus(Task.STATUS s){
        this.status = s;
    }

    public STATUS getStatus() {
        return status;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}