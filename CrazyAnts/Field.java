import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color;


public class Field extends World
{
    private GreenfootImage myGrid;
    private Cell[][] myCells;
    private int myWidth, myHeight, myCellSize;
    
    public Field()
    {    
        super(35, 35, 10);
        myHeight = getHeight();
        myWidth = getWidth();
        myCellSize = getCellSize();
        drawGrid();
        createCells();
        placeAnts(4);
    }
   
    public void placeAnts(int numAnts) {
        for(int i = 0; i < numAnts; i++) {
             int x = Greenfoot.getRandomNumber(myHeight);
             int y = Greenfoot.getRandomNumber(myWidth);
            addObject(new CrazyAnt(), x, y);
            myCells[x][y].setState(true);
        }
    }
    
    public void act() 
    {
        for(int i = 0; i < myHeight; i++)
            for(int j = 0; j < myWidth; j++)
                myCells[i][j].saveLastState();
    }    
    
    private void drawGrid() {
        myGrid = getBackground();
       
        
        int endY = myCellSize * myHeight;
        int endX = myCellSize * myWidth;
        
        for (int i = 0; i < endX; i += myCellSize)
            myGrid.drawLine(i, 0, i, endY);
        for (int i = 0; i < endY; i += myCellSize)
            myGrid.drawLine(0, i, endX, i);
    }
    
    private void createCells() {
        myCells = new Cell[myHeight][myWidth];
        for (int i = 0; i < myHeight; i++)
            for (int j = 0; j < myWidth; j++) {
                Cell c = new Cell(myCellSize);
                myCells[i][j] = c;

                addObject(c, i, j);
            }
    }
}
