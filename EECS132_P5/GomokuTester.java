import org.junit.*;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;

public class GomokuTester {
  
//Gomoku class tests 
  
  @Test
  public void numberInLineTest() {
    
    Gomoku gomoku = new Gomoku();
    
    //should catch index out of bounds exception and return count (1)
    int[][] board = new int[0][0];
    assertEquals("test 0", 1, gomoku.numberInLine(board, 0, 0, 3));
    
    //the count is 1
    gomoku.setRowNumber(1);
    gomoku.setColumnNumber(1);
    assertEquals("test 1", 1, gomoku.numberInLine(board, 0, 0, 3));
    
    //the count is 4 - the length of the array
    board = new int[4][4];
    assertEquals("test Many", 4, gomoku.numberInLine(board, 0, 0, 3));
    
    //testing directions
    gomoku.setRowNumber(4);
    gomoku.setColumnNumber(4);
    board[0][1] = 1;
    board[1][1] = 1;
    board[2][1] = 1;
    assertEquals("test first", 3, gomoku.numberInLine(board, 2, 1, 1));
    
    board[0][1] = 2;
    assertEquals("test first pt.2", 2, gomoku.numberInLine(board, 2, 1, 1));
    
    board[0][3] = 2;
    board[1][2] = 2;
    board[2][1] = 2;
    assertEquals("test middle", 3, gomoku.numberInLine(board, 0, 3, 6)); 
    
    board[1][0] = 2;
    assertEquals("test last", 2, gomoku.numberInLine(board, 2, 1, 8));
  }
  
  
  @Test
  public void isOpenTest() {
    Gomoku gomoku = new Gomoku();
    
    //should catch index out of bounds exception and return false
    int[][] board = new int[0][0];
    assertFalse("test 0", gomoku.isOpen(board, 0, 0, 3));
    
    board = new int[1][1];
    assertFalse("test 1", gomoku.isOpen(board, 0, 0, 3));
    
    board = new int[4][4];
    assertTrue("test many", gomoku.isOpen(board, 0, 0, 3));
    
    
    board = new int[4][4];
    board[2][0] = 1;
    board[2][1] = 1;
    board[2][2] = 1;
    assertTrue("test first", gomoku.isOpen(board, 2, 0, 1));
    assertTrue("test middle", gomoku.isOpen(board, 2, 0, 3));
    
    board[2][2] = 2;
    assertFalse("test middle pt.2", gomoku.isOpen(board, 2, 0, 3));
    
    board[2][2] = 1;
    board[2][3] = 1;
    assertFalse("test middle pt.3", gomoku.isOpen(board, 2, 0, 3));
    
    board[1][1] = 2;
    assertTrue("test last", gomoku.isOpen(board, 1, 1, 8));
    
  }
  
  @Test
  public void getXDirection() {
    Gomoku gomoku = new Gomoku();
    
    assertEquals("the x displacent in the direction 1 is -1", -1, gomoku.getXDirection(1));
    
    assertEquals("the x displacent in the direction 4 is 1", 1, gomoku.getXDirection(4));
    
    assertEquals("the x displacent in the direction 7 is 0", 0, gomoku.getXDirection(7));
    
  }
  
  @Test
  public void getYDirection() {
    Gomoku gomoku = new Gomoku();
    
    assertEquals("the y displacent in the direction 1 is 0", 0, gomoku.getYDirection(1));
    
    assertEquals("the y displacent in the direction 4 is 1", 1, gomoku.getYDirection(4));
    
    assertEquals("the y displacent in the direction 7 is -1", -1, gomoku.getYDirection(7));
  }
  
  
// On Click class tests 
  @Test
  public void setButtonBackroundTest() {
    
    Gomoku gomoku = new Gomoku();
    Gomoku.OnClick onClick = gomoku.new OnClick(1, 1);
    
    int[][] board = new int[4][4];
    int[][] expected = new int[4][4];
    expected[1][1] = 1;
    onClick.placeCurrentPlayer(board);
    assertArrayEquals("the player at 1,1 should be changed to black (1)",expected, board);
    
    onClick.swapColors();
    onClick.setRow(2);
    expected[2][1] = 2;
    onClick.placeCurrentPlayer(board);
    assertArrayEquals("the player at 2,1 should be changed to white (2)", expected, board);
  }
  
  @Test
  public void  count4DirectionsTest() {
    Gomoku gomoku = new Gomoku();
    Gomoku.OnClick onClick = gomoku.new OnClick(1, 1);
    
    int[][] board = new int[4][4];
    board[0][1] = 1;
    board[1][1] = 1;
    board[2][1] = 1;
    assertEquals("test first direction", 3, onClick.count4Directions(1));
    
    board[0][3] = 2;
    board[1][2] = 2;
    board[2][1] = 2;
    onClick.setRow(0);
    onClick.setColumn(3);
    assertEquals("test middle direction", 3, onClick.count4Directions(2));
    
    board[1][0] = 2;
    onClick.setRow(2);
    onClick.setColumn(1);
    assertEquals("test last direction", 2, onClick.count4Directions(4));
    
  }
  
  @Test
  public void isOpen4Directions() {
    Gomoku gomoku = new Gomoku();
    Gomoku.OnClick onClick = gomoku.new OnClick(1, 1);
    
    int[][] board = new int[4][4];
    board[0][1] = 1;
    board[1][1] = 1;
    board[2][1] = 1;
    assertTrue("should return true", onClick.isOpen4Directions(1));
    
    board[2][1] = 1;
    board[3][1] = 1;
    assertFalse("should return flase", onClick.isOpen4Directions(1));
    
    board[3][1] = 2;
    assertFalse("should return flase", onClick.isOpen4Directions(1));
    
    board[0][3] = 2;
    board[1][2] = 2;
    board[2][1] = 2;
    onClick.setRow(0);
    onClick.setColumn(3);
    assertTrue("should return true", onClick.isOpen4Directions(2));
    
  }
  
  @Test
  public void rule4PassedTest() {
    Gomoku gomoku = new Gomoku();
    Gomoku.OnClick onClick = gomoku.new OnClick(0,0);
    gomoku.setWinNumber(5);
    
    int[][] board = new int[5][5];
    board[3][0] = 1;
    board[3][1] = 1;
    board[3][2] = 1; 
    board[0][3] = 1;
    board[1][3] = 1;
    board[2][3] = 1;
    onClick.setRow(3);
    onClick.setColumn(3);
    assertFalse("test many: the move creates two instances of 4 black pieces", onClick.rule4Passed());
    
    board[3][2] = 2;
    assertTrue("test one: the move creates one instance of 4 black pieces", onClick.rule4Passed());
    
    board[2][3] = 2; 
    assertTrue("test zero: the move creates 0 instance of 4 black pieces", onClick.rule4Passed());
    
    board = new int[5][5];
    board[2][1] = 2; 
    board[2][3] = 2;
    board[2][4] = 2;
    board[1][2] = 2;
    board[3][2] = 2;
    board[4][2] = 2;
    onClick.setRow(2);
    onClick.setColumn(2);
    assertFalse("test middle: the move creates two instances of 4 black pieces", onClick.rule4Passed());
    
    board[0][2]= 1;
    assertFalse("test middle pt.2: the move creates two instances of 4 black pieces", onClick.rule4Passed());
    
  }
  
  @Test
  public void rule3PassedTest() {
    Gomoku gomoku = new Gomoku();
    Gomoku.OnClick onClick = gomoku.new OnClick(0,0);
    
    gomoku.setWinNumber(5);
    int[][] board = new int[5][5];
    board[3][1] = 1;
    board[3][2] = 1; 
    board[1][3] = 1;
    board[2][3] = 1;
    onClick.setRow(3);
    onClick.setColumn(3);
    assertFalse("the move creates two instances of 3 black pieces with open ends", onClick.rule3Passed());
    
    board[3][2] = 2;
    assertTrue("the move creates one instance of 3 black pieces", onClick.rule3Passed());
    
    board[3][2] = 1;
    board[3][0] = 1;
    assertTrue("the move creates two instances of 3 black pieces but only one has an open end", onClick.rule3Passed());
    
    board[3][1] = 1;
    board[3][2] = 1; 
    board[1][3] = 1;
    board[2][3] = 1;
    board[4][3] = 2; 
    assertFalse("the move creates two instances of 3 black pieces with open ends", onClick.rule3Passed());
    
    board = new int[5][5];
    board[2][1] = 2; 
    board[2][3] = 2;
    board[1][2] = 2;
    board[3][2] = 2;
    onClick.setRow(2);
    onClick.setColumn(2);
    assertFalse("test middle: the move creates two instances of 3 white pieces", onClick.rule3Passed());
    
    board[2][4] = 1;
    assertTrue("test middle pt. 2: the move creates two instances of 3 white pieces but only one of them has open ends", onClick.rule3Passed());
    
  }
  
  @Test
  public void checkWinTest() {
    
    
    Gomoku gomoku = new Gomoku();
    Gomoku.OnClick onClick = gomoku.new OnClick(0,0);
    gomoku.setWinNumber(4);
    gomoku.setPlayer(1);
    
    int[][] board = new int[0][0];
    int player = 1;
    onClick.checkWin(player);
    assertEquals("test 0", 0, player);
    
    board = new int[1][1];
    onClick.checkWin(player);
    assertEquals("test 1", 0, player);
    
    board = new int[5][5];
    board[1][1] = 1; 
    board[2][2] = 1;
    board[3][3] = 1;
    onClick.checkWin(player);
    assertEquals("test many", 4, player);
    
    
    board[1][1] = 0;
    board[4][4] = 1;
    onClick.setRow(1);
    onClick.setColumn(1);
    onClick.checkWin(player);
    assertEquals("test first", 4, player);
    
    board[1][1] = 1;
    board[2][2] = 0;
    onClick.setRow(2);
    onClick.setColumn(2);
    onClick.checkWin(player);
    assertEquals("test middle", 4, player);
    
    board[2][2] = 1;
    board[4][4] = 0;
    onClick.setRow(4);
    onClick.setColumn(4);
    onClick.checkWin(player);
    assertEquals("test last", 4, player);
    
    board[1][1] = 0;
    onClick.checkWin(player);
    assertEquals("test last pt.2", 0, player);
    
  }
  
  @Test
  public void swapColorsTest() {
    Gomoku gomoku = new Gomoku();
    Gomoku.OnClick onClick = gomoku.new OnClick(0,0);
    
    gomoku.setCurrentColor(Color.WHITE);
    onClick.swapColors();
    assertEquals("player should be 1", 1, gomoku.getPlayer());
    
    onClick.swapColors();
    assertEquals("player should be 2", 2, gomoku.getPlayer());
    
  }
  
}
