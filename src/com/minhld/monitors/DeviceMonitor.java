package com.minhld.monitors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DeviceMonitor extends Thread {
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
	    
	    new DeviceUpdateThread(canvas).start();
	    
	    // set windows properties
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(mainFrame);
			mainFrame.pack();
		} catch (Exception e) { }
		mainFrame.setSize(1200, 860);
		mainFrame.setMinimumSize(new Dimension(1200, 860));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	public static void main(String args[]) {
		// start the devices
		new DeviceStartUp().start();
		
		// start the device monitor
		new DeviceMonitor().start();
		
	}
}
