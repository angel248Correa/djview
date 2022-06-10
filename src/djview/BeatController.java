package djview;

public class BeatController extends DJView2 implements ControllerInterface {
	
	BeatModelInterface model; 
	DJView view; 
	
	
	 public BeatController (BeatModelInterface model) { 
		  this.model = model; 
		  view  = new DJView (this, model);
		  view.createView();
		  view.createControls(); 
		  view.disableStartMenu(); 
		  view.enableStartMenu();
		  model.initialize();
	 }
	@Override
	public void start() {
		 model.on();
		 view.disableStartMenu();
		 view.enableStopMenu();
		
	}

	@Override
	public void stop() {
		 model.off();
		 view.disableStopMenu();
		 view.enableStartMenu();
	}

	@Override
	public void increaseBPM() {
		int bpm = getBPM(); 
		model.SetBMP(bpm + 1);
		
		
	}

	private int getBPM() {
		model.getBPM();
	 return 0; 
	}
	@Override
	public void decreaseBPM() {
		int bpm = getBPM(); 
		model.SetBMP(bpm - 1);
		
	}

	@Override
	public void setBPM(int bpm) {
		model.SetBMP(bpm);
		
	} 
	

}
