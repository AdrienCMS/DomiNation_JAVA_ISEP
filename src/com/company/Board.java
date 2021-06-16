package com.company;

import java.util.ArrayList;

public class Board {
    public Cell[][] Cells = new Cell[5][5];
    private Position position = new Position();
    //Constructeur
    public Board ()
    {
        //Initialisation des cases du terrain
        for (int i = 0 ; i < 5 ; i++)
        {
            for (int j = 0 ; j < 5; j++)
            {
                position.row = i;
                position.column= j;
                this.Cells[i][j] = new Cell();
            }
        }
    }

    //Placer le chateau un première fois sur le terrain de jeu
    public void placeCastle(int x, int y)
    {
        Cells[x][y].fill("CASTLE",0);
    }
    //Affichage de la grille dans la console
    public void display()
    {
        //On parcours les éléments du tableau
        for (int i = 0 ; i <= 4 ; i ++)
        {
            for(int j = 0; j <= 4; j ++)
            {
                //Chaque caractère est entouré de '|'
                System.out.print('|');
                //Affichage d'une lettre correspondant à un paysage
                switch (Cells[i][j].landscape)
                {
                    case "" :
                        System.out.print('X');
                        break;

                    case "WASTELAND" :
                        System.out.print('W');
                        break;

                    case "DESERT" :
                        System.out.print('D');
                        break;

                    case "FOREST" :
                        System.out.print('F');
                        break;

                    case "SEA" :
                        System.out.print('S');
                        break;

                    case "PLAIN" :
                        System.out.print("P");
                        break;

                    case "CASTLE":
                        System.out.print("C");
                        break;

                    case "MINE" :
                        System.out.print("M");
                }
                System.out.print(Cells[i][j].crown);
                System.out.print('|');
            }
            //Retour à la ligne + retour chariot
            System.out.print("\n\r");
            //Ligne de séparation
            for(int k = 0; k < 20 ; k++) System.out.print('-');
            //Retour à la ligne + retour chariot
            System.out.print("\n\r");

        }
    }

    //Vérification des cases adjacentes
    public boolean ckeckAround(Position position)
    {
        //Ici, on vérifie si une des cases adjacentes contient un paysage similaire ou une case CASTLE
        boolean result = false;
        if(position.row < 4)
        if (Cells[position.row][position.column].landscape == Cells[position.row+1][position.column].landscape || Cells[position.row+1][position.column].landscape == "CASTLE" ) result |= true;
        if(position.row > 0)
        if (Cells[position.row][position.column].landscape == Cells[position.row-1][position.column].landscape || Cells[position.row-1][position.column].landscape == "CASTLE") result |= true;
        if(position.column < 4)
        if (Cells[position.row][position.column].landscape == Cells[position.row][position.column+1].landscape || Cells[position.row][position.column+1].landscape == "CASTLE") result |= true;
        if(position.column > 0)
        if (Cells[position.row][position.column].landscape == Cells[position.row][position.column-1].landscape || Cells[position.row][position.column-1].landscape == "CASTLE") result |= true;

        return result;
    }
    //Comptage de points
    //*NON TERMINÉE
    public int getPoints()
    {
        ArrayList<Cell> collection = new ArrayList<>();
        boolean sameLandscape = true;
        int points = 0;
        //On parcours le tableau pour trouver des paysages adjacents
        for (int i = 0 ; i < 5 ; i++)
        {
            for(int j = 0; j<5;j++)
            {
                    if(i < 4) {
                        if (Cells[i][j].landscape == Cells[i + 1][j].landscape) {
                            collection.add(Cells[i][j]);
                        }
                    }
                    if( j < 5) {
                        if (Cells[i][j].landscape == Cells[i][j + 1].landscape) {
                            collection.add(Cells[i][j]);
                        }
                    }
                    if(i > 0) {
                        if (Cells[i][j].landscape == Cells[i-1][j].landscape)
                        {
                            collection.add(Cells[i][j]);
                        }
                    }
                    if (j > 0)
                    {
                        if (Cells[i][j].landscape == Cells[i][j].landscape)
                        {
                            collection.add(Cells[i][j]);
                        }
                    }

            }
        }

        return points;
    }
}
