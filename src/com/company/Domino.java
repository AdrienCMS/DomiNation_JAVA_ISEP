package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Domino {
    static int number = 0;
    public String landscape1 = new String() ; //Variable contenant l'environnement de la case 1 "MINE","FOREST","DESERT","SEA","PLAIN" ou "WASTELAND"
    public String landscape2 = new String();  //Variable contenant l'environnement de la case 2
    public int crown1, crown2;      //Nombre de couronnes de chaque cases
    public Position positionCell1;         //positions du domino
    public Position positionCell2;

    //Default constructor
    public Domino()
    {}

    //Constructeur où l'on défini les paysages et les couronnes du domino
    public Domino(String landscape1,String landscape2,int crown1,int crown2)
    {
        //Le domino obtien son numéro unique
        number++;
        this.landscape1 = landscape1;
        this.landscape2 = landscape2;

        this.crown1 = crown1;
        this.crown2 = crown2;

    }

    //Fonction permettant de vérifier si la future position est valide *NE FONCTIONNE PAS **PAS UTILISÉE
    public boolean isValidMove(Position newPosition1, Position newPosition2,Cell[][] board)
    {
        //On crée deux liste contenant les coordonnées des cellules adjacentes
        List<Cell> nearbyCells1 = new ArrayList<>();
        List<Cell> nearbyCells2 = new ArrayList<>();
        boolean matchingLandscapes = false,isOnTheBoard = false, emptyCell = false;

        Cell cellToTheLeft = board[newPosition1.row][newPosition1.column-1];
        Cell cellToTheRight = board[newPosition1.row][newPosition1.column+1];

        Cell cellAbove = board[newPosition1.row-1][newPosition1.column];
        Cell cellBelow = board[newPosition1.row+1][newPosition1.column];

        Cell cellToTheLeft2 = board[newPosition2.row][newPosition2.column-1];
        Cell cellToTheRight2 = board[newPosition2.row][newPosition2.column+1];

        Cell cellAbove2 = board[newPosition2.row-1][newPosition2.column];
        Cell cellBelow2 = board[newPosition2.row+1][newPosition2.column];

        nearbyCells1.add(cellToTheLeft);
        nearbyCells1.add(cellToTheRight);

        nearbyCells1.add(cellAbove);
        nearbyCells1.add(cellBelow);

        nearbyCells2.add(cellToTheLeft2);
        nearbyCells2.add(cellToTheRight2);

        nearbyCells2.add(cellAbove2);
        nearbyCells2.add(cellBelow2);

        //On vérifie pour les cases du domino si les paysages adjacents correspondent
        for (Cell check : nearbyCells1)
        {
         if(check.position.column == newPosition2.column && check.position.row == newPosition2.row )
         {
             //Ici on vérifie si la case visée par la case 2 est vide
             emptyCell |= check.isEmpty();
         }
         else
             if(check.landscape == this.landscape1 || check.landscape == "CASTLE") matchingLandscapes = true;

        }

        for (Cell check : nearbyCells2)
        {
            if(check.position.column == newPosition1.column && check.position.row == newPosition1.row )
            {
                //Ici on vérifie si la case visée par la case 1 est vide
                emptyCell |= check.isEmpty();
            }
            else {
                if (check.landscape == this.landscape2 || check.landscape == "CASTLE") matchingLandscapes = true;

            }
        }



        return true;//matchingLandscapes&emptyCell;//&isOnTheBoard;
    }
}
