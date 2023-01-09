import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;

//DISCLAIMER MR EATON***  NOT ALL OF THIS CODE BELONGS TO US;
//N8 & ALEX WANTED TO MAKE AN ANT FARM SIMULATION AND TURNED TO GREENFOOT FOR
//THE HARD PARTS. WE JUST MADE IT BETTER
public class CrazyAnt extends Actor
{
    private int myLast, myBeforeLast;
    private boolean antDirection;
    private int antMovement;
   
    public CrazyAnt() {
        reset();
    }
    public void Setsize() {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() - 500, image.getHeight() - 500);
        setImage(image);
    }
    
    
    public void act() 
    {
        if (antMovement > getWorld().getHeight())
            reset();
                    
            
        
                        
        else if (getOneIntersectingObject(CrazyAnt.class) != null)
            reset();
            
        if (antMovement == (myLast + myBeforeLast)) {
            myBeforeLast = myLast;
            myLast = antMovement;
            antMovement = 1;
            turn();
            Greenfoot.playSound("AH.wav");            
        }
        move();
        antMovement++;
        Cell c = ((Cell)getOneIntersectingObject(Cell.class));
        if (c != null)
            c.flipState();
    }    
    
    
    public void turn() {
        if (antDirection)
            setRotation(getRotation() + 90);
        else
            setRotation(getRotation() - 90);
    }
    
    
    public void move()
    {
        double angle = Math.toRadians(getRotation());
        int x = (int) Math.round(getX() + Math.cos(angle));
        int y = (int) Math.round(getY() + Math.sin(angle));
        
        if (x < 0)
            x = getWorld().getWidth() - 1;
        else if (x > getWorld().getWidth() - 1)
            x = 0;
            
        if (y < 0)
            y = getWorld().getHeight() - 1;
        else if (y > getWorld().getHeight() - 1)
            y = 0;
            
        setLocation(x, y);
    }

    
    private void reset() {
        myBeforeLast = 0;
        myLast = 1;
        antMovement = 1;
        if(Greenfoot.getRandomNumber(2) == 0)
            antDirection = true;
        else
            antDirection = false;
        setRotation(90 * Greenfoot.getRandomNumber(4));
    }
}
