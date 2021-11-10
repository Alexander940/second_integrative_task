package edu.co.icesi.main;

import edu.co.icesi.model.Board;

import java.util.Scanner;

public class Main {

    private Scanner sc;
    public Board board;
    private Board userBoard;
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
        principal.menu();
    }


    private void menu(){
        System.out.println("  Menu  \n"
                + "1.Play\n"
                + "2.Exit");
        int option= Integer.parseInt(sc.nextLine());
        if(option==1){
            initializeGame();
            menu();
        }
        System.out.println("Goodbye\n");
    }

    private void initializeGame(){
        System.out.println("Please register the parameters for this game\n" + "*Please create a board bigger than 3*2/2*3*");
        String board= sc.nextLine();
        String[] parts = board.split(" ");
        if(Integer.parseInt(parts[2])+Integer.parseInt(parts[3])<=(Integer.parseInt(parts[0])*Integer.parseInt(parts[1])-2)/2) {
            if(Integer.parseInt(parts[0])!=2 || Integer.parseInt(parts[1])!=3) {
                if ((Integer.parseInt(parts[0]) != 3 || Integer.parseInt(parts[1]) != 2)){
                    userBoard = new Board(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), 0);
                    System.out.println(printBoard(userBoard, "", 0, 0, userBoard.getDimension(), parts));
                }
                else{
                    System.out.println("Please register valid parameters\n");
                    initializeGame();
                }
            }
            else{
                System.out.println("Please register valid parameters\n");
                initializeGame();
            }
        }
        else{
            System.out.println("Please register valid parameters\n");
            initializeGame();
        }
    }

    private String printBoard(Board boardToPrint,String msg,int rows,int cols,int nodes,String[] parts){
        if(cols<boardToPrint.getNumColumns()){
            if(rows<boardToPrint.getNumRows()){
                if(nodes==1){
                    msg += "[" + nodes + parts[4] +"] ";
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnakeHead()!=null){
                    msg+="["+nodes+ boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnake() + "] ";
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnakeTail()!=null){
                    msg+="["+nodes+ boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnake() + "] ";
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadderBottom()!=null){
                    msg+="["+nodes+ boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadder() + "] ";
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadderTop()!=null){
                    msg+="["+nodes+ boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadder() + "] ";
                }
                else {
                    msg += "[" + nodes + "] ";
                }
                return printBoard(boardToPrint,msg,rows+1,cols,nodes-1,parts);
            }
            msg+="\n";
            return printBoard(boardToPrint,msg,0,cols+1,nodes,parts);
        }
        return msg;
    }

}