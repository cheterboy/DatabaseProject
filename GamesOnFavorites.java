import java.sql.*;

public class GamesOnFavorites extends ProjQuery{
	
	public GamesOnFavorites(Connection connP) {
		super(connP);
		setResultString(toString());
	}
	public GamesOnFavorites(GamesOnFavorites original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select Title" + 
						" from GAME, FAVORITES" + 
						" where GId = GameId";
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("%-20s%n", "Favorites");
			tempString += "-------------------------\n";
			while (rs.next()) {
				String title = rs.getString("Title");
				tempString += String.format("%-20s%n", title);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}