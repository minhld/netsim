package com.minhld.utils;

import org.zeromq.ZMQ;

public abstract class SignalClient extends Thread {
	private ZMQ.Context context;
    private ZMQ.Socket responder;
    
	public void run() {
        this.context = ZMQ.context(1);

        responder = this.context.socket(ZMQ.REQ);
        responder.connect("tcp://" + Constants.MY_IP + ":" + Constants.SIGNAL_PORT);

        String resp;
        while (!Thread.currentThread().isInterrupted()) {
            // send back locations
        	resp = createResponse();
        	responder.send(resp);

            // waits for location request
            responder.recv();
        }
        
        responder.close();
        context.term();
	}
	
	public abstract String createResponse();
}
