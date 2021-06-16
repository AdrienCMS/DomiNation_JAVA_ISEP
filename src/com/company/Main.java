package com.company;

import java.util.Scanner;

public class Main {

    static Match match = new Match();
    static int playerNumber = 0;
    static String number;


    public static void main(String[] args) {
	// write your code here
        Scanner in = new Scanner(System.in);

        //On demandeà l'utilisateur de renseigner le nombre de joueurs
        while(playerNumber < 1 || playerNumber > 4) {
            System.out.print("Nombre de joueurs : ");

            playerNumber = in.nextInt();

        }
        //Lancement de la méthode start de match qui gère les parties
        match.start(playerNumber);
    }
}
