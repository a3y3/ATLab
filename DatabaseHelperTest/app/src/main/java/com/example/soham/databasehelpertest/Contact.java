package com.example.soham.databasehelpertest;

/**
 * Created by Soham on 21-Mar-17.
 */

public class Contact {
    int _id;
    String _name;
    String _phone_number;

    public Contact(int _id, String _name, String _phone_number)
    {
        this._id = _id;
        this._name = _name;
        this._phone_number = _phone_number;
    }

    public Contact(){}

    public Contact(String _name, String _phone_number)
    {
        this._name = _name;
        this._phone_number = _phone_number;
    }

    public int get_id(){
        return this._id;
    }

    public String get_name(){
        return this._name;
    }

    public String get_phone_number(){
        return this._phone_number;
    }

    public void set_id(int id)
    {
        this._id = id;
    }

    public void set_name(String name){
        this._name = name;
    }

    public void set_phone_number(String _phone_number)
    {
        this._phone_number = _phone_number;
    }

}
