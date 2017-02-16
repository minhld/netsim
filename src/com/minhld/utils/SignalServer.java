package com.minhld.utils;

import org.zeromq.ZMQ;

public class SignalServer extends Thread {
	private int deviceNumber = 0;
	private SignalServerListener listener;
	
	public void setDeviceNumber(int deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	
	public void setSignalServerListener(SignalServerListener listener) {
		this.listener = listener;
	}
	
	public void run() {
		ZMQ.Context context = ZMQ.context(1);

        // Socket to talk to workers
        ZMQ.Socket inquirer = context.socket(ZMQ.REP);
        inquirer.bind("tcp://" + Constants.MY_IP + ":" + Constants.SIGNAL_PORT);

        ZMQ.Poller poller = new ZMQ.Poller(1);
        poller.register(inquirer, ZMQ.Poller.POLLIN);

        String resp;

        while (!isInterrupted()) {

            int totalPoll = 0;
            while (totalPoll < this.deviceNumber && poller.poll(100) > 0) {
                // received the response from client
                resp = inquirer.recvStr();

                // send a trigger signal back to the client so that it can
                // restart the listening loop
                inquirer.send("");

                totalPoll++;

                // if the total poll number reaches the number of workers,
                // we will return a message for that event
                if (totalPoll == this.deviceNumber && this.listener != null) {
                	this.listener.allAcksReceived();
                }
            }
        }

        inquirer.close();
        context.term();
	}
	
	public interface SignalServerListener {
		public void updateDeviceInfo();
		public void allAcksReceived();
	}
}