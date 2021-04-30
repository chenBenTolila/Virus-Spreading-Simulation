package ui;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import simulation.*;


public class MainWindow extends JFrame {
    BorderLayout myBorderLayout = new BorderLayout();
    
    /**
     * default constructor
     */
    public MainWindow()
    {
    	super("Main Window");
    	myBorderLayout.setHgap(10);
    	myBorderLayout.setVgap(0);
    	this.setLayout(myBorderLayout);
    	createJSlider();
    	Menu menu = new Menu();
    	this.add(menu, BorderLayout.NORTH);
    	this.add(new JButton("Map Panel"), BorderLayout.CENTER);  // need to change to a panel!!!!
    	this.pack();
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	this.setVisible(true);
    }
    
    /**
     * creating the JSilder for the simulation speed
     */
    public void createJSlider()
    {
        JSlider simuSpeed = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);

        this.add(simuSpeed, BorderLayout.SOUTH);
        simuSpeed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				 
				Main.setSleepTime(simuSpeed.getValue());  // change the sleep Time of the simulation
			}
		});	
        
        //Turn on labels at major tick marks.
        simuSpeed.setMajorTickSpacing(10);
        simuSpeed.setMinorTickSpacing(1);
        simuSpeed.setPaintTicks(true);
        simuSpeed.setPaintLabels(true);
    }
    
    
    
    
    /*
    private final InputsPanel inputs;
    private static class InputsPanel extends JPanel {
        private final JTextField tbWidth, tbHeight, tbLength;

        public InputsPanel() {
            this.setLayout(new GridLayout(1, 2));
            this.add(new JLabel("Width:"));
            this.add(tbWidth = new JTextField());
            this.add(new JLabel("Height:"));
            this.add(tbHeight = new JTextField());
            this.add(new JLabel("Length:"));
            this.add(tbLength = new JTextField());
        }

        public int getVolume() {
            int w = Integer.parseInt(tbWidth.getText());
            int h = Integer.parseInt(tbHeight.getText());
            int l = Integer.parseInt(tbLength.getText());
            return w * h * l;
        }

        public int getArea() {
            int w = Integer.parseInt(tbWidth.getText());
            int h = Integer.parseInt(tbHeight.getText());
            int l = Integer.parseInt(tbLength.getText());
            return 2 * (w * h  + w * l + h * l);
        }
    }

    private class CalculatePanel extends JPanel {
        private final JLabel lbVolume, lbArea;

        public CalculatePanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

            JButton btCalc = new JButton("Calculate");
            btCalc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lbVolume.setText("Volume: " + inputs.getVolume());
                    lbArea.setText("Area: " + inputs.getArea());
                    pack();
                }
            });
            this.add(btCalc);
            this.add(Box.createHorizontalStrut(10));
            this.add(lbVolume = new JLabel("volume"));
            this.add(Box.createHorizontalStrut(10));
            this.add(lbArea = new JLabel("area"));
        }
    }

    public VolumeAreaBox() {
        super("Main Window");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(inputs = new InputsPanel());
        this.add(new CalculatePanel());
        this.pack();
        this.setVisible(true);
    }
    */

    
    // members for the slider
    private static final int FPS_MIN = 0;
    private static final int FPS_MAX = 30;
    private static final int FPS_INIT = 15;    //initial frames per second
}