package djview;

import java.util.ArrayList;
import java.util.Random;

public class HeartModel implements HeartModelInterface, Runnable {
	
	ArrayList<BeatObserver> beatObserver = new ArrayList<BeatObserver>();  
	ArrayList<BPMObserver> bpmObserver = new ArrayList<BPMObserver>(); 
	
	int time = 1000; 
	int bpm = 90; 
	
	Random  random = new Random(System.currentTimeMillis()); 
	Thread thread; 
	
	public HeartModel() { 
		
		thread =  new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		int lastrate = -1; 
		
		 for (;;) { 
			  int change = random.nextInt(10);
			  if (random.nextInt(2) == 0) {
				  change = 0 - change; 
			  } 
			  
			  int rate = 6000/(time + change);
			  if(rate < 120 && rate > 50) { 
				  time += change; 
				  notifyBeatObservers();
				  if(rate != lastrate) { 
					 lastrate = rate; 
					 notifyBPMObservers(); 
				  }
			  }
			  
			  try { 
				  thread.sleep(time); 
			  } catch(Exception e) { }
		 }
	}
	
	public int getHeartRate() { 
		
		return 60000/time; 
	}
	
	@Override
	public void registerObserver(BeatObserver o) {
		beatObserver.add(o);
		
	}
	@Override
	public void removeObserver(BeatObserver o) {
		int i = beatObserver.indexOf(o); 
		 if( i >= 0 ) { 
			 beatObserver.remove(i); 
		 }
		
	}
	private void notifyBeatObservers() {
	for(int i = 0; i < beatObserver.size(); i++) { 
		BeatObserver observer = (BeatObserver)beatObserver.get(i);
			observer.updateBeat();
	}
		
	}
	@Override
	public void registerObserver(BPMObserver o) {
		bpmObserver.add(o); 
		
	}
	@Override
	public void removeObserver(BPMObserver o) {
		int i = bpmObserver.indexOf(o);
		if(i >= 0 ) {
			bpmObserver.remove(i); 
		}
		
	}
	private void notifyBPMObservers() {
		for(int i = 0; i < bpmObserver.size(); i++){ 
			BPMObserver observer = (BPMObserver)bpmObserver.get(i);
			observer.updateBeat();
		}
}
	
}