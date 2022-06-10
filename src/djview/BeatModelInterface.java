package djview;

public interface BeatModelInterface {

	void initialize();
	
	void on();
	
	void off();
	
	void SetBMP(int bpm);
	
	int getBPM();
	
	void registerObserver (BeatObserver o); 
	
	void removeObserver(BeatObserver o);
	
	void registerObserver(BPMObserver o);
	
	void removeObserver(BPMObserver o);
	
}
