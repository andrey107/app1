package com.stockex;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main(String[] args ) {
    	String clientFilePath = args[0];
    	String ordersFilePath = args[1];

    	if (!clientFilePath.contains("clients.txt")) {
    		System.out.println("First argument must be clients.txt");
    		return;
    	}	
    	if (!ordersFilePath.contains("orders.txt")) {
    		System.out.println("Second argument must be orders.txt");
    		return;
    	}
    	Map<String, Client> clientsMap = null;
    	List<Order> orders = null;
    	try {
    		clientsMap = StockUtils.readClients(new File(clientFilePath));
    		orders = StockUtils.readOrders(new File(ordersFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	StockProcessor stockProcessor = new StockProcessor(clientsMap);
    	for (Order order : orders) {
    		stockProcessor.processOrder(order);
    	}
    	stockProcessor.writeClientsToFile("result.txt");
    }

}
