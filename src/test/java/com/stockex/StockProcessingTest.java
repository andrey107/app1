package com.stockex;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.stockex.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class StockProcessingTest {
	final static List<Order> orders = new LinkedList<Order>();
	final static Map<String, Client> clientsMap = new HashMap<String, Client>();
	
	/*
	 *  1.
	 * Incoming Test clients data:
	 * C1 -15  15    0   0   0	
	 * C2   5   0    0   5   0
	 * C3  -5   0    0  -5   0
	 * C4 -20   0    0   0  20	
	 * C5  20   0    0   0 -20
	 * C6  10   0   10   0   0
	 * C7  15 -15    0   0   0
	 * C8 -10   0  -10   0   0 
	 * C9  1    1    1   1   1
	 * 
	 * ***********************
	 * 
	 * 2.
	 * Incoming test orders:
	 * C2	b	C	1	5
	 * C8	s	B	1	10
	 * C7	b	A	1	15
	 * C3	s	C	1	5
	 * C6	b	B	1	10
	 * C4	s	D	1	20
	 * C1	s	A	1	15
	 * C5	b	D	1	20
	 * ************************
	 * 
	 * 3.
	 * Test clients data ofter running test:
	 * C1  0 0 0 0 0	
	 * C2  0 0 0 0 0
	 * C3  0 0 0 0 0
	 * C4  0 0 0 0 0	
	 * C5  0 0 0 0 0
	 * C6  0 0 0 0 0
	 * C7  0 0 0 0 0
	 * C8  0 0 0 0 0 
	 * C9  1 1 1 1 1
	 */
	@BeforeClass
	public static void prepareTestData() {
		clientsMap.put("C1", new Client("C1", -15, 15, 0, 0, 0));
		clientsMap.put("C2", new Client("C2", 5, 0, 0, -5, 0));
		clientsMap.put("C3", new Client("C3", -5, 0, 0, 5, 0));
		clientsMap.put("C4", new Client("C4", -20, 0, 0, 0, 20));
		clientsMap.put("C5", new Client("C5", 20, 0, 0, 0, -20));
		clientsMap.put("C6", new Client("C6", 10, 0, -10, 0, 0));
		clientsMap.put("C7", new Client("C7", 15, -15, 0, 0, 0));
		clientsMap.put("C8", new Client("C8", -10, 0, 10, 0, 0));
		clientsMap.put("C9", new Client("C9", 1, 1, 1, 1, 1));
		
		orders.add(new Order(0, "C2", Operation.BUY, Stock.C, 1, 5));
		orders.add(new Order(1, "C8", Operation.SALE, Stock.B, 1, 10));
		orders.add(new Order(2, "C7", Operation.BUY, Stock.A, 1, 15));
		orders.add(new Order(3, "C3", Operation.SALE, Stock.C, 1, 5));
		orders.add(new Order(4, "C6", Operation.BUY, Stock.B, 1, 10));
		orders.add(new Order(5, "C4", Operation.SALE, Stock.D, 1, 20));
		orders.add(new Order(6, "C1", Operation.SALE, Stock.A, 1, 15));
		orders.add(new Order(7, "C5", Operation.BUY, Stock.D, 1, 20));
	}
	
	@Test
	public void testProcessingOrders() throws IOException {
		StockProcessor stockProcessor = new StockProcessor(clientsMap);
    	for (Order order : orders) {
    		stockProcessor.processOrder(order);
    	}
    	
    	for (Map.Entry<String, Client> entry : stockProcessor.getClients().entrySet()) {
    		Client client = entry.getValue();
    		if (!client.getName().equals("C9")) {
    			assertTrue(client.getMoney() == 0);
    			assertTrue(client.getNumberOfStock(Stock.A) == 0);
    			assertTrue(client.getNumberOfStock(Stock.B) == 0);
    			assertTrue(client.getNumberOfStock(Stock.C) == 0);
    			assertTrue(client.getNumberOfStock(Stock.D) == 0);
    		} else if (client.getName().equals("C9")) {
    			assertTrue(client.getMoney() == 1);
    			assertTrue(client.getNumberOfStock(Stock.A) == 1);
    			assertTrue(client.getNumberOfStock(Stock.B) == 1);
    			assertTrue(client.getNumberOfStock(Stock.C) == 1);
    			assertTrue(client.getNumberOfStock(Stock.D) == 1);
    		}
    	}
    	stockProcessor.writeClientsToFile("test_result.txt");
	}
}
