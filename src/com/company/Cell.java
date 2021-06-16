package com.company;

//Classe associée aux cases du jeu
public class Cell {
    public static Position position = new Position();//objet position qui contient les coordonnées de la case
    private boolean isEmpty = true; //Booléen permettant de savoir si la case est vide ou non
    public String landscape = ""; //Variable contenant l'environnement de la case "CASTLE","MINE","FOREST","DESERT","SEA","PLAIN" ou "WASTELAND"
    public int crown;


     //Constructor
        public Cell(){
            //étant statique, ces variables prendrons +1 à chaque nouvelles itérations
            position.row ++;
            position.column ++;
        }

    //Constructor
    public Cell(Position position)
    {
        this.position = position;
    }

    //getter et setter de position non utilisées car ici position est publique
    public void setPosition(Position position)
    {
        this.position = position ;
    }
    public Position getPosition()
    {
        return this.position;
    }

    //Renvoie l'état de la case true si vide false si pleine
    public boolean isEmpty()
    {
        if( this.landscape != "") {
            return this.isEmpty;
        }
        else
        {
            return true;
        }
    }

    //Fonction permettant de remplir une case
    public void fill(String landscape,int crown)
    {
        this.landscape = landscape;
        this.crown = crown;
        this.isEmpty = false;
    }

}
