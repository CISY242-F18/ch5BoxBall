import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * @author Michal Legocki
 * @version 2018.10.15
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }
    
    /**
     * simulate multiple balls bouncing within a square
     * 
     * @param ballAmount the amount of balls to be simulated, limit 5-25
     */
    public void boxBounce(int ballAmount){
        ArrayList<BoxBall> balls = new ArrayList<BoxBall>();
        Random rnd = new Random();
        
        int rightWall = (int)myCanvas.getSize().getWidth()-50;
        int topWall = 50;
        int botWall = (int)myCanvas.getSize().getHeight()-50;
        int leftWall = 50;
        
        myCanvas.setVisible(true);
        
        myCanvas.drawLine(leftWall,botWall,rightWall,botWall);
        myCanvas.drawLine(leftWall,topWall,rightWall,topWall);
        myCanvas.drawLine(leftWall,botWall,leftWall,topWall);
        myCanvas.drawLine(rightWall,botWall,rightWall,topWall);
        
        if(ballAmount >=5 && ballAmount <= 25){
            for(int c = ballAmount; c>0; c--){
                balls.add(new BoxBall(rnd.nextInt(rightWall-76)+51,
                    rnd.nextInt(botWall-76)+51,rnd.nextInt(16)+10 ,
                    Color.BLACK,botWall,leftWall,topWall,rightWall,myCanvas));
            }
        }else{
            for(int c = rnd.nextInt(26)+5; c>0; c--){
                balls.add(new BoxBall(rnd.nextInt(rightWall-76)+51,
                    rnd.nextInt(botWall-76)+51,rnd.nextInt(16)+10 ,
                    Color.BLACK,botWall,leftWall,topWall,rightWall,myCanvas));
            }
        }
        
        boolean finished = false;
        
        while(!finished){
            myCanvas.wait(50);
            for(BoxBall ball : balls){
                ball.move();
            }
            myCanvas.drawLine(leftWall,botWall,leftWall,topWall);
        }
        
        
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
