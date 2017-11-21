package asteroids_test;

import java.awt.Toolkit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone implements Runnable{
	int freq;
	int length;
	boolean tf;
	boolean rise;
	boolean fall;
	float volume;
	public Tone(int freq,int length,boolean tf,boolean rise,boolean fall,float volume){
		this.freq=freq;
		this.length=length;
		this.tf=tf;
		this.rise=rise;
		this.fall=fall;
		this.volume=volume;
	}
     public static float SAMPLE_RATE = 6000f;

     public void sound(int hz, int msecs, double vol)
      throws LineUnavailableException {

         if (hz <= 0)
             throw new IllegalArgumentException("Frequency <= 0 hz");

         if (msecs <= 0)
             throw new IllegalArgumentException("Duration <= 0 msecs");

         if (vol > 1.0 || vol < 0.0)
             throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");

         byte[] buf = new byte[(int)SAMPLE_RATE * msecs / 1000];
       //  ArrayList
double freq=2;
//while(true)
	//freq=freq+.001;

         for (int i=0; i<buf.length; i++) {
             double angle = i / (SAMPLE_RATE / hz) * freq * Math.PI;
             buf[i] = (byte)(Math.sin(angle) * 127.0 * vol);
             if(rise==true){
             freq=freq+.001;}
         
         if(fall==true){
        	 freq=freq-.001;
         }}
         // shape the front and back 10ms of the wave form
         for (int i=0; i < SAMPLE_RATE / 100.0 && i < buf.length / 2; i++) {
             buf[i] = (byte)(buf[i] * i / (SAMPLE_RATE / 100.0));
             buf[buf.length-1-i] =
              (byte)(buf[buf.length-1-i] * i / (SAMPLE_RATE / 100.0));
         }

         AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,tf,tf);
         SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
         sdl.open(af);
         sdl.start();
         sdl.write(buf,0,buf.length);
         sdl.drain();
         
			//Thread.currentThread().stop();//.destroy();//.join(5);
         Thread.currentThread();
         try {
			Thread.sleep(5);
			//af.sl
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
         sdl.close();
         
         try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	@Override
	public void run() {
	try {
		int i=(int)System.currentTimeMillis();
		Tone.this.sound(freq,length,volume);
		//this.finalize();//dispose();
		int j=(int)System.currentTimeMillis();
		System.out.println(j-i);
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
}