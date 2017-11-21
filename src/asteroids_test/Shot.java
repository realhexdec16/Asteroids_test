package asteroids_test;

import java.awt.*;
public class Shot{
final double shotSpeed =12;
double x,y,xVelocity,yVelocity;
int lifeLeft,shotDelay;
public Shot(double x, double y, double angle, double shipXVel, double shipYVel, int lifeLeft,int shotDelay,boolean friendly){
this.x=x;
this.y=y;
this.shotDelay=shotDelay;
// add the velocity of the ship to the velocity the shot velocity
// (so the shot's velocity is relative to the ship's)
xVelocity=shotSpeed*Math.cos(angle)+shipXVel;
yVelocity=shotSpeed*Math.sin(angle)+shipYVel;
// the number of frames the shot will last for before disappearing if it doesn't hit anything
this.lifeLeft=lifeLeft;
final ToneRun tone=new ToneRun();
new Thread(){
	
	@Override
	public void run(){
//tone.runTone(1600, 100, true, false, true, .8F);
//tone.runTone(1300, 70,true, false, true, .2F);
//tone.runTone(300, 50, true, false, true, .3F);
	}}.start();
	
	} 

public void move(int scrnWidth, int scrnHeight) { 
	lifeLeft--; //makes shot disappear
	x+=xVelocity; //move shot
	y+=yVelocity; 
	if(x<0) //wraps shot 
		x+=scrnWidth; 
	else if(x>scrnWidth)
		x-=scrnWidth; 
	if(y<0)
	y+=scrnHeight; 
	else if(y>scrnHeight)
		y-=scrnHeight; 
	//this.draw(AsteroidsGame.getasteroidz().mainpanel.getGraphics());

}
public void draw(Graphics g) { 
	
	g.setColor(Color.yellow); //shot color
	/*
	 * draws circle of radius=3 
	 * centered at closest point 
	 * with integer coordinates (.5 added to x-1 and y-1 for rounding)
	 */
	//AsteroidsGame.getasteroidz().g.fillOval((int)(x-.5), (int)(y-.5), 3, 3 );
	g.fillOval((int)(x-.5), (int)(y-.5), 3, 3 );
}

public double getX() { 
	return x; 
} 
public double getY() {
	return y; 
} 

public int getLifeLeft() { 
	return lifeLeft; 

}}
