package com.minhld.monitors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.minhld.utils.SimProperties;

public class DeviceMonitor extends Thread {
	JTextArea infoText;
	
	public void run() {
		JFrame mainFrame = new JFrame("Device Monitor v1.0");
		Container contentPane = mainFrame.getContentPane();
		
		// set toolbar and buttons
		JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
        toolbar.setBorderPainted(true);
        toolbar.setFloatable( true );

	    JButton button = new JButton("Update");
	    toolbar.add(button);
	    toolbar.addSeparator();
	    
	    contentPane.add(toolbar, BorderLayout.NORTH);
		
	    // set canvas
	    JPanel canvas = new JPanel();
	    canvas.setBackground(Color.white);
	    contentPane.add(canvas, BorderLayout.CENTER);
	    
	    // set control panel
	    contentPane.add(buildControlPanel(), BorderLayout.EAST);
	    
	    new DeviceGraphicsUpdate(canvas).start();
	    
	    // set windows properties
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(mainFrame);
			mainFrame.pack();
		} catch (Exception e) { }
		mainFrame.setSize(1280, 860);
		mainFrame.setMinimumSize(new Dimension(1280, 860));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
//		// set device startup environment
//		DeviceStartUp startUp = new DeviceStartUp();
//		startUp.setDeviceStartListener(new DeviceStartUp.DeviceStartListener() {
//			@Override
//			public void deviceLocationUpdated() {
//				
//			}
//		});
	}
	
	public static void main(String args[]) {
		// load configuration
		SimProperties.loadProps("basic.cfg");
		
		// set device startup environment
		new DeviceStartUp().start();
		
		// start the device monitor
		new DeviceMonitor().start();
		
	}
	
	/**
	 * build the right controller panel
	 * @return
	 */
	private JPanel buildControlPanel() {
		JPanel controller = new JPanel();
		controller.setLayout(new BorderLayout());
		
		// add network config panel
		JPanel networkConfig = new JPanel();
		networkConfig.setBorder(BorderFactory.createTitledBorder("Network Configuration"));
		
		GridLayout configLayout = new GridLayout(0, 2);
		configLayout.setVgap(5);
		networkConfig.setLayout(configLayout);
		
		// number of node field
		JLabel lbl1 = new JLabel("Number of nodes ");
		networkConfig.add(lbl1);
		JTextField numOfNodeField = new JTextField(15);
		int numOfDevs = SimProperties.getIntProp("num-of-nodes");
		numOfNodeField.setText(Integer.toString(numOfDevs));
		networkConfig.add(numOfNodeField);

		// device max speed
		JLabel lbl2 = new JLabel("Max Device Speed ");
		networkConfig.add(lbl2);
		JTextField devMaxSpeedField = new JTextField(15);
		int devMaxSpeed = SimProperties.getIntProp("device-max-speed");
		devMaxSpeedField.setText(Integer.toString(devMaxSpeed));
		networkConfig.add(devMaxSpeedField);
		
		networkConfig.add(new JLabel());
		JButton updateNetworkButton = new JButton("Update");
		networkConfig.add(updateNetworkButton);
		
		controller.add(networkConfig, BorderLayout.NORTH);
		
		// add network info panel
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(BorderFactory.createTitledBorder("Network Log"));
		
		infoText = new JTextArea(30, 46);
		infoText.setBorder(BorderFactory.createLineBorder(Color.gray));
		infoText.setFont(new Font("courier", Font.PLAIN, 11));
		infoText.setEditable(false);
		infoPanel.add(infoText, BorderLayout.CENTER);
		
		controller.add(infoPanel, BorderLayout.SOUTH);
		
		return controller;
	}
}
