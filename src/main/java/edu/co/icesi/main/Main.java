package edu.co.icesi.main;

import edu.co.icesi.model.Board;

import java.util.Scanner;

public class Main {

    private Scanner sc;
    public Board board;

    /**
     * This is the constructor
     */
    public Main() {
        sc = new Scanner(System.in);
    }

    /**
     * This is the point of entered
     * @param args
     */
    public static void main(String[] args) {
        Main principal = new Main();
        principal.board = new Board(5,4,2,2);
        /*do{

        }while(true);*/
    }

    private int menu(){
        System.out.println("  (menu)  \n"
                            + "1.Play\n"
                            + "2.Show position \n"
                            + "3.Exit");
        return Integer.parseInt(sc.nextLine());
    }

    private void execute(int option){
        switch(option){
            case 1:
                break;
            case 2:
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

}
