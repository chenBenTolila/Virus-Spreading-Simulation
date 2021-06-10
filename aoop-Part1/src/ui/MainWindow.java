package ui;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

import simulation.*;
import ui.LogFileOriginator.LogFileMemento;
import country.Map;
import country.Settlement;
import io.SimulationFile;
import location.Location;
import location.Point;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

/**
 * class MainWindow
 */
public class MainWindow extends JFrame {
	
	BorderLayout myBorderLayout = new BorderLayout();
    
    /**
     * default constructor
     */
    public MainWindow()
    {
    	super("Main Window");
    	m = new Map(); // create an empty map
    	myBorderLayout.setHgap(10);
    	myBorderLayout.setVgap(0);
    	this.setLayout(myBorderLayout);
    	// creating the slider
    	createJSlider();
    	// creating the map panel;
    	mapP = new MapPanel();
    	this.add(mapP, BorderLayout.CENTER);  // need to change to a panel!!!!
    	
    	// creating the menu
    	menu = new Menu();   // creating the menu object
    	this.add(menu, BorderLayout.NORTH);
    	
 
    	this.pack();
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent e) {
    	        e.getWindow().dispose();
    	        System.out.println("Program Finished");
    	        System.exit(0);
    	    }
    	});
    	this.setVisible(true);
    }
    
    /**
     * creating the JSilder for the simulation speed
     */
    public void createJSlider()
    {
    	JPanel jp = new JPanel();
    	jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
    	jp.add(new JLabel("Simulation speed"));
        JSlider simuSpeed = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, deafultSimuSpeed);
        jp.add(simuSpeed, BorderLayout.SOUTH);
        simuSpeed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				 
				menu.setSleepTime(simuSpeed.getValue());  // change the sleep Time of the simulation
			}
		});	
        
        //Turn on labels at major tick marks.
        simuSpeed.setMajorTickSpacing(2);
        simuSpeed.setMinorTickSpacing(1);
        simuSpeed.setPaintTicks(true);
        simuSpeed.setPaintLabels(true);
        this.add(jp, BorderLayout.SOUTH);
    }
    
    
    // begin test
    
    // map panel test
    
    /*
     * map panel class
     */
    public class MapPanel extends JPanel{
    	
    	public MapPanel() {
    		super();
    		setVisible(true);
    		this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                	if(m == null)
                		return;
                	
                	if(menu == null)
                		return;
                	int max_x = m.getMaxXInMap();
            		int max_y = m.getMaxYInMap();
            		
            		double Xratio = getWidth() / (max_x + 1.0);
            		double Yratio = getHeight() / (max_y + 1.0);
                    int x = (int)(evt.getX() / Xratio);
                    int y = (int)(evt.getY() / Yratio);
                    
                    for(int i = 0; i < m.getNumOfSettlement(); ++i)
                    	if(m.isPointInSetIndex(i, x, y))
                    	{
                    		menu.openStatWindow(m, i);
                    		//System.out.println("need to open statistics");
                    		break;
                    	}
                    	
                }
            });
    	}
    	
    	/**
    	 * method to paint map
    	 */
    	@Override
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		Graphics2D gr = (Graphics2D) g;
    		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		if(m == null)
    			return;
    		
    		int max_x = m.getMaxXInMap();
    		int max_y = m.getMaxYInMap();
    		
    		double Xratio = getWidth() / (max_x + 1.0);
    		double Yratio = getHeight() / (max_y + 1.0);
    		
    		LineDecorator ld = null;
    		for(Settlement s : m)   // using iterator on map
    		{
    			Settlement connect[] = s.getConnectSettlements();
    			for (Settlement b : connect)
    			{
    				ld = new LineDecorator(s, b);
    				ld.drawColoredLine(g, Xratio, Yratio);
    			}
    			
    			/*
    			Point[] pm = s.connectedMiddlePoints();
    			for(int j=1; j < pm.length; ++j) {
    				g.drawLine((int)(pm[0].getX() * Xratio), (int)(pm[0].getY() * Yratio), (int)(pm[j].getX() * Xratio), (int)(pm[j].getY() * Yratio));	
    			}
    			*/
    		}
    		 
    		Color col;
    		Location loc;
    		for(Settlement s: m)  // using iterator on map
    		{
    			col = s.getSetColor();
    			loc = s.getLocation();
    			if(col != null & loc != null)
    			{
   				g.setColor(col);
    				g.fillRect((int)(loc.getPointX() * Xratio) , (int)(loc.getPointY() * Yratio), (int)(loc.getSizeWidth() * Xratio), (int)(loc.getSizeHeight() * Yratio));
    				g.setColor(Color.BLACK);
    				g.drawString(s.getSettlementName(), (int)(loc.getPointX() * Xratio), (int)((loc.getPointY()+(loc.getSizeHeight()/2)) * Yratio));
    			}
    		}
    		
    	}
    	
    	
    	
    	/*
    	 * map size
    	 */
    	@Override
    	public Dimension getPreferredSize() {
    	return new Dimension(400, 400);
    	}
    	 
    	//private Map m_map; // keep map object
    	//private MainWindow mainWindow = null;
    }
    
    
    // menu test
    
    /**
     * menu class
     */
    public class Menu extends JMenuBar {

    	public Menu()
    	{
    		createFileMenu();
    		createSimulationMenu();
    		createHelpMenu();
    	}
    	
    	
    	/**
    	 * creates the mini menu file
    	 */
    	public void createFileMenu()
        {
    		
            JMenu m1 = new JMenu("FILE");
            this.add(m1);
            
            // create File menu
            JMenuItem m11 = new JMenuItem("Load");
            m11.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(!fileLoadedFlag) {
    					chooseFile(m);
    					mapP.repaint();
    					if(fileLoadedFlag)
    					{
    						m.setLogFilePath(logPath);
    						createBarrier();
    						m.spawnSett();
    					}
    				}
    				else {
    					JOptionPane.showMessageDialog(null, "In order to load a new file, you need to stop the current simulation");
    				}
    			}
    		});
            JMenuItem m12 = new JMenuItem("Statistics");
            m12.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				openStatWindow(m, -1);
    			}
    		});
            
            
            JMenuItem m13 = new JMenuItem("Edit Mutations");  // opening Mutation Edit as a modal
            m13.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(mutationWindow == null)
    					new EditMutationsWindow();
    				else
    					mutationWindow.setVisible(true);
    				
    			}
    		});
            
            JMenuItem m14 = new JMenuItem("Log File");
            m14.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(logPath == null)
    					saveLogFile();
    				
    				else
    				{
    					JOptionPane.showMessageDialog(null, "You already opened a log file");
    				}
    			}
    		});
            
            JMenuItem m15 = new JMenuItem("Exit");   // creating Exit option
            m15.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("Program Finished");
    				System.exit(0);   // close the system
    			}
    		});
            
            m1.add(m11);
            m1.add(m12);
            m1.add(m13);
            m1.add(m14);
            m1.add(m15);
        }
    	
    	/**
    	 * save the dead in file
    	 */
    	public void saveLogFile(){
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Specify a file to save");   
    		 
    		int userSelection = fileChooser.showSaveDialog(this);
    		 
    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    			File fileToSave = fileChooser.getSelectedFile();
    		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    		    
    		    logPath = fileToSave.getAbsolutePath();
    		    m.setLogFilePath(logPath);
    		}
    	}
    	
    	public void updateLogFilePath(String logPath)
    	{
    		if(logOriginator.getState() != null)
    		{
    			// logCaretaker.addMemento(new LogFileMemento(logOriginator.getState()));
    		}
    	}
    	
    	
    	/**
    	 * choose new file
    	 */
    	public void chooseFile(Map m){
    		if(m.getStopStat() || !fileLoadedFlag)
    		{
    			FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
    		    dialog.setMode(FileDialog.LOAD);
    		    dialog.setVisible(true);
    		    String file = dialog.getFile();
    		    System.out.println(file + " chosen.");
    		    SimulationFile.setFileName(file);
    		    m.setPlayState(true);
    		    try {
    				fileLoadedFlag = SimulationFile.createMap(m);
    				if(fileLoadedFlag == true)
    					m.setStopStat(false);
    				
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				System.out.println("error with file opening");
    			}
    		}
    	}
    	
    	
    	/**
    	 * create the mini menu simulation
    	 */
    	public void createSimulationMenu()
    	{
    		JMenu m2 = new JMenu("Simulation");
    		this.add(m2);
    		
         // create Simulation menu
            JMenuItem m21 = new JMenuItem("Play");
            m21.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(fileLoadedFlag)
    				{
    					m.setPlayState(true);  // update the play status to true
    					synchronized (m) {
    						m.notifyAll();
    					}
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(null, "You need to load a simulation first");
    				}
    				
    			}
    		});
           
            JMenuItem m22 = new JMenuItem("Pause");
            m22.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(fileLoadedFlag)
    					m.setPlayState(false);  // update the play status to false
    				else
    				{
    					JOptionPane.showMessageDialog(null, "You need to load a simulation first");
    				}
    			}
    		});
            
            JMenuItem m23 = new JMenuItem("Stop");
            m23.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(fileLoadedFlag)
    				{
    					// check the order (stop and then play)
    					m.setStopStat(true);
    					m.setPlayState(true);
    					if(m_sw != null)
    						m_sw.dispose();
    					m_sw = null;
    					// m = null;
    					fileLoadedFlag = false;
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(null, "You need to load a simulation first");
    				}
    			}
    		});
            
            JMenuItem m24 = new JMenuItem("Set Ticks Per Day");
            m24.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				createSetTicksDialog();
    			}
    		});
            
            m2.add(m21);
            m2.add(m22);
            m2.add(m23);
            m2.add(m24);
    	}  
    	
    	/**
    	 * create mini menu help
    	 */
    	public void createHelpMenu()
    	{
    		JMenu m3 = new JMenu("Help");
    		this.add(m3);
    		
         // create Simulation menu
            JMenuItem m31 = new JMenuItem("Help");
            m31.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				createHelpDialog();	
    			}
    		});
            
            JMenuItem m32 = new JMenuItem("About");
            m32.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				createAboutDialog();
    			}
    		});
            
            m3.add(m31);
            m3.add(m32);
        }
    	
    	/**
    	 * create a dialog that takes as input a number of ticks per day
    	 */
    	public void createSetTicksDialog()
    	{
    		SpinnerModel model = new SpinnerNumberModel(Clock.getTicksPerDay(), //initial value
    													1,  // minimum
    													10, //max
    													1); //step

    		JSpinner spinner = new JSpinner(model);
    		spinner.addChangeListener(new ChangeListener() {
    			
    			@Override
    			public void stateChanged(ChangeEvent e) {
    				try {
    					int value = (Integer) spinner.getValue();
    					Clock.setTicksPerDay(value);
    				}
    				catch(Exception e1)
    				{
    					System.out.println("Error - a non integer value was entered");
    				}
    			}
    		});
    		JPanel jp = new JPanel();
    		JLabel input = new JLabel("Enter numer of ticks per day:");
    		jp.add(input);
    		jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
    		jp.add(spinner);
    		JDialog jd = new JDialog();
    		jd.setSize(80, 40);
    		jd.setLocationRelativeTo(null);
    		jd.add(jp);
    		jd.setModal(true);
    		jd.pack();
    		jd.setVisible(true);
    	}
    	
    	/**
    	 * create an about modeless dialog
    	 */
    	public void createAboutDialog()
    	{
    		JPanel jp = new JPanel();
    		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
    		jp.add(new JLabel("Authors: Chen Ben Tolila, "));
    		jp.add(new JLabel("Hadar Amsalem"));
    		jp.add(new JLabel("Creation Date: 15/04/21"));
    		JDialog dialog = new JDialog((JFrame)null, "About");
    		/*
    		Container dialogContainer = dialog.getContentPane(); 
    	    dialogContainer.add(new JLabel("Authors: Chen Ben Tolila,\r\n" + " Hadar Amsalem" + "\nCreation Date: 15/04/21"));
    	    */
    		dialog.add(jp);
    	    dialog.pack();
    		dialog.setVisible(true);
    		dialog.setLocationRelativeTo(null); // center on screen
    	}
    	
    	/**
    	 * create an help modal dialog
    	 */
    	public void createHelpDialog()
    	{
    	    JOptionPane.showMessageDialog(null, "The program simulates the infection process of a virus that develops into different mutations.\r\n"
    	    		+ "The simulation takes place on different types of settlements,\r\n" + "and combines all the necessary information about the people in each settlement.\r\n"
    	    		+ "");
    	}
    	
    	/**
    	 * update the table in the statistic window 
    	 */
    	public void updateStatTable()
    	{
    		if(m_sw != null)
    			m_sw.updateTable();
    	}
    	
    	public void openStatWindow(Map m, int index)
    	{
    		if(!fileLoadedFlag)
    		{
    			JOptionPane.showMessageDialog(null, "You need to load a file first");
    		}
    		else if(m_sw == null)
    			m_sw = new StatisticsWindow(m, index);
    		else
    		{
    			m_sw.clearTextField();
    			m_sw.deselectAndSelectARow(index);
    			m_sw.setVisible(true);
    		}
    	}
    	
    	
    	/**
    	 * 
    	 * @return the file loaded flag status
    	 */
    	public boolean IsFileLoaded()
    	{
    		return fileLoadedFlag;
    	}
    	
    	public void createBarrier()
    	{
    		CyclicBarrier barrier = new CyclicBarrier(m.getNumOfSettlement(), new Runnable()
    				{
    					@Override
    					public void run() {
    						System.out.println("in barrier");
    						if(m_sw != null)
    							m_sw.updateTable();
    						
    						mapP.repaint();
    						Clock.nextTick();
    						
    						try {
    							Thread.sleep(1000 * sleepTime);
    						} catch (InterruptedException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    							System.out.println("failed to sleep");
    						}
    						
    					}
    				});
    		m.setMapBarrier(barrier);
    	}
    	
    	
    	/**
    	 * 
    	 * @param val - the new status of the file loaded flag 
    	 */
    	public void setFileLoadedFlag(boolean val)
    	{
    		fileLoadedFlag = val;
    	}
    	
    	
    	/**
    	 * 
    	 * @return the sleep time between simulations
    	 */
    	public int getSleepTime()
    	{
    		return sleepTime;
    	}
    	
    	
    	/**
    	 * 
    	 * @param val - the new sleep time between simulations
    	 */
    	public void setSleepTime(int val)
    	{
    		sleepTime = val;
    	}
    	
    	
    	// members for the statistic window
        private StatisticsWindow m_sw = null;
        private boolean fileLoadedFlag = false;
        private int sleepTime = 10;
        private String logPath = null;
        private LogFileOriginator logOriginator = new LogFileOriginator();
        private LogFileCaretaker logCaretaker = new LogFileCaretaker();
    }

    
    
    /**
     * method to repaint map after simulation
     */
    public void repaintMap()
    {
    	mapP.repaint();
    }
    
    
    /**
     *  update the statistic window
     */
    public void updateStatistic()
    {
    	if(menu != null)
    		menu.updateStatTable();
    }
    
    
    public Menu getMenu()    // check if needed
    {
    	return menu;
    }
    
    
    
    private MapPanel mapP = null; // map panel
    private Menu menu = null;  // the menu
    private Map m;
    private EditMutationsWindow mutationWindow = null;  // the edit mutationsW
    
    
    // members for the slider
    private final int FPS_MIN = 1; 
    private final int FPS_MAX = 20; 
    private int deafultSimuSpeed = 10;
    		
}