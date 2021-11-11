package edu.co.icesi.main;

import edu.co.icesi.model.Board;

import java.util.Scanner;

public class Main {

    private Scanner sc;
    private int movementsToWin;
    private Board userBoard;
    /**
     * This is the constructor
     */
    public Main() {
        sc = new Scanner(System.in);
        movementsToWin=0;
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
            if(Integer.parseInt(parts[0])>=3 && Integer.parseInt(parts[1])>=3) {
                userBoard = new Board(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), 0);
                userBoard.getHead().setPlayersOnNode(parts[4]);
                System.out.println(printBoard(userBoard, "", 0, 0, userBoard.getDimension(), parts[4]));
                sc.nextLine();
                System.out.println(printActiveBoard(userBoard,"",0,0,userBoard.getDimension()));
                String instruction=sc.nextLine();
                startPlaying(userBoard,parts[4],0,instruction);
                System.out.println("The game has ended!");
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

    private void startPlaying(Board currentBoard,String players,int i,String instruction){
        if(instruction.equalsIgnoreCase("num")){
            System.out.println(printActiveBoardNum(currentBoard,"",0,0, currentBoard.getDimension()));
            instruction= sc.nextLine();
            startPlaying(currentBoard,players,i,instruction);
        }
        else if(instruction.equalsIgnoreCase("menu")){
            menu();
            return;
        }
        else if(currentBoard.getFinishGame()==false){
                if(i<players.length()){
                    System.out.println(currentBoard.movePlayer(players.charAt(i)));
                    movementsToWin++;
                    if(currentBoard.getFinishGame()==true) {
                        int totalMovements=returnMovementsToWin(players.length());
                        System.out.println("With " + totalMovements + " movements\n");
                        movementsToWin=0;
                        return;
                    }
                    else {
                        System.out.println(printActiveBoard(currentBoard, "", 0, 0, currentBoard.getDimension()));
                        instruction= sc.nextLine();
                        startPlaying(currentBoard,players,i+1,instruction);
                    }
                }
            startPlaying(currentBoard,players,0,"");
        }
    }

    private String printBoard(Board boardToPrint,String msg,int rows,int cols,int nodes,String players){
        if(cols<boardToPrint.getNumColumns()){
            if(rows<boardToPrint.getNumRows()){
                if(nodes==1){
                    msg += "[" + nodes + players +"] ";
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
                return printBoard(boardToPrint,msg,rows+1,cols,nodes-1,players);
            }
            msg+="\n";
            return printBoard(boardToPrint,msg,0,cols+1,nodes,players);
        }
        return msg;
    }

    private String printActiveBoard(Board boardToPrint,String msg,int rows,int cols,int nodes){
        if(cols<boardToPrint.getNumColumns()){
            if(rows<boardToPrint.getNumRows()){
                msg+="[";
                if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnakeHead()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnake();
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnakeTail()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnake();
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadderBottom()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadder();
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadderTop()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadder();
                }

                if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getPlayersOnNode()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getPlayersOnNode();
                }
                msg+="] ";
                return printActiveBoard(boardToPrint,msg,rows+1,cols,nodes-1);
            }
            msg+="\n";
            return printActiveBoard(boardToPrint,msg,0,cols+1,nodes);
        }
        return msg;
    }

    private String printActiveBoardNum(Board boardToPrint,String msg,int rows,int cols,int nodes){
        if(cols<boardToPrint.getNumColumns()){
            if(rows<boardToPrint.getNumRows()){
                msg+="[" + nodes;
                if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnakeHead()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnake();
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnakeTail()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getSnake();
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadderBottom()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadder();
                }
                else if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadderTop()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getLadder();
                }

                if(boardToPrint.get(nodes,1,boardToPrint.getHead()).getPlayersOnNode()!=null){
                    msg+=boardToPrint.get(nodes,1,boardToPrint.getHead()).getPlayersOnNode();
                }
                msg+="] ";
                return printActiveBoard(boardToPrint,msg,rows+1,cols,nodes-1);
            }
            msg+="\n";
            return printActiveBoard(boardToPrint,msg,0,cols+1,nodes);
        }
        return msg;
    }

    private int returnMovementsToWin(int i){
        if(movementsToWin%i!=0){
            movementsToWin++;
            returnMovementsToWin(i);
        }
        return movementsToWin/i;
    }
}