package djview;

public class HeartAdapter implements BeatModelInterface {
	
	HeartModelInterface heart; 
	
	public HeartAdapter( HeartModelInterface heart) { 
		this.heart = heart; 
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void on() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void off() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getBPM() {
		return heart.getHeartRate();
	}

	@Override
	public void SetBMP(int bpm) {
	
	}


	@Override
	public void registerObserver(BeatObserver o) {
		heart.registerObserver(o);
		
	}

	@Override
	public void removeObserver(BeatObserver o) {
		heart.removeObserver(o);
		
	}

	@Override
	public void registerObserver(BPMObserver o) {
	heart.registerObserver(o);
		
	}

	@Override
	public void removeObserver(BPMObserver o) {
		heart.removeObserver(o);
		
	}
}
