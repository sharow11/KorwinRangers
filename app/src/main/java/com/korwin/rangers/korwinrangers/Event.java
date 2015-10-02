package com.korwin.rangers.korwinrangers;

import java.sql.Timestamp;

/**
 * Created by Sharow on 2015-10-01.
 */
public class Event {
    int _id;
    String _title;
    String _description;
    String _link;
    Timestamp _date;

    // Empty constructor
    public Event(){

    }

    // constructor
    public Event(int id, String title, String description, String link, Timestamp date){
        this._id = id;
        this._title = title;
        this._description = description;
        this._link = link;
        this._date = date;
    }

    public Event(String title, String description, String link, Timestamp date){
        this._title = title;
        this._description = description;
        this._link = link;
        this._date = date;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getTitle(){
        return this._title;
    }

    // setting name
    public void setTitle(String title){
        this._title = title;
    }

    // getting phone number
    public String getDescription(){
        return this._description;
    }

    // setting phone number
    public void setDescription(String description){
        this._description = description;
    }

    public String getLink(){
        return this._link;
    }

    // setting phone number
    public void setLink(String link){
        this._link = link;
    }

    public Timestamp getDate(){
        return this._date;
    }

    // setting phone number
    public void setDate(Timestamp date){
        this._date = date;
    }
}
