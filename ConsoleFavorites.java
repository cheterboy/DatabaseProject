import java.sql.*;

public class ConsoleFavorites extends ProjQuery{

	private String console;
	
	public ConsoleFavorites(String consoleP, Connection connP) {
		super(connP);
		console = consoleP;
		setResultString(toString());
	}
	public ConsoleFavorites(ConsoleFavorites original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		console = original.getConsole();
	}
	
	public String getConsole() {
		return console;
	}
	public void  setConsole(String consoleP) {
		console = consoleP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select c.ConsoleName, g.Title" + 
						" from GAME g, CONSOLE_TYPE ct, CONSOLE c, FAVORITES f" + 
						" where g.GId = ct.GameId" +
						" and ct.ConsoleId = c.ConId" +
						" and c.ConsoleName = " + "'"+ console + "'" + 
						" and g.GId = f.GameId"; 
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("List of Favorites From A Console%n");
			tempString += String.format("%-20s %-20s%n",  "Console", "Title");
			tempString += "--------------------------------------\n";
			while (rs.next()) {
				String console = rs.getString("ConsoleName");
				String title = rs.getString("Title");
				tempString += String.format("%-20s %-20s%n", console, title);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}
