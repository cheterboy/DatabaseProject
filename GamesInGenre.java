import java.sql.*;

public class GamesInGenre extends ProjQuery{

	private String genreTitle;
	
	public GamesInGenre(String genreTitleP, Connection connP) {
		super(connP);
		genreTitle = genreTitleP;
		setResultString(toString());
	}
	public GamesInGenre(GamesInGenre original) {
		super(original.getConn());
		setConn(original.getConn());
		setResultString(original.getResultString());
		genreTitle = original.getGenreTitle();
	}
	
	public String getGenreTitle() {
		return genreTitle;
	}
	public void  setGenreTitle(String genreTitleP) {
		genreTitle = genreTitleP;
	}
	
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select GenreTitle, Title" + 
						" from GAME, GENRE_TYPE, GENRE" + 
						" where GameId = GId" + 
						" and genreTitle = " + "'"+ genreTitle + "'" + 
						" and GenId = GenreId"; 
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("%-20s %-20s%n",  "Genre", "Title");
			tempString += "--------------------------------------\n";
			while (rs.next()) {
				String genreTitle = rs.getString("GenreTitle");
				String title = rs.getString("Title");
				tempString += String.format("%-20s %-20s%n", genreTitle, title);
			}
			rs.close();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempString;
	}	
}