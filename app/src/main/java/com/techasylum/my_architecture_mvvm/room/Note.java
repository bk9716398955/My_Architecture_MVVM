package com.techasylum.my_architecture_mvvm.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

import java.util.Date;

@Entity(tableName = "note_table")
public class Note {

    String title;
    String description;

//    private   int priority;
     String date;

    @PrimaryKey(autoGenerate = true)
    int id;

    public Note(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}
