package com.stockex;

public class Order {

    private final int orderNumber;
    private final String clientName;
    private final Operation operationType;
    private final Stock stockType;
    private final int price;
    private final int stockCount; 

	public Order(int orderNumber, String clientName, Operation op, Stock stock, int price, int stockCount) {
        this.orderNumber = orderNumber;
        this.clientName = clientName;
        this.operationType = op;
        this.stockType = stock;
        this.price = price;
        this.stockCount = stockCount;
    }
    
    public int getOrderNumber() {
		return orderNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public Operation getOperationType() {
		return operationType;
	}

	public Stock getStockType() {
		return stockType;
	}

	public int getPrice() {
		return price;
	}
	
	public int getStockCount() {
		return stockCount;
	}
}
