package com.company;

import java.util.*;

public class Match {
    //-------Initialisation des différents objets et variables du jeu-------//
    //Création de la pioche et de la ligne
    ArrayDeque<Domino> pick = new ArrayDeque<>();
    ArrayList<Domino> lign = new ArrayList<>();

    //Liste des dominos
    ArrayList<Domino> availableDominos = new ArrayList<>();

    //Chaine envoyée par le joueur
    String answer = new String();
    //Scanner permettant le dialogue joueur ↔ ordinateur
    Scanner in = new Scanner(System.in);

    //Booléen de vérification
    boolean validAnswer = false;


    int x = 0,y = 0; //Coordonnées envoyées par le joueur
    int kingPos = 0; //position du roi envoyée par le joueur

    //Initialisation du tableau de dominos
    Domino[] dominos = new Domino[24];

    //Création des rois
    King Blue = new King("BLUE");
    King Red = new King("RED");
    King Green = new King("GREEN");
    King Yellow = new King("YELLOW");




    //-------Méthode permettant de lancer et gérer la partie-------//
    public void start(int playerNumber)
    {

        //Initialisation des tableau des terrains de jeu et des joueurs
        Player[] player = new Player[playerNumber];
        Player playerImage = new Player(); //Variable permettant de trier les joueurs en fonction de leur dominos
        Board[] playerBoard = new Board[playerNumber];
        //Initialisation des dominos
        initDominos();
        //initialisation des rois


        //Les noms des joueurs sont définis en même temps que les objets "Player"
        for (int i = 0 ; i < playerNumber; i++) {
            System.out.println("Player " + i + " name :");
            player[i] = new Player(in.nextLine());
        }
        //initialisation des terrains de jeu et des joueurs
        for (int i = 0 ; i < playerNumber; i++)
        {
            playerBoard[i] = new Board();
            playerBoard[i].display();

            player[i].setBoard(playerBoard[i]);
        }


        //Distribution des rois
        switch (playerNumber)
        {
            //un joueur (+ IA)
            case 1 :
                player[0].getKing(Red);
                player[0].getKing(Yellow);
                /*player[1].getKing(Green);
                player[1].getKing(Blue);*/
                break;

            //Deux joueurs
            case 2 :
                player[0].getKing(Red);
                player[0].getKing(Yellow);
                player[1].getKing(Green);
                player[1].getKing(Blue);
                break;
            //Trois joueurs
            case 3 :
                player[0].getKing(Red);
                player[1].getKing(Yellow);
                player[2].getKing(Green);
                break;

            //Quatre joueurs
            case 4 :
                player[0].getKing(Red);
                player[1].getKing(Yellow);
                player[2].getKing(Green);
                player[3].getKing(Blue);
                break;
        }

        //Positionnement des chateaux pour chaque joueurs
        for(int i = 0; i<playerNumber;i++)
        {
            //Tant que les coordonnées entrées ne sont pas au bon format
            //On redemande les coordonnées
            while((!answer.contains(":"))/* || x < 5 || x >=0 || y < 5 || y >= 0 */) {
                System.out.println("Player " + player[i].getName() + ",please enter castle coordinates (format → x:y) :");
                answer = in.nextLine();


                x = Character.getNumericValue(answer.charAt(0));
                y = Character.getNumericValue(answer.charAt(2));
            }
            answer = "";

            playerBoard[i].Cells[y][x].fill("CASTLE",0);

            //Mise à jour du terrain dans lu joueur
            player[i].setBoard(playerBoard[i]);
            playerBoard[i].display();

        }

        //#######Début du jeu#######
        //On joue tant que la pioche comporte des dominos
        while(!pick.isEmpty())
        {
            //On pioche 4 dominos ou (3 si 3 joueurs)
            if(playerNumber == 3)
            {
                for (int i = 0; i < 3; i++)
                    lign.add(pick.poll());
            }
            else
            {
                for (int i = 0; i < 4; i++)
                    lign.add(pick.poll());
            }

            //Les joueurs sélectionnent des dominos
            printLign();

            //Pour chaque joueur
            for(int i = 0; i<playerNumber;i++)
            {
                //Pour chaque roi appartenant au joueur
                for(King king: player[i].kingList)
                {
                    while(!validAnswer)
                    {
                     System.out.println("Player " +player[i].getName()+",please choose "+king.getColor()+" position (1,2,3 or 4):");
                     kingPos = in.nextInt();
                     kingPos --;

                     if(kingPos < 4 && kingPos >= 0)
                     {

                         king.setPos(kingPos);
                         validAnswer = true;
                     }
                    }

                    validAnswer = false;

                }
            }
            System.out.println("Take your dominoes");
            //Les dominos passent dans les mains des joueurs
            for(Player p : player)
            {
                for(King k : p.kingList)
                p.getDomino(lign.get(k.getPos()));
            }

            //L'ordre des tours varie en fonction du numéro de domino reçu
           /* for(int i = 0;i < playerNumber; i ++)
            {
                playerOrder[i] =
            }*/
            lign.clear();
            System.out.println("Time to play !");
            //Les joueurs jouent chacun leurs tour
            for(Player p : player)
            {
                System.out.println("Player "+p.getName()+" turn !");
                p.getBoard().display();
                p.play();
                p.getBoard().display();
            }


        }

        //Une fois que la pioche est vide , on commence le comptage de points
        for (Player p : player)

        //Fin de partie
        System.out.println("Fin de la partie");
    }

    //-------Initialisation des dominos du jeu-------//
    private void initDominos()
    {
        //Initialisation des Dominos du jeu
        dominos[0] = new Domino("FOREST","PLAIN",0,2);
        dominos[1] = new Domino("SEA","PLAIN",0,2);
        dominos[2] = new Domino("DESERT","WASTELAND",0,2);
        dominos[3] = new Domino("FOREST","WASTELAND",0,2);
        dominos[4] = new Domino("PLAIN","WASTELAND",0,2);
        dominos[5] = new Domino("MINE","FOREST",2,0);
        dominos[6] = new Domino("MINE","PLAIN",2,0);
        dominos[7] = new Domino("DESERT","MINE",0,3);

        dominos[8] = new Domino("FOREST","DESERT",1,0);
        dominos[9] = new Domino("FOREST","DESERT",1,0);
        dominos[10] = new Domino("FOREST","SEA",1,0);
        dominos[11] = new Domino("SEA","DESERT",1,0);
        dominos[12] = new Domino("SEA","DESERT",1,0);
        dominos[13] = new Domino("SEA","FOREST",1,0);
        dominos[14] = new Domino("SEA","FOREST",1,0);
        dominos[15] = new Domino("DESERT","PLAIN",0,1);

        dominos[16] = new Domino("DESERT","DESERT",0,0);
        dominos[17] = new Domino("DESERT","DESERT",0,0);
        dominos[18] = new Domino("FOREST","FOREST",0,0);
        dominos[19] = new Domino("SEA","SEA",0,0);
        dominos[20] = new Domino("PLAIN","PLAIN",0,0);
        dominos[21] = new Domino("WASTELAND","WASTELAND",0,0);
        dominos[22] = new Domino("DESERT","FOREST",1,0);
        dominos[23] = new Domino("DESERT","SEA",1,0);

        //Ajout des dominos à la liste
        for (int i = 0; i < 24 ;i++)
        {
            availableDominos.add(dominos[i]);
        }

        //Mélange de la liste
        Collections.shuffle(availableDominos);

        //La liste mélangée devient la pioche
        pick = new ArrayDeque<Domino>(availableDominos);


    }

    //-------Affichage des dominos piochés-------//
    public void printLign()
    {
        for(int i = 0; i<4;i++)
        {
            System.out.println((i+1)+" : ["+lign.get(i).landscape1+":"+lign.get(i).crown1+"|"+lign.get(i).landscape2+":"+lign.get(i).crown2+"]");
        }
    }



}
