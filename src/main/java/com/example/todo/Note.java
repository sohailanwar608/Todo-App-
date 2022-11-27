package com.example.todo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

 // table name in room , we change it , which automatically generate is  Note.

@Entity(tableName = "my_notes")
public class Note
{
    private String title;
    private String disp;

    // room increment it automatically
    // component of mvvm architecture
    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }


    public Note(String title, String disp)
    {
        this.title = title;
        this.disp = disp;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDisp()
    {
        return disp;
    }

    public void setDisp(String disp)
    {
        this.disp = disp;
    }
}
