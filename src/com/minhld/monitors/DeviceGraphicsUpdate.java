package com.minhld.monitors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.minhld.utils.SimProperties;

public class DeviceGraphicsUpdate extends Thread {
	JPanel canvas;
	int updatePeriod = 0;
	
	public DeviceGraphicsUpdate(JPanel canvas) {
		this.canvas = canvas;
		this.updatePeriod = SimProperties.getIntProp("graphic-update-speed");
	}
	
	public void run() {
		Graphics2D g;
		while (true) {
			g = (Graphics2D) canvas.getGraphics();
			
			if (g == null) {
				
			} else {
				g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				for (int i = 0; i < 30; i++) {
					drawDevice(g, Color.darkGray, (int)(Math.random()*1000), (int)(Math.random()*1200));
				}
			}
			
			try {
				Thread.sleep(this.updatePeriod);
			} catch (Exception e) { }
		}
	}
	
	private void drawDevice(Graphics2D g, Color c, int posX, int posY) {
		g.setColor(c);
		g.fillOval(posX, posY, 10, 10);
		g.drawString("D#5", posX - 2, posY + 20);
	}
}
