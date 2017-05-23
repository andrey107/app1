package com.stockex;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class StockUtils {
	
    public static Map<String, Client> readClients(File f) throws IOException {
    	final List<String> lines = Files.readAllLines(Paths.get(f.getAbsolutePath()), StandardCharsets.UTF_8);
    	final Map<String, Client> clients = new HashMap<String, Client>();
    	for (String line : lines) {
    		StringTokenizer st = new StringTokenizer(line, "\t");
    		String clientName = st.nextToken();
    		int money = Integer.valueOf(st.nextToken());
    		int numOfStockA = Integer.valueOf(st.nextToken());
    		int numOfStockB = Integer.valueOf(st.nextToken());
    		int numOfStockC = Integer.valueOf(st.nextToken());
    		int numOfStockD = Integer.valueOf(st.nextToken());
    		clients.put(clientName, new Client(clientName, money, numOfStockA, numOfStockB, numOfStockC, numOfStockD));
    	}
        return clients;
    }

    public static List<Order> readOrders(File f) throws IOException {
    	final List<String> lines = Files.readAllLines(Paths.get(f.getAbsolutePath()), StandardCharsets.UTF_8);
    	final List<Order> orders = new LinkedList<Order>();
    	int orderNumber = 0;
    	for (String line : lines) {
    		StringTokenizer st = new StringTokenizer(line, "\t");
    		String clientName = st.nextToken();
    		Operation op;
    		String o = st.nextToken();
    	    if (o.equals("s")) {
    	    	op = Operation.SALE;
    	    } else {
    	    	op = Operation.BUY;
    	    }
    	    Stock stock = Stock.valueOf(st.nextToken());
    	    int price = Integer.valueOf(st.nextToken());
    	    int stockCount = Integer.valueOf(st.nextToken());
    	    
    	    orders.add(new Order(orderNumber++, clientName, op, stock, price, stockCount));
    	}
        return orders;
    }
}
