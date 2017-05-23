package com.stockex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StockProcessor {
	
	private Map<String, Client> clients;

	private List<Order> buyOrders = new LinkedList<Order>();
	private List<Order> sellOrders = new LinkedList<Order>();

	private boolean processing(Order order1, Order order2) {
		if (order1.getOperationType() == order2.getOperationType()) {
			return false;
		}
		//Cопоставляем заявки только по полному совпадению цены и количества.
		if (order1.getStockType() == order2.getStockType() 
				&& order1.getPrice() == order2.getPrice()
				&& order1.getStockCount() == order2.getStockCount()) {
			
			Client client1 = clients.get(order1.getClientName());
			Client client2 = clients.get(order2.getClientName());
			
			client1.doAction(order1.getStockType(), order1.getStockCount(), order1.getPrice(), order1.getOperationType());
			client2.doAction(order2.getStockType(), order2.getStockCount(), order2.getPrice(), order2.getOperationType());
			return true;
		}
		return false;
	}

	public StockProcessor(Map<String, Client> clients) {
		this.clients = clients;
	}
	
	public void processOrder(Order newOrder) {
		final List<Order> orderList;	
		if (newOrder.getOperationType() == Operation.BUY) {
			orderList = sellOrders;
		} else {
			orderList = buyOrders;
		}
		
		boolean isFound = false;
		for (Order existOrder : orderList) {
			if (processing(newOrder, existOrder)) {
				orderList.remove(existOrder);
				isFound = true;
				break;
			}
		}
		
		if (!isFound) {
			if (newOrder.getOperationType() == Operation.BUY) {
				buyOrders.add(newOrder);
			} else {
				sellOrders.add(newOrder);
			}
		}
	}
	
	public Map<String, Client> getClients() {
		return clients;
	}
	
	public void writeClientsToFile(String fileName) {
		File file = new File(fileName);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fr);
			
			List<Client> clientsList = new ArrayList<Client>();
			for (Map.Entry<String, Client> entry : clients.entrySet()) {
				Client client = entry.getValue();
				clientsList.add(client);
			}
			Collections.sort(clientsList, new Comparator<Client>() {
		        public int compare(Client c1, Client c2) {
		                return c1.getName().compareTo(c2.getName());
		        }
		    });
			for (Client client : clientsList) {
				bw.write(client.getName());
				bw.write("\t");
				bw.write(String.valueOf(client.getMoney()));
				bw.write("\t");
				bw.write(String.valueOf(client.getNumberOfStock(Stock.A)));
				bw.write("\t");
				bw.write(String.valueOf(client.getNumberOfStock(Stock.B)));
				bw.write("\t");
				bw.write(String.valueOf(client.getNumberOfStock(Stock.C)));
				bw.write("\t");
				bw.write(String.valueOf(client.getNumberOfStock(Stock.D)));
				bw.newLine();
			}
			bw.close();
			System.out.println("File result.txt is created");
		} catch (IOException   e) {
			e.printStackTrace();
		}
	}
}
