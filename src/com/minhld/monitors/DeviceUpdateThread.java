package com.minhld.monitors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class DeviceUpdateThread extends Thread {
	JPanel canvas;
	public DeviceUpdateThread(JPanel canvas) {
		this.canvas = canvas;
	}
	
	public void run() {
		Graphics2D g;
		int pos = 100;
		while (true) {
			g = (Graphics2D) canvas.getGraphics();
			
			if (g == null) {
				
			} else {
				g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g.setColor(Color.darkGray);
				g.drawOval(pos++,pos++,10,10);
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) { }
		}
	}	
}
