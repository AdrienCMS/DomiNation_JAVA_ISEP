package com.company;

import javax.swing.text.Position;

//Classe associée au rois
public class King {
    private int position ;  // Position
    private String color; //Couleur du roi "RED""GREEN" "BLUE" "YELLOW"

    //Default constructor
    public King()
    {}

    //Constructor
    public King( String color)
    {
        this.color = color;
    }

    //getter et setter pour position
    public void setPos(int pos)
    {
        this.position = pos;
    }
    public int getPos(){return this.position;}

    //*NON UTILISÉE
    public boolean isValidMove(Position newPosition, Cell[][] board)
    {
        return true;
    }
    //Méthode permettant de retourner la couleur du roi
    public String getColor()
    {
        return this.color;
    }
}

