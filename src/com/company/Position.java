package com.company;

public class Position {
    int column;
    int row;

    //Default constructor
    public void Position()
    {

    }

    //Constructor
    public void Position(int column,int row)
    {
        this.column = column;
        this.row = row;
    }

    //Position to string
    public String toString()
    {
        String answer = String.valueOf(this.column) + String.valueOf(this.row);
        return answer;
    }

}
