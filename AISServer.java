package com.pelatro.asi;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

public class AISServer {	
	public static void main(String[] args) throws Exception {

			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
	        server.createContext("/mviva_offers", new MvivaHandler());
	        server.createContext("/mviva_offer_provision", new ProvisionHandler());
	       // server.createContext("/mviva_nextbestoffers",new NbOffers());
	        //server.createContext("/mviva_nextbestoffers", new NextBestHandler());
	        server.setExecutor(null);
	        server.start();
	}
	
}
