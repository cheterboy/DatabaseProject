import java.sql.*;

public class GamesByPublisher extends ProjQuery{

	private String publisher;
	
	public GamesByPublisher(String publisherP, Connection connP) {
		super(connP);
		publisher = publisherP;
		setResultString(toString());
	}
	public GamesByPublisher(GamesByPublisher original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		publisher = original.getPublisher();
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void  setPublisher(String publisherP) {
		publisher = publisherP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select PublisherName, Title" + 
						" from GAME, PUBLISHER" + 
						" where PublisherId = PId" + 
						" and PublisherName = " + "'"+ 
						publisher + "'"; 
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("%-20s %-20s%n",  "PublisherName", "Title");
			tempString += "--------------------------------------\n";
			while (rs.next()) {
				String publisher = rs.getString("PublisherName");
				String title = rs.getString("Title");
				tempString += String.format("%-20s %-20s%n", publisher, title);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}