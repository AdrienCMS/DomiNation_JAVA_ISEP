package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Classe associée au joueur
public class Player {
    private String name; //Nom du joueur
    private Board board;
    List<King> kingList = new ArrayList(); //Rois du joueur
    List<Domino> Hand = new ArrayList();    //Main du joueur

    //Default Constructor
    public Player()
    {

    }

    //Constructeur
    public Player(String name)
    {
        this.name = name;
    }

    //Getter et Setter du terrain
    public void setBoard(Board board) { this.board = board; }
    public Board getBoard() { return this.board; }

    //Fonction qui renvoie le nom du joueur
    public String getName()
    {
        return this.name;
    }

    //Méthode qui ajoute un roi à la liste de rois du joueur
    public void getKing(King king)
    {
        kingList.add(king);
    }

    //Méthode qui affiche le contenu de la main du joueur
    public void displayHand()
    {
        for(Domino domino : Hand)
        {
            System.out.println(Hand.indexOf(domino) +" : ["+domino.landscape1+":"+domino.crown1+"|"+domino.landscape2+":"+domino.crown2+"]");
        }
    }

    //Méthode qui ajoute un domino à la liste correspondant à la main du joueur
    public void getDomino(Domino domino)
    {
        Hand.add(domino);
    }

    //Méthode qui gère les actions du joueur pendant son tour
    public void play()
    {
        Scanner in = new Scanner(System.in);
        String answer;
        Domino image ;
        Position newPosition1 = new Position();
        Position newPosition2 = new Position();
        boolean turn = true, validMove = false;
        int action = 0,dominoToMove;

        int newX1,newX2,newY1,newY2;
        while(turn)
        {
           switch(action)
           {
               case 0 :
                   do
                   {
                       System.out.println(this.name + " choose your action : \n1 : place domino \n2 : end your turn ");
                       action = in.nextInt();
                   }
                   while(action < 0 &&  action > 3);
                   break;
               case 1 :
                   System.out.println("Enter the number of the domino to place :");
                   this.displayHand();
                   System.out.print("\n");
                   dominoToMove= in.nextInt();

                   while(!validMove) {
                       System.out.println("Enter coordinates for " + dominoToMove + " (format → x1:y1:x2:y2) :");
                       in = new Scanner(System.in);
                       answer = in.nextLine();

                       newX1 = Character.getNumericValue(answer.charAt(0));
                       newY1 = Character.getNumericValue(answer.charAt(2));

                       newX2 = Character.getNumericValue(answer.charAt(4));
                       newY2 = Character.getNumericValue(answer.charAt(6));

                       newPosition1.column = newX1;
                       newPosition1.row = newY1;
                       newPosition2.column = newX2;
                       newPosition2.row =newY2;

                       image = Hand.get(dominoToMove);

                       //Avant de placer le domino, on vérifie si les cases sont libres et si les cases adjacentes contiennent un paysage similaire ou un chateau
                       if(board.ckeckAround(newPosition1) && board.ckeckAround(newPosition2) && board.Cells[newPosition1.row][newPosition1.column].isEmpty() && board.Cells[newPosition2.row][newPosition2.column].isEmpty() )
                       {
                           validMove = true;
                           board.Cells[newY1][newX1].fill(image.landscape1, image.crown1);
                           board.Cells[newY2][newX2].fill(image.landscape2, image.crown2);
                           //Un fois le domino placé, on le supprime de la main du joueur
                           Hand.remove(dominoToMove);

                           action = 0 ;
                           break;
                       }
                       else
                       {
                           System.out.println("Invalid move !\n Try again ?(Y/n)");
                           if(in.nextLine()=="n" || in.nextLine() == "N")
                           {
                               action = 0 ;

                               break;
                           }
                       }
                   }
                   validMove = false;
                   break;

               case 2 :
                   turn = false;
                   action = 0;
                   break;

           }
        }

        return;
    }

}
