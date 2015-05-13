import java.sql.*;

public class GameAllyList extends ProjQuery{

	private String title;
	private String mainCharacterName;
	
	public GameAllyList(String titleP, Connection connP, String mainCharacterNameP) {
		super(connP);
		title = titleP;
		mainCharacterName = mainCharacterNameP;
		setResultString(toString());
	}
	public GameAllyList(GameAllyList original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		title = original.getTitle();
		mainCharacterName = original.getMainCharacterName();
	}
	
	public String getTitle() {
		return title;
	}
	public void  setTitle(String titleP) {
		title = titleP;
	}
	public String getMainCharacterName() {
		return mainCharacterName;
	}
	public void setCharacterName(String mainCharacterNameP) {
		mainCharacterName = mainCharacterNameP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select Title, CharacterName, AllyName" + 
						" from MAIN_CHARACTER, GAME, ALLY" + 
						" where GameId = GId" +
						" and CharacterId = ChId" +
						" and Title = " + "'"+ title + "'" +
						" and CharacterName = " + "'"+ mainCharacterName + "'";
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("%-20s %-20s %-20s%n",  "CharacterName", "Title", "Ally");
			tempString += "-------------------------------------------------------\n";
			while (rs.next()) {
				String characterName = rs.getString("CharacterName");
				String title = rs.getString("Title");
				String ally = rs.getString("AllyName");
				tempString += String.format("%-20s %-20s %-20s%n", characterName, title, ally);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}