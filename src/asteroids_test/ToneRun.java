package asteroids_test;

import javax.sound.sampled.LineUnavailableException;

public class ToneRun {
	public Tone tone;
	public ToneRun(){
		
	}
	public void runTone(final int freq,final int length,boolean tf,boolean rise,boolean fall,final float volume){
	tone=new Tone(freq,length,tf,rise,fall,volume);
	//tone.run();
	Thread t=new Thread(tone);
	new Thread(){
		
	@Override
	public void run(){
	try {
		tone.sound(freq, length, volume);
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}}.start();
	t.start();
	/*try {
		t.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	//System.out.println(t.activeCount());
	}
}