package asteroids_test;

import java.awt.Toolkit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class EngineSound{
	public int freq;
	boolean tf;
	float volume;
	public EngineSound(int freq,boolean tf,float volume) throws LineUnavailableException{
		this.freq=freq;
		this.tf=tf;

		this.volume=volume;
	
     final float SAMPLE_RATE = 8000f;


/*      throws LineUnavailableException {

         if (this.freq <= 0)
             throw new IllegalArgumentException("Frequency <= 0 hz");

         //if (msecs <= 0)
            // throw new IllegalArgumentException("Duration <= 0 msecs");

         if (volume > 1.0 || volume < 0.0)
             throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");

         */
double freq1=1;
//while(true)
	AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,tf,tf);
         SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
         sdl.open(af);
         sdl.start();
         while(true){
		byte[] buf = new byte[(int)SAMPLE_RATE / 1000];
         for (int i=0; i<buf.length; i++) {
             double angle = i / (SAMPLE_RATE / this.freq) * freq1 * Math.PI;
             buf[i] = (byte)(Math.sin(angle) * 127.0 * volume);
             freq=Math.abs((int)((AsteroidsGame.getasteroidz().ship.getXVel()+
            		 AsteroidsGame.getasteroidz().ship.getYVel())*2000)/20);
             System.out.println(freq);
     		//*2000/20;
            sdl.write(buf,0,buf.length);
         //sdl.flush();
         }}
         // shape the front and back 10ms of the wave form
   

         
         
         

        // sdl.close();
     
	}
}