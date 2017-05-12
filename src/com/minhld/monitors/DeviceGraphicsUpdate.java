package com.minhld.monitors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.minhld.devices.Device;
import com.minhld.utils.DeviceList;
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

				Device device;
				for (String key : DeviceList.getKeySet()) {
					device = DeviceList.get(key);
					drawDevice(g, device, Color.gray);
				}
			}
			
			try {
				Thread.sleep(this.updatePeriod);
			} catch (Exception e) { }
		}
	}
	
	private void drawDevice(Graphics2D g, Device dev, Color c) {
		g.setColor(c);
		int posX = dev.location.x;
		int posY = dev.location.y;
		g.fillOval(posX, posY, 10, 10);
		g.drawString("#" + dev.name, posX - 2, posY + 20);
	}
}
