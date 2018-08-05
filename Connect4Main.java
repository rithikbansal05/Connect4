import java.util.*;
import java.lang.*;

/**
 * Write a description of class mainProgram here.
 *
 *It contains the main program and methods such 
 *as the reMatch(which asks user if they want another game.),
 *overallWinner (which checks which player won more games). 
 *
 *The main program calls methods from the class methodsClass.
 *The program as a whole runs a connect4 game with a 4v4 board.
 *Users get to choose their symbol and they can play as many 
 *times they want. 
 *
 * @Rithik Bansal
 * @12/06/2017
 */
public class Connect4Main
{
    //public variable: user is of class Scanner
    //It is public because it is invoked in
    //various methods in mainProgram class.
    public static Scanner user = new Scanner(System.in);
    
    //public variable: connect4 is an instance of class 
    //methodsClass. It is public because it is invoked in
    //various methods in mainProgram class.
    private static Connect4GameMethods connect4 = new Connect4GameMethods();

    
    //this is the main method. This is the method which makes use
    //of other methods declared in the whole program to run a 
    //functioning Connect 4 game. 
    public static void main(String[] args){
        
        /*the following variables declare the number of rows
        *and number of columns in the game board. Changing the
        *number here will automatically work for other methods
        *as well.*/
        int gameBoardRow = 4;
        int gameBoardColumn = 4;

        //the following statements, prints out the word
        //connect 4 in bold in a huge font onto the screen.
        connect4.gameTitle();
        
        //the following statement prompts user to pick their
        //game symbols. 1 for player1, 1 for player2.
        connect4.gameSymbols();
        
        //creates an array with the declared no. of 
        //rows and columns. 
        String[][] playBoard = connect4.gameBoard(gameBoardRow, gameBoardColumn);
        
        //prints the empty board to the screen and 
        //instructions how to play
        connect4.printBoard(playBoard);
        
        //it runs the game until one player wins or
        //the game is tied.
        connect4.endGameP1(playBoard);

        //the boolean variable is used as the condition
        //for the upcoming while loop.
        boolean gameOver = false;
        
        //the following char variable stores users choice 
        //whether they want to play another game.
        char choice = reMatch();
        
        /*the while loop uses the user inputted response
         * for another game. 
         */
        while(gameOver ==false){
            /*The reason for the first 2 if systems with choice == 'y'
             * is to make sure that in the next game, the other player 
             * starts. 
             * 
             * the 3rd if statement i.e. choice == 'n: invokes the 
             * overallWInner method which checks which player won 
             * more games. THe program ends after running the statements
             * under this if statment.
             * 
             * the last else statement repromts user to enter the correct
             * input i.e. y/n. 
             */
            if(choice == 'y' && connect4.getGameCounter()%2==0)
            {   playBoard = connect4.gameBoard(gameBoardRow, gameBoardColumn);
                connect4.printBoard(playBoard);
                connect4.endGameP1(playBoard);
                choice = reMatch();
            }    
            else if(choice == 'y' && connect4.getGameCounter()%2==1)
            {   playBoard = connect4.gameBoard(gameBoardRow, gameBoardColumn);
                connect4.printBoard(playBoard);
                connect4.endGameP2(playBoard);
                choice = reMatch();
            }  
            else if(choice == 'n')
            {
                System.out.println(overallWinner());
                gameOver = true; 
                System.out.println("Game Over");
            }
            else
            {
                System.out.println("Incorrect input. Use \"y\" or \"n\" only");
                choice = reMatch();
                System.out.println();
            }
        }
        

    }

    
    //this methods promts user whether they want to play another game
    //after the previous game ends.
    public static char reMatch(){
        System.out.print("\nPlayers! do you want a re-match? y/n: "); 
        String answer = user.nextLine();
        answer = answer.toLowerCase();
        char choice = answer.charAt(0);
        return choice;
    }

    //This method checks which player won more games
    //and produces the relevant output.
    public static String overallWinner(){
        String winner = null;
        
        //the if else statements pertain to p1 winning the series, 
        //p2 winning the series and series being tied. 
        if(connect4.getP1Win() > connect4.getP2Win())
            {
                //if p1 wins more games than p2 these statements are executed
                winner = "Congratulations! Player 1 wins the series by " +connect4.getP1Win()+ "-" +connect4.getP2Win();
                System.out.println("Here is a gift for you");
                
                //prints a huge gift onto the screen.
                connect4.drawGift();
            }
        else if(connect4.getP1Win() < connect4.getP2Win())
            {
                //if p1 wins less games than p2 these statements are executed
                winner = "Congratulations! Player 2 wins the series " +connect4.getP2Win()+ "-" +connect4.getP1Win();
                System.out.println("Here is a gift for you");
                
                //prints a huge gift onto the screen.
                connect4.drawGift();
            }
        else
            //p1 won same number of games as p2. Hence, no gift is printed. 
            winner = "The series is tied.";

        return winner;
    }

}
