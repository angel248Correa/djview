package djview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DJView2 implements ActionListener, BeatObserver, BPMObserver{
	BeatModelInterface model; 
	ControllerInterface controller; 
	JFrame viewFrame; 
	JPanel viewPanel; 
	BeatBar beatBar; 
	JLabel bpmOutputLabel; 
	JFrame controlFrame;
	JPanel controlPanel; 
	JLabel bpmLabel; 
	JTextField bpmTextField; 
	JButton increaseBPMButton; 
	JButton setBPMButton; 
	JButton decreaseBPMButton; 
	JMenuBar menuBar; 
	JMenu menu; 
	JMenuItem startMenuItem; 
	JMenuItem stopMenuItem; 
	
	public void createControls() { 
		// creating all the  swing components
		JFrame.setDefaultLookAndFeelDecorated(true);
		controlFrame = new JFrame("control"); 
		controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controlFrame.setSize(new Dimension(100, 80));
		
		controlPanel = new JPanel( new GridLayout(1, 2)); 
		
		menuBar = new JMenuBar(); 
		
		menu = new JMenu("DJ Control");
		
		startMenuItem = new JMenuItem("Start");
		menu.add(startMenuItem); 
		startMenuItem.addActionListener((event) -> controller.start());
		
		stopMenuItem = new JMenuItem("stop"); 
		menu.add(stopMenuItem); 
		stopMenuItem.addActionListener((event) -> controller.stop());
		
	
		JMenuItem exit = new JMenuItem("Quit");
		exit.addActionListener((event) -> System.exit(0));
		
		menu.add(exit); 
		menuBar.add(menu); 
		controlFrame.setJMenuBar(menuBar); 
		
		
		bpmTextField =  new JTextField(); 
		bpmLabel  =  new JLabel("Enter BPM" , SwingConstants.RIGHT);
		setBPMButton = new JButton(); 
		setBPMButton.setSize(new Dimension(10, 40));
		increaseBPMButton = new JButton(">>");
		decreaseBPMButton = new JButton("<<");
		
		setBPMButton.addActionListener(this);
		increaseBPMButton.addActionListener(this);
		decreaseBPMButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel( new GridLayout(1,2));
		
		buttonPanel.add(increaseBPMButton); 
		buttonPanel.add(decreaseBPMButton); 
		
		
		JPanel enterPanel = new JPanel(new GridLayout(1,2));
		enterPanel.add(bpmLabel);
		enterPanel.add(bpmTextField);
		
		JPanel insideControlPanel = new JPanel( new GridLayout(3,1));
		insideControlPanel.add(enterPanel); 
		insideControlPanel.add(setBPMButton);
		insideControlPanel.add(buttonPanel); 
		controlPanel.add(insideControlPanel); 
		
		bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		controlFrame.getRootPane().setDefaultButton(setBPMButton);
		controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);
		
		controlFrame.pack(); 
		controlFrame.setVisible(true); 
		
	}
	
	public void enableStopMenu() { 
		stopMenuItem.setEnabled(true);
	}
	
	public void disableStopMenu() { 
		stopMenuItem.setEnabled(false);
	}
	 public void enableStartMenu()  { 
		 startMenuItem.setEnabled(true);
	 }
	 public void disableStartMenu()  { 
		 startMenuItem.setEnabled(false);
	 }


	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == setBPMButton) { 
			int bpm = 90; 
			String bpmText = bpmTextField.getText(); 
			if(bpmText == null || bpmText.contentEquals("")) { 
				bpm = 90; 
			}else  { 
				bpm = Integer.parseInt(bpmTextField.getText()); 
			} 
			 controller.setBPM(bpm); 
			 
			} else if(event.getSource() == increaseBPMButton) { 
				controller.increaseBPM(); 
		}else if(event.getSource() == increaseBPMButton) { 
			  controller.decreaseBPM(); 
		}
	}
	
	public void updateBPM() { 
		if(model != null ) { 
			int bpm  = model.getBPM(); 
			if(bpm == 0 ) { 
				if(bpmOutputLabel !=null) {
					bpmLabel.setText("offline");
				}
			} else { 
				if(bpmOutputLabel != null) { 
					bpmLabel.setText("current bpm"  + model.getBPM());
				}
				
			}
		}
	}
	
	@Override
	public void updateBeat() {
		if(beatBar != null) { 
			beatBar.setValue(100); 
		}
		
	}

}
