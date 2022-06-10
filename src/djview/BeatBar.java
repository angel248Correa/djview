package djview;

import javax.swing.JProgressBar;

public class BeatBar extends JProgressBar implements Runnable {

	public static final long serialVersionUID = 2L;
	JProgressBar progressBar; 
	Thread thread; 
	
	public BeatBar() { 
		thread =  new Thread(); 
		setMaximum(100);
		thread.start();
	}
	
	

	@Override
	public void run() {
		 for(;;) {
				int value = getValue(); 
				 value  =(int) (value * .75);
				 setValue(value);
				  repaint(); 
				  
				  try { 
					   thread.sleep(50);
				  } catch(Exception e) {}
			 }
	}
}
