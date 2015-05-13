import java.sql.*;

public class GenreFavorites extends ProjQuery{

	private String genreTitle;
	
	public GenreFavorites(String genreTitleP, Connection connP) {
		super(connP);
		genreTitle = genreTitleP;
		setResultString(toString());
	}
	public GenreFavorites(GenreFavorites original) {
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
	
	
	//g.GId = gt.GameId , gt.GenreId = gr.GenId, g.GId = f.GameId
	public String toString() {
		String tempString = "";
		try {
			//Step 2: build and execute the query
			Statement stmt = getConn().createStatement();
			String qry = "select GenreTitle, Title" + 
						" from GAME g, GENRE_TYPE gt, GENRE gr, FAVORITES f" + 
						" where g.GId = gt.GameId" +
						" and gt.GenreId = gr.GenId" +
						" and genreTitle = " + "'"+ genreTitle + "'" + 
						" and g.GId = f.GameId"; 
			ResultSet rs = stmt.executeQuery(qry);
			tempString += ("\n");
			System.out.println("\n");
			
			//Step 3: loop through the result set
			tempString += String.format("List of Favorites with Genre%n");
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