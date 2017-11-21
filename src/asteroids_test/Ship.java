package asteroids_test;

import java.awt.*;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
public class Ship {
	final double[] origXPts={14,-10,-6,-10},origYPts={0,-8,0,8},
			origFlameXPts={-6,-23,-6},origFlameYPts={-3,0,3};
	final int radius=6;
	double x, y, angle, xVelocity, yVelocity, acceleration,
	velocityDecay, rotationalSpeed;
	boolean turningLeft, turningRight, accelerating, active;
	int[] xPts, yPts, flameXPts, flameYPts;
	int shotDelay, shotDelayLeft;
int rocketflick;
ToneRun tone=new ToneRun();
SoundLoader audio=null;
Thread audiothread;

public Ship(double x, double y, double angle, double acceleration,double velocityDecay, double rotationalSpeed,int shotDelay){
//this.x refers to the Ship's x, x refers to the x parameter
this.x=x;
this.y=y;
this.x=Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
this.y=Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;

this.angle=angle;this.angle=-Math.PI/2;
this.acceleration=acceleration;
this.velocityDecay=velocityDecay;
this.rotationalSpeed=rotationalSpeed;
xVelocity=0; // not moving
yVelocity=0;
turningLeft=false; // not turning
turningRight=false;
accelerating=false; // not accelerating
active=false; // start off paused
xPts=new int[4]; // allocate space for the arrays
yPts=new int[4];
flameXPts=new int[3];
flameYPts=new int[3];
this.shotDelay=shotDelay; // # of frames between shots
shotDelayLeft=0; // ready to shoot

new Thread(){
	@Override
	public void run(){
final float SAMPLE_RATE = 8000f;

try {int freq=30;
double freq1=30.4;
AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,true);
 SourceDataLine sdl;
	sdl = AudioSystem.getSourceDataLine(af); sdl.open(af);
 sdl.start();
	 while(true){  freq1=Math.abs(((getXVel()+getYVel())*30.0)/40.0);
		 byte[] buf = new byte[(int) (SAMPLE_RATE/ 100)];
		  for (int i=0; i<buf.length; i++) {
		   //   System.out.println(freq);
		      double angle = i / (SAMPLE_RATE / freq) * freq1 * Math.PI;
		    
		      //if(i%2==1){
		      buf[i] = (byte)(Math.sin(angle)* 127.0 * .4F);// );
		      //}else{
		    	 // buf[i] = (byte)(Math.cos(angle) * 127.0 * .4F);
		    //  }
		      
		    //  sdl=AudioSystem.getAudioInputStream();
		 		//*2000/20;
		     if(isAccelerating()){
		   //  sdl.write(buf,0,buf.length);
		     }else{
		    	 sdl.flush();
		     }
		  //sdl.flush();
		  }}} catch (LineUnavailableException e1) {
		 	// TODO Auto-generated catch block
		 	e1.printStackTrace();
		 }
	}}.start();
}

public void draw(Graphics g){
	//super.paintComponent(g);
	 Graphics2D g2d1=(Graphics2D)g.create();
    RenderingHints hints1 = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				RenderingHints render1 =new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
				hints1.add(render1);
				g2d1.setRenderingHints(hints1);
				//g2d1.setColor(new Color(0,0,0));
				//g2d1.setStroke(new BasicStroke(4));
				//g2d1.drawOval(200,200,200,200);
//rotate the points, translate them to the ship's location (by
//adding x and y), then round them by adding .5 and casting them
//as integers (which truncates any decimal place)
if(accelerating && active){ // draw flame if accelerating
for(int i=0;i<3;i++){
	
flameXPts[i]=(int)(origFlameXPts[i]*Math.cos(angle)-
origFlameYPts[i]*Math.sin(angle)+
x+.5);
flameYPts[i]=(int)(origFlameXPts[i]*Math.sin(angle)+
origFlameYPts[i]*Math.cos(angle)+
y+.5);
}
rocketflick++;
if(rocketflick>=1&rocketflick<3){//sets the rate of the flickering effect of the flame
g2d1.setColor(Color.red); //set color of flame
g2d1.fillPolygon(flameXPts,flameYPts,3); // 3 is # of points
}
else if(rocketflick>3){//Don't forget to change this too if editing flicker speed
 rocketflick=0;	
}

}
//calculate the polgyon for the ship, then draw it
for(int i=0;i<4;i++){
xPts[i]=(int)(origXPts[i]*Math.cos(angle)- //rotate
origYPts[i]*Math.sin(angle)+
x+.5); //translate and round
yPts[i]=(int)(origXPts[i]*Math.sin(angle)+ //rotate
origYPts[i]*Math.cos(angle)+
y+.5); //translate and round
}
g2d1.setClip((int)x-15,(int)y-15,30,30);
if(active) // active means game is running (not paused)
g2d1.setColor(Color.white);
else // draw the ship dark gray if the game is paused
g2d1.setColor(Color.darkGray);
g2d1.fillPolygon(xPts,yPts,4); // 4 is number of points
}
public void move(int scrnWidth, int scrnHeight){
	if(shotDelayLeft>0) //move() is called every frame that the game
		shotDelayLeft--; //is run; this ticks down the shot delay
	if(turningLeft) //this is backwards from typical polar coordinates
		angle-=rotationalSpeed; //because positive y is downward.
	if(turningRight) //Because of that, adding to the angle is
		angle+=rotationalSpeed; //rotating clockwise (to the right)
	if(angle>(2*Math.PI)) //Keep angle within bounds of 0 to 2*PI
		angle-=(2*Math.PI);
		else if(angle<0)
			angle+=(2*Math.PI);

	if(accelerating){ //adds accel to velocity in direction pointed
		//calculates components of accel and adds them to velocity
		xVelocity+=acceleration*Math.cos(angle);
		yVelocity+=acceleration*Math.sin(angle);
	}
	
	x+=xVelocity; //move the ship by adding velocity to position
	y+=yVelocity;
	xVelocity*=velocityDecay; //slows ship down by percentages
	yVelocity*=velocityDecay; //(velDecay should be between 0 and 1)

	if(x<0) //wrap the ship around to the opposite side of the screen
		x+=scrnWidth; //when it goes out of the screen's bounds
		else if(x>scrnWidth)
			x-=scrnWidth;
	if(y<0)
		y+=scrnHeight;
		else if(y>scrnHeight)
			y-=scrnHeight;


	
}

public void resetShip(){
	System.out.println("boom");
	shipBoom();
	Ship.this.x=Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
	Ship.this.y=Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
	Ship.this.xVelocity=0;
	this.setAccelerating(false);
	this.setTurningLeft(false);
	this.setTurningRight(false);
	Ship.this.yVelocity=0;
	Ship.this.angle=-Math.PI/2;
	Ship.this.setActive(false);
	
}
void shipBoom(){
	try {///GameV2/src/resources/Troll_Song.wav
		audio = new SoundLoader("/resources/Ship_Crash.wav",false);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	audiothread=new Thread(audio);
	//audio.playSound();
	audiothread.run();
}
public void setAccelerating(boolean accelerating){
this.accelerating=accelerating;
}
public void setTurningLeft(boolean turningLeft){
this.turningLeft=turningLeft;
}
public void setTurningRight(boolean turningRight){
this.turningRight=turningRight;
}
public boolean isAccelerating(){
	return accelerating;
}
public boolean isTurningLeft(){
	return turningLeft;
}
public boolean isTurningRight(){
	return turningRight;
}
public double getX(){
return x;
}
public double getY(){
return y;
}
public double getXVel(){
return this.xVelocity;
}
public double getYVel(){
return this.yVelocity;
}
public double getRadius(){
return radius;
}
public void setActive(boolean active){
this.active=active;
}
public boolean isActive(){
return active;
}
public boolean canShoot(){
if(shotDelayLeft>0) //checks to see if the ship is ready to
return false; //shoot again yet or needs to wait longer
else
return true;
}
public Shot shoot(){
shotDelayLeft=shotDelay; //set delay till next shot can be fired
//a life of 40 makes the shot travel about the width of the
//screen before disappearing
new Thread(){
	
@Override
	public void run(){
tone.runTone(1600, 100, true, false, true, .15F);
tone.runTone(1300, 70,true, false, true, .11F);
tone.runTone(300, 50, true, false, true, .12F);
//Thread.currentThread().destroy();
//tone.runEngine(600, 100, true, .4F);
}}.start();
return new Shot(x,y,angle,xVelocity,yVelocity,60,20,true);
}}