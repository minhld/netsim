package com.minhld.monitors;

import java.awt.Point;
import java.util.HashMap;

import org.zeromq.ZMQ;

import com.minhld.devices.Device;
import com.minhld.devices.MobileDevice;
import com.minhld.devices.SimpleMovement;
import com.minhld.utils.Constants;

public class StartUp extends Thread {
	public void run() {
		int numOfDevs = 10;
		// points to define top-left and bottom-right corners 
		Point p11 = new Point(0, 0);
		Point p22 = new Point(10000, 10000);
		
		// initiate signal server
		SignalServer signalServer = new SignalServer();
		signalServer.setDeviceNumber(numOfDevs);
		signalServer.start();
		
		MobileDevice dev;
		for (int i = 0; i < numOfDevs; i++) {
			dev = new MobileDevice();
			dev.setMovement(new SimpleMovement(p11, p22));
			dev.setDeviceListener(new Device.DeviceListener() {
				
				@Override
				public void networkChanged() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void locationUpdated(Point location) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void discoveryCompleted() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void deviceListUpdated(HashMap<String, Device> nearbyDevices) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void connectionEstablished() {
					// TODO Auto-generated method stub
					
				}
			});
			dev.start();
		}
	}
	
	private class SignalServer extends Thread {
		private int deviceNumber = 0;
		
		public void setDeviceNumber(int deviceNumber) {
			this.deviceNumber = deviceNumber;
		}
		
		public void run() {
			ZMQ.Context context = ZMQ.context(1);

            // Socket to talk to workers
            ZMQ.Socket inquirer = context.socket(ZMQ.REP);
            inquirer.bind("tcp://" + Constants.MY_IP + ":" + Constants.SIGNAL_PORT);

            ZMQ.Poller poller = new ZMQ.Poller(1);
            poller.register(inquirer, ZMQ.Poller.POLLIN);

            byte[] resp;

            while (!isInterrupted()) {

                int totalPoll = 0;
                while (totalPoll < this.deviceNumber && poller.poll(100) > 0) {
                    // received the response from client
                    resp = inquirer.recv();

                    // send a trigger signal back to the client so that it can
                    // restart the listening loop
                    inquirer.send("");

                    totalPoll++;

                    // if the total poll number reaches the number of workers,
                    // we will return a message for that event
                    if (totalPoll == this.deviceNumber) {
                    	allAcksReceived();
                    }
                }
            }

            inquirer.close();
            context.term();
		}
		
		private void allAcksReceived() {
			
		}
	}
	
	public static void main(String args[]) {
		new StartUp().start();
	}
}
