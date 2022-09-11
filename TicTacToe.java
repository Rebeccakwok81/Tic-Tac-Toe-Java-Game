import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe{

    static ArrayList<Integer> playerPostions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPostions = new ArrayList<Integer>();

    public static void main(String[]arg){

        //create 3 rows
        char[] [] gameBoard = {{' ', '|', ' ', '|',' '}, 
                          {'-', '+', '-', '+','-'}, 
                          {' ', '|', ' ', '|',' '}, 
                          {'-', '+', '-', '+','-'}, 
                          {' ', '|', ' ', '|',' '}}; 

      
        printGameBoard(gameBoard);             
       
       //create the game loop, keep asking the user for position
       while(true){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your move(1-9)");
        int playerPos = scan.nextInt();

        //check the right position and keep asking if the user didn't input the right position
        while(playerPostions.contains(playerPos) || cpuPostions.contains(playerPostions)){
            System.out.println("Position taken, please enter a correct position");
            playerPos = scan.nextInt();
        }

        placePiece(gameBoard, playerPos, "player");

        String result = checkWinner();
        if(result.length() > 0){  
        System.out.println(result);
        break;
        }

        //use Random to generate the move of cpu
        Random random = new Random();
        int cpuPos = random.nextInt(9) + 1;
        while(playerPostions.contains(cpuPos) || cpuPostions.contains(cpuPos)){
            cpuPos = random.nextInt(9) + 1;
        }
        placePiece(gameBoard, cpuPos, "cpu");
 
        printGameBoard(gameBoard);

        result = checkWinner();
        if(result.length() > 0){  
        System.out.println(result);
        break;
        }


       }
      
    }

    public static void printGameBoard(char[][] gameBoard){
        //print out the game board
        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user){

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPostions.add(pos);
        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuPostions.add(pos);
        }

        switch(pos){
            case 1:
            gameBoard[0][0] = symbol;
            break;
    
            case 2:
            gameBoard[0][2] = symbol; // row 0, column 2
            break;
    
            case 3:
            gameBoard[0][4] = symbol;
            break;
    
            case 4:
            gameBoard[2][0] = symbol; // row 2, column 0
            break;
    
            case 5:
            gameBoard[2][2] = symbol;
            break;
    
            case 6:
            gameBoard[2][4] = symbol;
            break;
    
            case 7:
            gameBoard[4][0] = symbol;
            break;
    
            case 8:
            gameBoard[4][2] = symbol;
            break;
    
            case 9:
            gameBoard[4][4] = symbol;
            break;

            default: 
            break;
        
           }
    }


    public static String checkWinner(){
        //check inside the 2D Array, and where is the position of the winnier
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        //put all the winning condition in a List 
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning){
            if(playerPostions.containsAll(l)){
                return "Yah! You won!";
            } else if(cpuPostions.containsAll(l)){
                return"Oops, CPU wins!";
            } else if(playerPostions.size() + cpuPostions.size() == 9){
                return "CAT!";
            }
        }

        return "";

    }
}