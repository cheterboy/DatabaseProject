import java.sql.*;

public class DeveloperFavorites extends ProjQuery{

	private String developer;
	
	public DeveloperFavorites(String developerP, Connection connP) {
		super(connP);
		developer = developerP;
		setResultString(toString());
	}
	public DeveloperFavorites(DeveloperFavorites original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		developer = original.getDeveloper();
	}
	
	public String getDeveloper() {
		return developer;
	}
	public void  setGenreTitle(String developerP) {
		developer= developerP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select d.DeveloperName, g.Title" + 
						" from GAME g, DEVELOPER_TYPE dt, DEVELOPER d, FAVORITES f" + 
						" where g.GId = dt.GameId" +
						" and dt.DeveloperId = d.DevId" +
						" and d.DeveloperName = " + "'"+ developer + "'" + 
						" and g.GId = f.GameId"; 
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("List of Favorites From Developer%n");
			tempString += String.format("%-20s %-20s%n",  "Developer", "Title");
			tempString += "--------------------------------------\n";
			while (rs.next()) {
				String developer = rs.getString("DeveloperName");
				String title = rs.getString("Title");
				tempString += String.format("%-20s %-20s%n", developer, title);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}