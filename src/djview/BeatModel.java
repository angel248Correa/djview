package djview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class BeatModel implements BeatModelInterface, Runnable {
	List<BeatObserver> beatObserver = new ArrayList<BeatObserver>();
	List<BPMObserver> bpmObserver = new ArrayList<BPMObserver>();
	
	int bpm = 90;
	Thread thread; 
	boolean stop = false; 
	Clip clip; 
	
	@Override
	public void initialize() {
		try { 
			File resource = new File("clip.wav"); 
			clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			clip.open(AudioSystem.getAudioInputStream(resource));
		}
		catch(Exception ex) {/*...*/}
	}

	@Override
	public void on() {
		int bmp = 90; 
		// notifyBPMObserver(); 
		thread = new Thread(this);
		stop = false; 
		thread.start();
		
	}

	private void notifyBPMObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void off() {
		stopBeat(); 
		stop = true; 
		
	}


	@Override
	public void SetBMP(int bpm) {
		// TODO Auto-generated method stub
		
	}
	
	public void run() {
		while(!stop) { 
			playBeat();
			notifyBeatObserver(); 
			try { 
				thread.sleep(600000/bpm);
			} catch (Exception e) {}
			
		}
		
	}
	public void setBPM(int bpm) { 
		 this.bpm = bpm;
		 notifyBPMObserver(); 
	}
	
	@Override
	public int getBPM() {
	 return bpm;	
		
	}
	
	 public void notifyBeatObserver() { 
		  for(int i = 0; i < beatObserver.size(); i++) { 
			  BeatObserver observer = (BeatObserver)beatObserver.get(i);
			  observer.updateBeat();
		  }
	 }
	


	@Override
	public void registerObserver(BeatObserver o) {
		beatObserver.add(o);
		
	}
	
	public void notifyBMPObserver() { 
		 for(int i = 0; i < bpmObserver.size(); i++) { 
			 BPMObserver observer = (BPMObserver)bpmObserver.get(i);
			 observer.updateBeat();
			 
		 }
	}

	@Override
	public void removeObserver(BeatObserver o) {
		
		 int i = beatObserver.indexOf(o);
		  if(i >= 0) { 
			  beatObserver.remove(i);
		  }
	}
		
		@Override
		public void removeObserver(BPMObserver o) {
		int i = bpmObserver.indexOf(o);
		 if(i >= 0 ) {
			  bpmObserver.remove(i); 
		 }
			
		}
		
	

	@Override
	public void registerObserver(BPMObserver o) {
		bpmObserver.add(o); 
		
	}



	private void playBeat() {
		clip.setFramePosition(0);
		clip.start();
		
	}

private void stopBeat() {
	clip.setFramePosition(0);
	clip.stop();
	
}

}