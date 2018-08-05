import java.util.*;
/**
 * Write a description of class methodsClass here.
 *
 *This class contains all the relevant methods
 *related to the game connect4.
 *Such as : player1turn, player2turn, checkWinner
 *checkTie, drawGift, drawConnect4
 *
 *It also includes the traceVariable method
 *(which tests for the current value
 *of the variable, although it is commented out).
 *
 *
 * @Rithik Bansal
 * @12/06/2017
 */
public class Connect4GameMethods
{
    
    //the following statements declare all the variables
    //which are used multiple times in this class. 
    private static String player1;
    private static String player2;
    private static int p1WinCount;
    private static int p2WinCount;
    private static int gameCounter;
    
    //public variable: user is of class Scanner
    public static Scanner user = new Scanner(System.in);

    //the method promtps user to pick their game symbols
    //player 1 picks first.
    public static void gameSymbols(){
        
        //prompts player1 to pick his/her symbol for the game
        System.out.print("\n*Best option will be to choose");
        System.out.println(" a symbol with 2 characters.*");
        System.out.print("\nPlayer1 choose your game symbol: ");
        player1 = user.nextLine();
        
        //prompts player2 to pick his/her symbol for the game
        System.out.print("\n*Best option will be to choose");
        System.out.println(" a symbol with 2 characters.*");
        System.out.print("\nPlayer2 choose your game symbol: "); 
        player2 = user.nextLine();
        System.out.println();
    }

    //creates the multidimensional array
    //the 2 int parameters refer to row and columns
    //respectively
    public static String[][] gameBoard(int a, int b){
        String[][] board = new String[a][b*3];

        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length;j++){
                if(j%3==0)
                    board[i][j] = "[";

                if(j>=2 && (j-2)%3==0)
                    board[i][j] = "]";

                if(j>=1 && (j-1)%3==0)
                    board[i][j] = "__";
            }
        } 

        return board;
    }

    //prints the game board onto the screen.
    //prints the column number at the end of the game board.
    public static void printBoard(String[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.print(a[i][j]);
            }
            System.out.println();
        }

        for(int i = 1; i<= a.length; i++){
            System.out.print("  " +i+ " " );
        }  
    }

    //method which prompts the player1 to take his turn.
    public static String[][] player1Turn(String[][] a){

        System.out.println("\nEnter the column number you wish to drop");
        System.out.println(" your symbol. (player1)");
        int p1InputColNum = user.nextInt();
        boolean incorrectInput = false;

        //checks if the user inputted value is the required one
        if(p1InputColNum == 1 || p1InputColNum == 2 || p1InputColNum == 3 || p1InputColNum == 4)
            incorrectInput = true;

        //checks if the user input the correct column number
        //if yes then it is executed otherwise user is reprompted
        //to take his turn. 
        while(incorrectInput == false){
            System.out.print("Incorrect input! ");
            System.out.println("Type only \"1\",\"2\",\"3\" or \"4\"");
            System.out.println("\nEnter the column number you wish to drop");
            System.out.println(" your symbol. (player1)");
            if(p1InputColNum == 1 || p1InputColNum == 2 || p1InputColNum == 3 || p1InputColNum == 4)
            p1InputColNum = user.nextInt();
            if(p1InputColNum == 1 || p1InputColNum == 2 || p1InputColNum == 3 || p1InputColNum == 4)
                incorrectInput = true;
        }

        //num is altered in such a way that it refers to the 
        //column where we want it to drop. What user enters is not 
        //the actual column number where the symbol is suppsoed to be.
        //hence it is updated.
        int value = (p1InputColNum-1)*3+1;

        //the for loop checks for the lowest empty spot in the column
        //and updates it with the player1's symbol.
        for(int i = a.length-1; i>=0; i--){

            if(a[i][value] == "__"){
                a[i][value] = player1;
                break;
            } 
        }

        //returns the updated array with p1s turn 
        return a;
    }

    //method which prompts the player1 to take his turn.
    public static String[][] player2Turn(String[][] a){
        System.out.println("\nEnter the column number you wish to drop");
        System.out.println(" your symbol. (player2)");
        int p2InputColNum = user.nextInt();
        boolean incorrectInput = false;

        //checks if the user inputted value is the required one
        if(p2InputColNum == 1 || p2InputColNum == 2 || p2InputColNum == 3 || p2InputColNum == 4)
            incorrectInput = true;

        //checks if the user input the correct column number
        //if yes then it is executed otherwise user is reprompted
        //to take his turn.            
        while(incorrectInput == false){
            System.out.print("Incorrect input! ");
            System.out.println("Type only \"1\",\"2\",\"3\" or \"4\"");
            System.out.println("\nEnter the column number you wish to drop");
            System.out.println(" your symbol. (player2)");
            p2InputColNum = user.nextInt();
            if(p2InputColNum == 1 || p2InputColNum == 2 || p2InputColNum == 3 || p2InputColNum == 4)
                incorrectInput = true;
        }

        //num is altered in such a way that it refers to the 
        //column where we want it to drop. What user enters is not 
        //the actual column number where the symbol is suppsoed to be.
        //hence it is updated.
        int value = (p2InputColNum-1)*3+1;

        //the for loop checks for the lowest empty spot in the column
        //and updates it with the player1's symbol.
        for(int i = a.length-1; i>=0; i--){

            if(a[i][value] == "__"){
                a[i][value] = player2;
                break;
            } 
        }

        //returns the updated array with p1s turn
        return a;
    }

    //method allows users to take their turns alternatively
    //starting with player 1.
    //Also keeps track of the number of wins by each player
    //number of games played.
    //it also checks if either player wins on any of the turn
    //and whether the game is tied at the end.
    public static boolean endGameP1(String[][] a){
        //these 2 boolean variables are used in the 
        //upcoming while loop. 
        boolean gameEnd = false;
        boolean checkTie = true;

        System.out.println("\nPlayer 1 goes first");
        //while loop which does not stop until the game
        //is tied or either player wins.
        while(gameEnd == false && checkTie){ 
            //calls method where player1 takes his turn
            player1Turn(a);

            //prints the game board including player1s turn
            printBoard(a);

            //checks if player1 won after his most recent turn
            if(checkWinner(a, player1)){                
                gameEnd = true;
                System.out.println("\nPlayer 1 wins");
                p1WinCount++;
                gameCounter++;
                //testVariable(gameCounter);
                return gameEnd;
            } 
            //calls method where player2 takes his turn
            player2Turn(a);

            //prints the game board including player2s turn
            printBoard(a);

            //checks if player2 won after his most recent turn
            if(checkWinner(a, player2)){
                gameEnd = true;
                System.out.println("\nPlayer 2 wins");
                p2WinCount++;
                gameCounter++;
                //testVariable(gameCounter);
                return gameEnd;
            }
            //updates the boolean value if the game is tied
            checkTie = checkTie(a, player1, player2);

            //checks if no one won and prints the 
            //relevant outcome on the screen.
            if(gameEnd == false && checkTie == false)
            {
                checkTie = checkTie(a, player1, player2);
                System.out.println("\nGame Over. No Winner!" );
            }
        }
        return gameEnd;
    }

    //method allows users to take their turns alternatively
    //starting with player 2.
    //Also keeps track of the number of wins by each player
    //number of games played.
    //it also checks if either player wins on any of the turn
    //and whether the game is tied at the end.
    public static boolean endGameP2(String[][] a){
        //these 2 boolean variables are used in the 
        //upcoming while loop. 
        boolean gameEnd = false;
        boolean checkTie = true;

        System.out.println("\nPlayer 2 goes first");
        //while loop which does not stop until the game
        //is tied or either player wins.
        while(gameEnd == false && checkTie){
            //calls method where player2 takes his turn
            player2Turn(a);

            //prints the game board including player2s turn
            printBoard(a);

            //checks if player2 won after his most recent turn
            if(checkWinner(a, player2)){
                gameEnd = true;
                System.out.println("\nPlayer 2 wins");
                p2WinCount++;
                gameCounter++;
                //testVariable(gameCounter);
                return gameEnd;
            }
            //calls method where player1 takes his turn
            player1Turn(a);

            //prints the game board including player1s turn
            printBoard(a);

            //checks if player1 won after his most recent turn
            if(checkWinner(a, player1)){                
                gameEnd = true;
                System.out.println("\nPlayer 1 wins");
                p1WinCount++;
                gameCounter++;
                //testVariable(gameCounter);
                return gameEnd;
            }   
            //updates the boolean value if the game is tied
            checkTie = checkTie(a, player1, player2);

            //checks if no one won and prints the 
            //relevant outcome on the screen.
            if(gameEnd == false && checkTie == false)
            {
                System.out.println("\nGame Over. No Winner!" );
            }
        }
        return gameEnd;
    }

    //return the int value for number of games played
    public static int getGameCounter(){
        //testVariable(gameCounter);
        return gameCounter;
    }

    //return the int value for number of games won by player1
    public static int getP1Win(){
        //testVariable(p1WinCount);
        return p1WinCount;
    }

    //return the int value for number of games won by player2
    public static int getP2Win(){
        //testVariable(p2WinCount);
        return p2WinCount;
    }

    //method checks whether the player won.
    //it checks for all possible sneraios of winning
    //such as horizontal, vertical and diagonals.
    public static boolean checkWinner(String[][] a, String b){
        int repeatSymbol =0;
        boolean gameOver = false;
        int i=0, j=0;
        int value = 1;

        //the actual array contains 12 columns
        //so the following variable traces those where
        //we want the symbol to land. 
        int numOfColumns = a[0].length/3;

        //code to check winner along the rows
        //done via a nested for loop.
        for(i = 0 ;i< a.length; i++){
            for(j =1; j<numOfColumns; j++){
                //counts the number of times the same symbol is repeated in a straight line
                if(a[i][(j-1)*3+1] == a[i][j*3+1] && a[i][(j-1)*3+1] == b )
                    repeatSymbol++;

                //checks the number of times the symbol is repeated
                //in a straight line. If yes, it ends the method and 
                //returns true.
                if(repeatSymbol == 3){
                    gameOver = true;
                    return gameOver;
                }
            }
            repeatSymbol =0;
        }  
        //the variable is updated to 0 for the next part of the code
        repeatSymbol =0;

        //code to check winner along the column
        //done via a nested loop
        for(i = 1 ;gameOver == false && i< numOfColumns; i++){
            for(j = 0; j < a.length-1;j++){

                //counts the number of times the same symbol is repeated in a straight line
                if(a[j][(i-1)*3+1] == a[j+1][(i-1)*3+1] && a[j][(i-1)*3+1] == b)
                    repeatSymbol++;

                //checks the number of times the symbol is repeated
                //in a straight line. If yes, it ends the method and 
                //returns true.
                if(repeatSymbol == 3){
                    gameOver = true;
                    return gameOver;             
                }
            }
            repeatSymbol = 0;
        } 
        //the variable is updated to 0 for the next part of the code
        repeatSymbol =0;

        //code to check winner from top left to bottom right
        //done via a single for loop
        for(i = 0, j=1 ;gameOver == false && i< a.length-1 && j<numOfColumns; i++, j++){
            //counts the number of times the same symbol is repeated in a straight line
            if(a[i][(j-1)*3+1] == a[i+1][j*3+1] && a[i][(j-1)*3+1] == b)
                repeatSymbol++;

            //checks the number of times the symbol is repeated
            //in a straight line. If yes, it ends the method and 
            //returns true.    
            if(repeatSymbol == 3){
                gameOver = true;
            }
        }
        //the variable is updated to 0 for the next part of the code
        repeatSymbol =0;

        //code to check winner from bottom left to top right
        //checks via a single for loop
        for(i = a.length-1, j =1 ;gameOver == false && i> 0 && j<numOfColumns; i--, j++){
            //counts the number of times the same symbol is repeated in a straight line
            if(a[i][(j-1)*3+1] == a[i-1][j*3+1] && a[i][(j-1)*3+1] == b)
                repeatSymbol++;

            //checks the number of times the symbol is repeated
            //in a straight line. If yes, it ends the method and 
            //returns true.    
            if(repeatSymbol == 3){
                gameOver = true;
                return gameOver;
            }
        }
        return gameOver;
    }

    //method checks if the game is tied
    //the case where neitehr player wins and the game board is full.
    public static boolean checkTie(String[][] a, String b, String c){
        int numOfColumns = a[0].length/3;
        boolean gameOver = true;
        int i = 0;

        //the for loop checks if the top row of the game board is full after
        //checkWinner method returned false
        for(int j = 1; j<numOfColumns; j++){
            if((a[i][(j-1)*3+1] == b || a[i][(j-1)*3+1] == c) && a[i][(j-1)*3+1] != "__"){
                gameOver = false;
            }
            else
                gameOver = true;
        }
        return gameOver;
    }

    //method gameTitle prints the name of the game
    //and description of the game.
    //(Wrote it all by myself.)
    public static void gameTitle(){
        //The following statements print Connect 4 in a huge font 
        //as the first item on the screen.
        System.out.print("   ______     ______    ____      __   ");
        System.out.print("____      __   _______    ______  ");
        System.out.println("_____________         ___");

        System.out.print("  /  __  \\   / ____ \\  |    \\    |  |");
        System.out.print(" |    \\    |  | |  _____|  /  __  \\ ");
        System.out.println("|____   _____|       /  /");

        System.out.print(" /  /  \\__\\ / /    \\ \\ |  |\\ \\   |  | ");
        System.out.print("|  |\\ \\   |  | |  |__    /  /  \\__\\");
        System.out.println("     |  |    ____   /  /  ");

        System.out.print("|  |        | |    | | |  | \\ \\  |  | ");
        System.out.print("|  | \\ \\  |  | |  ___|   |  |      ");
        System.out.println("     |  |   |____| /  / __");

        System.out.print("|  |     __ | |    | | |  |  \\ \\ |  | ");
        System.out.print("|  |  \\ \\ |  | |  |      |  |    __");
        System.out.println("     |  |         /  /_|  |_");

        System.out.print(" \\ \\____/ / \\ \\____/ / |  |   \\ \\|  | ");
        System.out.print("|  |   \\ \\|  | |  |_____ \\  \\___/ /");
        System.out.println("     |  |         |____    _|");

        System.out.print("  \\______/   \\______/  |__|    \\____| ");
        System.out.print("|__|    \\____| |________| \\______/ ");
        System.out.println("     |__|              |__| ");

        //The follwoing statements give a brief description about the game
        //the version of the game made in this particular code. 
        System.out.print("This is a 2 player game");
        System.out.print(" in this code user can use any symbol as their");
        System.out.print(" game unit.\nHowever, the game board is not a ");
        System.out.print("typical 7 by 6 board but a 4 by 4 board in this code");
        System.out.print(" \nThe objective of the game is to be the first to form a ");
        System.out.print("\nhorizontal, vertical, or diagonal line of four ");
        System.out.println("of one's own discs.");
    }

    //method prints a huge gift shaped structure 
    //at the end of the game. 
    //(Wrote it all by myself.)
    public void drawGift(){
        System.out.print(" ___________\n|    ||    |\n|    OO    |\n");
        System.out.print("|____/\\____|\n|---/||\\---|\n|    ||    |\n");
        System.out.println("|____||____|");
    }

    public static int testVariable(int a){
        System.out.println(a);
        return a;
    }
}
