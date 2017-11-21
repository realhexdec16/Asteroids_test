package asteroids_test;

import java.awt.*;
public class Asteroid {
double x, y, xVelocity, yVelocity;
int hitsLeft, numSplit,divisor; 
int type;

//Asteroid variants:
/*
int[] Axpoints={10,20,40,60,70,75,60,40,20,0,0,0};
int[] Aypoints={10,-5,0,10,20,40,70,60,70,50,40,20};
	Polygon asteroidrA=new Polygon(Axpoints,Aypoints,12);

int[] Bxpoints={-10,10,40,60,60,75,60,30,20,0,-10,0};
int[] Bypoints={0,-20,-10,-5,20,40,60,70,60,60,40,20};
	Polygon asteroidB=new Polygon(Bxpoints,Bypoints,12);
	
int[] Cxpoints={-10,0,40,70,60,70,60,40,20,0,-10,-20};
int[] Cypoints={10,-5,-20,0,20,40,60,70,60,60,40,20};
	Polygon asteroidC=new Polygon(Cxpoints,Cypoints,12);
	
int[] Dxpoints={0,20,40,60,60,70,60,40,20,-10,0,0};
int[] Dypoints={10,-30,0,-5,20,40,60,50,60,60,40,20};
	Polygon asteroidD=new Polygon(Dxpoints,Dypoints,12);
*/
int[] Astx={10,25,50,65,75,75,65,50,25, 10, 0, 0};
int[] Asty={10, 0, 0,10,25,50,65,75,75,65,50,25};
	Polygon asteroide=new Polygon(Astx,Asty,12);
	
int[] Xpoints;
int[] Ypoints;
Point minXY;
Point maxXY;

int astWidth,astHeight;
Polygon asteroid;
	public Asteroid(double x, double y, double minVelocity,
		double maxVelocity, int hitsLeft, int numSplit,int divisor) {

type=(int)(Math.random()*5)+1;
//type=3;
this.x=x;
this.y=y; 
this.hitsLeft=hitsLeft; //number of shots left to destroy asteroid 
this.numSplit=numSplit;//number of smaller frag 


//calc random velocity 
double vel=minVelocity + Math.random()*(maxVelocity-minVelocity),
	//dir=(2*Math.PI)*Math.random(); 
dir=Math.random()*360;
xVelocity=vel*Math.cos(dir); 
yVelocity=vel*Math.sin(dir); 


Xpoints=getXpts();
Ypoints=getYpts();
this.divisor=divisor;
dividePoints(this.divisor);
minXY=getMinXY();
maxXY=getMaxXY();

astWidth=(int) (Math.abs(minXY.getX())+Math.abs(maxXY.getX()));
astHeight=(int) (Math.abs(minXY.getY())+Math.abs(maxXY.getY()));
setAsteroid((int)x,(int)y);
}
public void move(int scrnWidth, int scrnHeight) {
x+=xVelocity; //move asteroid 
y+=yVelocity;

/*
 * wrap around code allowing asteroid to go
 * off screen to a distance equal to its radius
 * before entering other side
 */
if(x<0-astWidth)
	x+=scrnWidth+astWidth; 
else if(x>scrnWidth)
	x-=scrnWidth+astWidth; 
if(y<0-astHeight)
	y+=scrnHeight+astHeight; 
else if (y>scrnHeight)
	y-=scrnHeight+astHeight;

}
public void draw(Graphics g) { 
	Graphics2D g2d1=(Graphics2D)g.create();
	g2d1.setColor(Color.white); //asteroid color 
	//asteroid center @ (x,y) 
	g2d1.setStroke(new BasicStroke(3));
	setAsteroid((int)x,(int)y);
	g2d1.drawPolygon(asteroid);
}

private int[] getXpts(){
	for(int i=0;i<Astx.length;i++){
		Astx[i]+=(int)(Math.random()*30)+1;
	}

	return Astx;
	/*
	if(type==1){
		return Axpoints;
	}
	else if(type==2){
		return Bxpoints;
	}
	else if(type==3){
		return Cxpoints;
	}
	else if(type==4){
		return Dxpoints;
	}
	else if(type==5){
		return Astx;
	}
	else{
		System.out.println("No array of points initalized");
		return null;
	}*/
}
private int[] getYpts(){
	for(int i=0;i<Asty.length;i++){
		Asty[i]+=(int)(Math.random()*30)+1;
	}
	return Asty;
	/*
	if(type==1){
		return Aypoints;
	}
	else if(type==2){
		return Bypoints;
	}
	else if(type==3){
		return Cypoints;
	}
	else if(type==4){
		return Dypoints;
	}
	else if(type==5){
		return Asty;
	}
	else{
		System.out.println("No array of points initalized");
		return null;
	}*/
}
private Point getMinXY(){
	int x1=Xpoints[0];
	int y1=Ypoints[0];
	for(int X:Xpoints){//finds the smallest x
		if(X<x1){
			x1=X;
		}
	}
	for(int Y:Ypoints){//finds the smallest y
		if(Y<y1){
			y1=Y;
		}
	}
	return new Point(x1,y1);
}
private Point getMaxXY(){
	int x1=Xpoints[0];
	int y1=Ypoints[0];
	for(int X:Xpoints){//finds the largest x
		if(X>x1){
			x1=X;
		}
	}
	for(int Y:Ypoints){//finds the largest y
		if(Y>y1){
			y1=Y;
		}
	}
	return new Point(x1,y1);
}
private void dividePoints(int divisor){//used to shrink the asteroid based on numsplit - hitsleft
	int divideBy=this.divisor-this.hitsLeft;
	for(int x1=0;x1<Xpoints.length;x1++){
		if(divideBy==0)
		Xpoints[x1]*=1.75;
		
		else if(divideBy==1){}
		
		else{Xpoints[x1]/=1.5;}
	}
	for(int y1=0;y1<Ypoints.length;y1++){
		if(divideBy==0)
		Ypoints[y1]*=1.75;
		
		else if(divideBy==1){}
		
		else{Ypoints[y1]/=1.5;
		}}
		
		}
		
private void setAsteroid(int xamt,int yamt){
	int[] X=new int[Xpoints.length];
	int[] Y=new int[Ypoints.length];
	for(int x1=0;x1<Xpoints.length;x1++){
		X[x1]=Xpoints[x1]+xamt;
	}
	for(int y1=0;y1<Ypoints.length;y1++){
		Y[y1]=Ypoints[y1]+yamt;
	}
	asteroid=new Polygon(X,Y,12);
};
public Asteroid createSplitAsteroid (double minVelocity, double maxVelocity){

	/*
	 * when asteroids get hit by a shot this method is called
	 * numSplit times by AsteroidsGame to create numSplit smaller asteroids
	 * Divides radius by sqrt(numSplit) makes the sum of the areas
	 * taken up by the smaller asteroids equal to the area of THIS asteroid 
	 * each smaller asteroid has one less hit left before annihilation 
	 */
	return new Asteroid(x, y, minVelocity, maxVelocity, this.hitsLeft-1, numSplit,this.divisor);
	
}
	public boolean shipCollision(Ship ship){
	
	/*
	 * use distance formula to check if ship is touching asteroids
	 * if the sum of the radii is grater than the distance between center of ship
	 * and the asteroid they are in contact
	 */
	try{
	return asteroid.contains(ship.getX(), ship.getY());
	}catch(Exception ex){
		System.out.println("Error in contains");
	return true;
	}
}
public boolean shotCollision(int x,int y
		//Shot shot
		){

	//same as shipCollision but shotRadius=0
	return asteroid
			.contains(
			x,
			y);
}
public int getHitsLeft() { 
	//decides whether to split or get destroyed
	return hitsLeft; 
} 
public int getNumSplit() { 
	return numSplit; 
}}
