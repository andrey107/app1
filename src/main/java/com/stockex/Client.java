package com.stockex;

import java.util.HashMap;
import java.util.Map;

public class Client {

	private final Map<Stock, Integer> stock2count = new HashMap<Stock, Integer>();
    private final String name;
	private int money;

    public Client(String name, int money, int numOfStockA, int numOfStockB, int numOfStockC, int numOfStockD) {
        this.name = name;
        this.money = money;
        stock2count.put(Stock.A, numOfStockA);
        stock2count.put(Stock.B, numOfStockB);
        stock2count.put(Stock.C, numOfStockC);
        stock2count.put(Stock.D, numOfStockD);
    }
    
    public Client(String name) {
        this(name,0,0,0,0,0);
    }
    
    public int getNumberOfStock(Stock stock) {
    	return stock2count.get(stock);
    }
    
    public String getName() {
		return name;
	}
    
    public void setNumberOfStock(Stock stock, int stockCount) {
    	stock2count.put(stock, stockCount);
    }
    
    

    void doAction(Stock s, Integer stockCount, int pricePerStock, Operation op) {
        int oldStockCount = stock2count.get(s);
        int newStockCount = 0;
        switch (op) {
            case BUY:
                newStockCount = oldStockCount + stockCount;
                money = money - pricePerStock*stockCount;
                break;
            case SALE:
                newStockCount = oldStockCount - stockCount;
                money = money + pricePerStock*stockCount;
        }
        setNumberOfStock(s, newStockCount);

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
