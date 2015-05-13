import java.sql.*;

public class GamesOnConsole extends ProjQuery{

	private String consoleName;
	
	public GamesOnConsole(String consoleNameP, Connection connP) {
		super(connP);
		consoleName = consoleNameP;
		setResultString(toString());
	}
	public GamesOnConsole(GamesOnConsole original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		consoleName = original.getConsoleName();
	}
	
	public String getConsoleName() {
		return consoleName;
	}
	public void  setConsoleName(String consoleNameP) {
		consoleName = consoleNameP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select ConsoleName, Title" + 
						" from CONSOLE_TYPE, GAME, CONSOLE" + 
						" where GameId = GId" + 
						" and ConsoleName = " + "'"+ consoleName + "'" + 
						" and ConsoleId = ConId"; 
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("%-20s %-20s%n",  "ConsoleName", "Title");
			tempString += "--------------------------------------\n";
			while (rs.next()) {
				String consoleName = rs.getString("ConsoleName");
				String title = rs.getString("Title");
				tempString += String.format("%-20s %-20s%n", consoleName, title);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}