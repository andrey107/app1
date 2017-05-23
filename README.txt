-  Build:
   cd stock-app
   mvn package
   
-  Running:
   cd stock-app/target
   java -cp stock-app-1.0-SNAPSHOT.jar com.stockex.App clients.txt orders.txt
   
   result.txt is generate in the same folder
   
-  Running test:
   cd stock-app
   mvn test
   
   The description of the test is in StockProcessingTest class