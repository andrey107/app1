-  Build:
   cd app1
   mvn package
   
-  Running:
   cd app1/target
   java -cp stock-app-1.0-SNAPSHOT.jar com.stockex.App clients.txt orders.txt
   
   result.txt is generate in the same folder
   
-  Running test:
   cd app1
   mvn test
   
   The description of the test is in StockProcessingTest class