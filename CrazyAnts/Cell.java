import greenfoot.*;
import greenfoot.Color;
import java.util.List;
public class Cell  extends Actor
{
    Boolean         myFirstTime = true;
    Boolean         myState, myLastState;
    GreenfootImage  myBody;
    int             myNumNeighbors;
    int             myBodySize;
    Cell []         myNeighbors;
    
    public Cell(int cellSize) {
        super();
        myState = myLastState = false;
        myBody = new GreenfootImage(cellSize, cellSize);
        setImage(myBody);
        myBodySize = cellSize - 2;
        showState();
    }
    
    
    public void act() 
    {
        if (myFirstTime) {
            List neighbors = getNeighbours(1, true, Cell.class);
            myNumNeighbors = neighbors.size();
            myNeighbors = new Cell[myNumNeighbors];
            for(int i = 0; i < myNumNeighbors; i++)
                myNeighbors[i] = (Cell)neighbors.get(i);
            myFirstTime = false;
         }
        
    }
    
    public void saveLastState() {
        myLastState = myState;
    }

    public void setState(boolean state) {
        myState = state;
        showState();
    }
    
    public boolean getState() {
       return(myState);
    }

    public void flipState() {
        setState(!myState);
    }
    
    private int countNeighbors() {
        int sum = 0;
        for(int i = 0; i < myNumNeighbors; i++) 
            if (myNeighbors[i].myLastState)
                sum++;
        return(sum);
    }
    
    private boolean applyRule(int numNeighborsOn) {
        boolean state = myState;
        
        if (numNeighborsOn < 2)
            state = false;
        else if (numNeighborsOn > 3)
            state = false;
        else if (numNeighborsOn == 3)
            state = true;
            
        return(state);
    }
   
    private void showState() {
        if (myState) 
            myBody.setColor(Color.BLACK);
        else 
            myBody.setColor(Color.GREEN);
        myBody.fillRect(1, 1, myBodySize, myBodySize);
    }
}
