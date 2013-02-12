	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.Vector;
	
	public class DatabaseDataSource {
	public WarehouseAuctionItem[] getAuctionItems(){
	WarehouseAuctionItem[] items;
	Vector<WarehouseAuctionItem> auctionVector = new Vector<WarehouseAuctionItem>();
	Connection conn = null;
	Statement sqlstatement;
	try {
		// Set up the driver needed to connect to DB2 Universal DB
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Create the jdbc connection string
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse");
		// A debug statement to ensure you actually did connect to the database
		System.out.println("DB Connection Successful.");
		} catch (Exception e) {
		e.printStackTrace();
		}
		try {
		//SQL query select all items from local warehouse.
		sqlstatement = conn.createStatement();
		ResultSet results = sqlstatement.executeQuery("SELECT * from warehousefactory");
		// Place all of the results in a vector
		while(results.next()){
		WarehouseAuctionItem item = new WarehouseAuctionItem();
		item.setItemID(results.getInt("itemID"));
		item.setItemTitle(results.getString("itemTitle"));
		item.setItemDesc(results.getString("itemDesc"));
		item.setItemPrice(results.getInt("itemPrice"));
		
		auctionVector.add(item);
		}
		} catch (Exception e1) {
		e1.printStackTrace();
		}
		// Place all the elements from the vector into a properly-typed array.
		items = new WarehouseAuctionItem[auctionVector.size()];
		for(int i = 0; i<auctionVector.size(); i++) {
		items[i] = (WarehouseAuctionItem)auctionVector.elementAt(i);
		}
		return items;
		}}