import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * This class represents Gomoku game
 * @author Alena Tochilkina
 */

public class Gomoku extends Application {
  
  /**stores the number of pieces of the same color in a row required to win the game*/
  private int winNumber;
  /** stores the number of rows for the board*/
  private int rowNumber;
  /** stores the number of columns for the board*/
  private int columnNumber;
  /** stores the color of the current player*/
  private Color currentColor = Color.BLACK;
  /** stores whether the game is won yet or no*/
  private boolean gameWon = false;
  /** stores the current board situation in integers*/
  private int[][]board;
  /** stores the current player for the board (balck(1) and white(2)*/
  private int player = 1;
  
  /**
   * The method returns current player
   * @return int This returns the current player
   */
  public int getPlayer() {
    return player;
  }
  
  /**
   * The method sets the current player
   * @param player is the current player
   */
  public void setPlayer( int player) {
    this.player = player;
  }
  
  /**
   * The method returns the current board situation
   * @return int[][] This returns the current board situation
   */
  public int[][] getBoard() {
    return board;
  }
  
  /**
   * The method sets the current board situation
   * @param board is the board of integers to represent the current board situation
   */
  public void setBoard(int[][] board) {
    this.board = board;
  }
  
  /**
   * The method returns the color of current player
   * @return Color This returns the color of the current player
   */
  public Color getCurrentColor() {
    return currentColor;
  }
  
  /**
   * The method sets the color of the current player
   * @param currentColor is the color of the current player
   */
  public void setCurrentColor(Color currentColor) {
    this.currentColor = currentColor;
  }
  
  /**
   * The method sets the win number for the game
   * @param winNumber is the number of pieces of the same color in a row required to win the game
   */
  public void setWinNumber(int winNumber) {
    this.winNumber = winNumber;
  }
  
  /**
   * The method returns the win number for the game
   * @return int This returns the win number for the game
   */
  public int getWinNumber() {
    return winNumber;
  }
  
  /**
   * The method sets the number of rows for the game
   * @param rowNumber is the number of rows for the board
   */
  public void setRowNumber(int rowNumber) {
    this.rowNumber = rowNumber;
  }
  
  /**
   * The method returns the number of rows for the board for the game
   * @return int This returns the number of rows for the board for the game
   */
  public int getRowNumber() {
    return rowNumber;
  }
  
  /**
   * The method sets the number of columns for the game
   * @param columnNumber is the number of columns for the board
   */ 
  public void setColumnNumber(int columnNumber) {
    this.columnNumber = columnNumber;
  }
  
  /**
   * The method returns the number of columns for the board for the game
   * @return int This returns the number of columns for the board for the game
   */
  public int getColumnNumber() {
    return columnNumber;
  }
  
  /**
   * Creates the board for gomoku game
   * Puts pieces on the board
   * @param primaryStage the main window
   */
  public void start(Stage primaryStage) {
    
    //Sets the default game
    //19x19 board and 5 as a win number 
    if(this.getParameters().getRaw().size() == 0) { 
      this.setWinNumber(5);
      this.setRowNumber(19);
      this.setColumnNumber(19);
    }
    
    //Sets the board with one parameter - win number
    //Sets 19x19 board     
    if(this.getParameters().getRaw().size() == 1) { 
      try {
        if(Integer.parseInt(this.getParameters().getRaw().get(0)) >= 4){
          this.setWinNumber(Integer.parseInt(this.getParameters().getRaw().get(0)));
          this.setRowNumber(19);
          this.setColumnNumber(19);
        }
        else {
          System.out.println("Win number must be at least 4");
        }
      }
      //if anything except numbers is entered
      catch (NumberFormatException e) {
        System.out.println("Please only enter numbers");
      }
    }
    
    //Sets the board with two parameters - row number and column number
    //Sets 5 as a win number     
    if(this.getParameters().getRaw().size() == 2) {
      try { 
        this.setWinNumber(5);
        this.setRowNumber(Integer.parseInt(this.getParameters().getRaw().get(0)));
        this.setColumnNumber(Integer.parseInt(this.getParameters().getRaw().get(1)));
      }
      //if anything except numbers is entered
      catch (NumberFormatException e) {
        System.out.println("Please only enter numbers");
      }
    }
    
    //Sets the board with there parameters - win number, row number and column number
    try {
      if(this.getParameters().getRaw().size() == 3 ) {
        try {
          if(Integer.parseInt(this.getParameters().getRaw().get(0)) >= 4) {
            this.setWinNumber(Integer.parseInt(this.getParameters().getRaw().get(0)));
            this.setRowNumber(Integer.parseInt(this.getParameters().getRaw().get(1)));
            this.setColumnNumber(Integer.parseInt(this.getParameters().getRaw().get(2)));
          }
          else {
            System.out.println("Win number must be at least 4");
          }
        }
        //if anything except numbers is entered
        catch (NumberFormatException e) {
          System.out.println("Please only enter numbers");
        }
      }
      
      //if more than 3 parameters are entered 
      if( this.getParameters().getRaw().size() > 3) {
        throw new IllegalArgumentException("Too many arguments. Please enter 3 or less arguments.");
      }
    }
    catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    
    BorderPane pane = new BorderPane();
    GridPane gridpane = new GridPane();
    //board stores appropriate number of rows and columns
    setBoard(new int[getRowNumber()][getColumnNumber()]);
    
    //creates the gridpane of buttons and sets them on action
    for(int i = 0; i < getRowNumber(); i++) {
      for(int j = 0; j < getColumnNumber(); j++) {
        Button button = new Button();
        button.setPrefHeight(25);
        button.setPrefWidth(25);
        button.setMinHeight(20);
        button.setMinWidth(20);
        button.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, new Insets(1))));
        button.setOnAction(new OnClick(i, j));
        gridpane.add(button, j, i);
      }
    }
    
    pane.setCenter(gridpane);
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);            
    primaryStage.show();
    
  }
  
  /**
   * The method counts the number of pieces of the same color in a given direction starting from a specific button
   * @param board is the array with current board situation
   * @param x is the x-coordinate of the button the count stars from
   * @param y is the y-coordinate of the button the count stars from
   * @param direction is the direction in which the buttons should be counted - 8 directions, each represented by numbers 1-8
   * @return int This returns the number of pieces of the same color in a given direction starting from a specific button
   */
  public int numberInLine(int[][] board, int x, int y, int direction) {
    //the count does not include the button we are starting with
    int count = 1;
    //sets by how how much we are moving in x-direction with each step
    int dx = getXDirection(direction);
    //sets by how how much we are moving in y-direction with each step
    int dy = getYDirection(direction);
    try {
      //stores the color of the starting button from the parameters
      int btnColor = board[x][y];
      //the loop stops once it reaches the button of different color
      while(board[x + dx][y + dy] == btnColor) {
        //update x location
        x += dx;
        //update y location
        y += dy;
        count++;
      }
      return count;
    }
    //in case if the last button is on the edge of the board
    catch (ArrayIndexOutOfBoundsException e) {
      return count;
    }
  }
  
  /**
   * The method tells whether in there is an empty button in the end
   * @param board is the array with current board situation
   * @param x is the x-coordinate of the button the count stars from
   * @param y is the y-coordinate of the button the count stars from
   * @param direction is the direction in which the buttons should be counted - 8 directions, each represented by numbers 1-8
   * @return boolean This returns whether there is an empty button in the end
   */
  
  public boolean isOpen(int[][] board, int x, int y, int direction) {
    //sets by how how much we are moving in x-direction with each step
    int dx = getXDirection(direction);
    //sets by how how much we are moving in y-direction with each step
    int dy = getYDirection(direction);
    try {
      //stores the color of the starting button from the parameters
      int color = board[x][y];
      //stores the next button position
      int nextButton = board[x + dx][y + dy];
      //loops until reaches an empty square
      while(nextButton != 0) {
        //check for the same color
        if(nextButton == color) {
          //update x location
          x += dx;
          //update y location
          y += dy;
          //update the location of the next button
          nextButton = board[x + dx][y + dy];
        }  
        else {
          //in case if there is a different color in the end
          return false;
        }
      }
      //if the loop reaches an empty square 
      return true;
    } 
    //in case if the last button is on the edge of the board
    catch(ArrayIndexOutOfBoundsException e){
      return false;
    }
    catch(NullPointerException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
  
  
  /**
   * The method defines the x direction and dx in that direction
   * each direction represents a number between 1 and 8
   * 1 points at 12.00 O'clock, all the following are incremented by 45 degrees
   * @param direction is the direction specified by a number between 1 and 8
   * @return int This returns the change in x direction within the specified direction
   */
  public int getXDirection(int direction) {
    int[][] params = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
    //returns each cell in the direction array params
    int[] displacement = params[direction - 1];
    //returns the second value of the cell from above - the change in rows
    return displacement[1];
  }
  
  /**
   * The method defines the y direction and dy in that direction
   * each direction represents a number between 1 and 8
   * 1 points at 12.00 O'clock, all the following are incremeted by 45 degrees
   * @param direction is the direction specified by a number between 1 and 8
   * @return int This returns the change in y direction within the specified direction
   */
  public int getYDirection(int direction) {
    int[][] params = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
    //returns each cell in the direction array params
    int[] displacement = params[direction - 1];
    //returns the first value of the cell from above - the change in columns
    return displacement[0];
  }
  
  /**
   * A class to represent the button action
   */
  public class OnClick implements EventHandler<ActionEvent> {
    
    /**stores whether the button is taken or not*/
    private boolean isSet = false;
    /**stores row number of the current button*/
    private int row;
    /**stores column number of the current button*/
    private int column;
    
    /**
     * A constructor for OnClick class
     * @param x is the row number of the button
     * @param y is the column of the button
     */
    public OnClick(int row, int column) {
      this.row = row;
      this.column = column;
    }
    
    /**
     * The method returns the row number for the current button 
     * @return int This returns the row number for the current button 
     */
    public int getRow() {
      return row;
    }
    
    /**
     * The method sets the row number for the current button
     * @param row is the row number of the current button 
     */
    public void setRow(int row) {
      this.row = row;
    }
    
    /**
     * The method returns the column number of the current button
     * @return int This returns the column number of the current button
     */
    public int getColumn() {
      return column;
    }
    
    /**
     * The method sets the column number for the current button
     * @param column is the column number for the current button
     */
    public void setColumn(int column) {
      this.column = column;
    }
    
    /**
     * The method sets an appropriate action for the button when clicked.
     * makes sure that the button is empty to put the piece on it
     * put the piece and alternates colors
     * makes sure the conditions are met for the piece to be put on the button (3-3 and 4-4 rules)
     * checks is the game is won
     * @param e is the ActionEvent
     */
    public void handle(ActionEvent e) {
      //gets source of the button that was clicked
      Button button = (Button) e.getSource();
      //allows to put the piece on the button if the button is empty and the game is not over yet
      if(!isSet && !gameWon) {
        setButtonBackground(button);
        //checks for 3-3 and 4-4 before putting a piece on the button
        //if the rules are not passed, puts a "blank" button, instead of a piece
        if(!rule4Passed() || !rule3Passed()) {
          button.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, new Insets(1))));
        }
        //if the rules are passed, sets the button, swaps colors and checks whether the move was winning
        else {
          checkWin(getPlayer());
          isSet = true;
          swapColors();
        }
      } 
    }
    
    /**
     * The method sets the appropriate piece on the board
     * @param button is the current button
     */
    public void setButtonBackground(Button button) {
      button.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, new Insets(1)),
                                          new BackgroundFill(getCurrentColor(), new CornerRadii(50), new Insets(2))));
      //sets the correct current player
      placeCurrentPlayer(getBoard());
    }
    
    /**
     * The method places the appropriate player (1 or 2) in the appropriate place on the board
     * so the board of ints corresponds to gridpane
     * @param board is the board of integers to represent the current board situation
     */
    public void placeCurrentPlayer(int[][] board) {
      int player = getPlayer();
      board[getRow()][getColumn()] = player;
      setPlayer(player);
    }
    
    /**
     * The method counts pieces in 4 directions (horiz, vert, diag1 and diag2)
     * Direction is represented by numbers from 1 to 4
     * @param direction is the direction in which the pieces will be counted
     * @return int This returns the number of pieces in each direction
     */ 
    public int count4Directions(int direction) {
      int counter = 0;
      counter += numberInLine(getBoard(), getRow(), getColumn(), direction);
      counter += numberInLine(getBoard(), getRow(), getColumn(), direction + 4);
      return counter;
    }
    
    /**
     * The method tells if the chains of pieces are open in 4 directions (horiz, vert, diag1 and diag2)
     * @param direction is the direction in which the pieces will be examined
     * @return boolean This returns whether the chains of pieces are open in 4 directions
     */
    public boolean isOpen4Directions(int direction) {
      return (isOpen(getBoard(), getRow(), getColumn(), direction)) && (isOpen(getBoard(), getRow(), getColumn(), direction + 4));
    }
    
    /**
     * The method makes sure that the 4-4 rule is passed or not
     * @return boolean This returns whether the 4-4 rule was passed
     */
    public boolean rule4Passed() {
      //sets the number for 4-4 rule
      int max4 = getWinNumber() - 1;
      int counter = 0;
      //counts the number of pieces in every direction
      for(int direction = 1; direction <= 4; direction++) {
        //counts the number of fours created by pieces of the same color
        if(count4Directions(direction) - 1 == max4) {
          counter++;
        }
      }
      //the rule is passed only if there are none or one instance of "four" created
      return counter < 2;
    }
    
    /**
     * The method makes sure that the 3-3 rule is passed or not
     * @return boolean This returns whether the 3-3 rule was passed
     */
    public boolean rule3Passed() {
      //sets the number for 3-3 rule
      int max3 = getWinNumber() - 2;
      int openSpots = 0;
      int counter = 0;
      boolean passed = false;
      //counts pieces in every direction
      for(int direction = 1; direction <= 4; direction++) {
        //counts the number of threes created by pieces of the same color as the current color
        if (count4Directions(direction) - 1 == max3) {
          counter++;
          //counts the number of open spots in the end in each direction created by pieces of the same color
          if(isOpen4Directions(direction)) { 
            openSpots++;
          }
        }
      }
      //the rule is passed if there are none or one instance of "three" created 
      if(counter < 2) {
        passed = true;
      }
      //the rule is also passed if there are multiple threes but the ends of these threes are not open
      else {
        if(openSpots < counter) //
          passed = true;
      }
      return passed;
    }
    
    /**
     * The method checks for win number being created on the board with pieces of the same color
     * @param player is the recently played button
     */
    public void checkWin(int player) {
      // the numberInLine method does not account for the button the recent button
      int counter = 0;
      // checks the number of pieces in 4 direction - horizontally, vertically, and
      // diagonally
      for (int direction = 1; direction <= 4; direction++) {
        if (getPlayer() != 0) {
          counter = count4Directions(direction);
          //counter will count the starting piece twice
          if (counter - 1 == getWinNumber()) {
            announceVictory(getPlayer());
            return;
          }
          // if the number of buttons don't add up to the win number in one direction,
          // sets counter to 0
          else {
            counter = 0; 
          }
        }
      }
    }
    
    /**
     * The method states which side won the game
     * @param player is the recently played button
     */
    public void announceVictory(int player) {
      String color = "";
      //gets the color of the recently played button
      if(getPlayer() == 1){
        color = "Black";
      } 
      else {
        color = "White";
      }
      //sets the game to be won
      gameWon = true;
      System.out.println(color + " won the game");
    }
    
    /**
     * The method swaps/alternates colors of the pieces
     */
    public void swapColors() {
      if(getCurrentColor() == Color.WHITE) {
        setCurrentColor(Color.BLACK);
        setPlayer(1); 
      }
      else {
        setCurrentColor(Color.WHITE);
        setPlayer(2); 
      }
    }
  }
  
  /**
   * The method to launch the program
   * @param args  The command line arguments. The arguments are passed on to the JavaFX application.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
