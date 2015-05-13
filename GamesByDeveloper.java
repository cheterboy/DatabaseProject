import java.sql.*;

public class GamesByDeveloper extends ProjQuery{

	private String developer;
	
	public GamesByDeveloper(String developerP, Connection connP) {
		super(connP);
		developer = developerP;
		setResultString(toString());
	}
	public GamesByDeveloper(GamesByDeveloper original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		developer = original.getDeveloper();
	}
	
	public String getDeveloper() {
		return developer;
	}
	public void  setDeveloper(String developerP) {
		developer = developerP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select DeveloperName, Title" + 
						" from GAME, DEVELOPER_TYPE, DEVELOPER" + 
						" where GId = GameId" +
						" and DeveloperId = DevId" +
						" and DeveloperName = " + "'"+ 
						developer + "'"; 
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("%-20s %-20s%n",  "DeveloperName", "Title");
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