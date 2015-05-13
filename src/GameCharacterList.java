import java.sql.*;

public class GameCharacterList extends ProjQuery{

	private String title;
	
	public GameCharacterList(String titleP, Connection connP) {
		super(connP);
		title = titleP;
		setResultString(toString());
	}
	public GameCharacterList(GameCharacterList original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		title = original.getTitle();
	}
	
	public String getTitle() {
		return title;
	}
	public void  setTitle(String titleP) {
		title = titleP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select CharacterName, Title" + 
						" from MAIN_CHARACTER, GAME" + 
						" where GameId = GId" + 
						" and Title = " + "'"+ title + "'";
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("%-20s %-20s%n",  "CharacterName", "Title");
			tempString += "---------------------------\n";
			while (rs.next()) {
				String characterName = rs.getString("CharacterName");
				String title = rs.getString("Title");
				tempString += String.format("%-20s %-20s%n", characterName, title);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}
