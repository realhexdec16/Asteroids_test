package asteroids_test;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
public class SoundLoader implements Runnable{
	public AudioPlayer Player = AudioPlayer.player;
	public AudioStream Stream = null;
	public AudioData data=null;
	InputStream inputStream=null;
	InputStream inputStream2=null;
	InputStream Stream2=null;
//	AudioFormat format=null;
	public ContinuousAudioDataStream loop;
	public String url;
	public URL Url;
	public URL Url2;
	public boolean repeat=true;
public SoundLoader(String url,boolean repeat)throws IOException{
	this.repeat=repeat;
	Url=getClass().getResource(url);
	Url2=getClass().getResource(url);
	 inputStream = Url.openStream();
	 inputStream2 = Url2.openStream();
		Stream=new AudioStream(inputStream);
		Stream2=new AudioStream(inputStream2);
		data=Stream.getData();
		loop = new ContinuousAudioDataStream(data);
		
}
public void play(){
	if(this.repeat==true){
	   	Player.start(loop);
	   	}
		else if(this.repeat==false);
		Player.start(Stream2);
		}
	@Override
	public void run() {
	  play();
	}}