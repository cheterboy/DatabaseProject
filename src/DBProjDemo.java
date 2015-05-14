import java.sql.*;

import org.apache.derby.drda.NetworkServerControl;
import org.apache.derby.jdbc.ClientDriver;


//import org.apache.derby.jdbc.EmbeddedDriver;
import java.util.Scanner;
import java.util.InputMismatchException;

public class DBProjDemo {

	public static Scanner keyboardObj = new Scanner(System.in);
	public static String incorrectEntry = "Not a correct entry: Try Again:";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		boolean moreQueries = true;

		
		try {
			NetworkServerControl server = new NetworkServerControl();
			server.start (null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Driver d = new ClientDriver();
		//Driver d = new EmbeddedDriver();
		// String url = "jdbc:derby:/home/dc/workspace/DBProj/gamedb";
		
		
		
		
		String url = "jdbc:derby://localhost:1527/gamedb;create=true";
		
		
		// try to connect to gamedb
		try {
			conn = d.connect(url, null);// The connection to gamedb
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
		
		// Main loop for querying. Continues until user stops the program.
		while (moreQueries) {
			printMainMenu();
			switchMain(conn);
			moreQueries = askToContinueQuerying();
		}

		// close the connection to gamedb
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Exiting Application");
	}

	// -----------------------------------------------------------------------//
	// ----------------------I/O Methods Start--------------------------------//
	// -----------------------------------------------------------------------//
	/*private static void printSchema(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int numbColumns = md.getColumnCount();

		System.out.println("There are " + numbColumns + " output columns");

		for (int i = 1; i <= numbColumns; i++) {
			String name = md.getColumnName(i);
			int size = md.getColumnDisplaySize(i);
			int typecode = md.getColumnType(i);

			String type;
			if (typecode == Types.INTEGER)
				type = "int";
			else if (typecode == Types.VARCHAR)
				type = "string";
			else
				type = "other";
			System.out.println(name + "\t" + type + "\t" + size
					+ " characters wide");
		}
	}*/

	private static boolean askToContinueQuerying() {
		int userInput = 0;

		System.out.println("\n" + "Continue?\n" + "1: Yes\n" + "2: No\n");
		userInput = userEnterInteger();
		switch (userInput) {
		case 1:
			return true;
		case 2:
			return false;
		default:
			System.out.println("Error:	askToContinueQuerying	"
					+ "value out of range\n" + "Continuing To Query");
			return true;

		}
	}

	private static void printMainMenu() {
		System.out.println("VIDEO GAME DATABASE\n" + "-------------------\n"
				+ "Select From The Following\n" + "1: Lists\n"
				+ "2: Table Entries\n");
	}

	private static void switchMain(Connection conn) {
		int userInput = 0;

		userInput = userEnterInteger();
		switch (userInput) {
		case 1: // Go to the List Menu
			printListMenu();
			switchList(conn);
			break;
		case 2: // Go to TableEntries Menu
			printTableEntriesMenu();
			switchTableEntries(conn);
			break;
		default:
			System.out.println("Error:	switchMain	value out of" + " range");
			break;
		}
	}

	private static void printListMenu() {
		System.out.println("" + "Select A List Option:\n"
				+ "------------------------------\n"
				+ " 1: List Of Characters In A Game\n"
				+ " 2: List Of Allies In A Game\n"
				+ " 3: List Of Games With A Certain Number Of Sales\n"
				+ " 4: List Of Games On A Certain Console\n"
				+ " 5: List Of Games Of A Certain Genre\n"
				+ " 6: List Of Games By A Certain Publisher\n"
				+ " 7: List Of Games By A Certain Developer\n"
				+ " 8: List Of Games On The Favorites List\n"
				+ " 9: Favorites List From A Genre\n"
				+ "10: Favorites Developer List\n"
				+ "11: Favorites List From A Paricular Console\n");
	}

	private static void switchList(Connection conn) {
		int userInput = 0;
		String userInputString = "";

		userInput = userEnterInteger();
		switch (userInput) {
		case 1: // List of Main_Characters In A Game
			System.out.println("List Of Main Characters In A Game");
			printTableEntries(conn, "GAME", "Title");
			System.out.println("Enter The Title Of The Game");
			userInputString = userEnterString();
			GameCharacterList tempGameCharacterList = new GameCharacterList(
					userInputString, conn);
			System.out.println(tempGameCharacterList.getResultString());
			break;
		case 2: // List Of Allies In A Game
			System.out.println("List Of Allies In A Game");
			printTableEntries(conn, "GAME", "Title");
			System.out.println("Enter The Title Of The Game");
			String userInputTitle = userEnterString();
			printTableEntries(conn, "MAIN_CHARACTER", "CharacterName");
			System.out.println("Enter The Main Character");
			String userInputMainCharacter = userEnterString();
			GameAllyList tempGameAllyList = new GameAllyList(userInputTitle,
					conn, userInputMainCharacter);
			System.out.println(tempGameAllyList.getResultString());
			break;
		case 3: // List Of Games With A Certain Number Of Sales//CANT DO WITH
				// CURRENT DB
			System.out.println("List Of Games With A Certain Number Of Sales");
			break;
		case 4: // List Of Games On a Certain Console
			System.out.println("List Of Games On A Certain Console");
			printTableEntries(conn, "CONSOLE", "ConsoleName");
			System.out.println("Enter The Name Of The Console");
			String userInputConsole = userEnterString();
			GamesOnConsole tempGamesOnConsole = new GamesOnConsole(
					userInputConsole, conn);
			System.out.println(tempGamesOnConsole.getResultString());
			break;
		case 5: // List Of Games Of A Certain Genre
			System.out.println("List Of Games Of A Certain Genre");
			printTableEntries(conn, "GENRE", "GenreTitle");
			System.out.println("Enter The Genre");
			String userInputGenre = userEnterString();
			GamesInGenre tempGamesInGenre = new GamesInGenre(userInputGenre,
					conn);
			System.out.println(tempGamesInGenre.getResultString());
			break;
		case 6: // List Of Games By A Certain Publisher
			System.out.println("List Of Games By A Certain Publisher");
			printTableEntries(conn, "PUBLISHER", "PublisherName");
			System.out.println("Enter The Publisher");
			String userInputPublisher = userEnterString();
			GamesByPublisher tempGamesByPublisher = new GamesByPublisher(
					userInputPublisher, conn);
			System.out.println(tempGamesByPublisher.getResultString());
			break;
		case 7: // List Of Games By A Certain Developer
			System.out.println("List Of Games By A Certain Developer");
			printTableEntries(conn, "DEVELOPER", "DeveloperName");
			System.out.println("Enter The Developer");
			String userInputDeveloper = userEnterString();
			GamesByDeveloper tempGamesByDeveloper = new GamesByDeveloper(
					userInputDeveloper, conn);
			System.out.println(tempGamesByDeveloper.getResultString());
			break;
		case 8: // List Of Games On The Favorites List
			System.out.println("List Of Games On The Favorites List");
			GamesOnFavorites tempGamesOnFavorites = new GamesOnFavorites(conn);
			System.out.println(tempGamesOnFavorites.getResultString());
			break;
		case 9: // Favorites List From A Genre
			System.out.println("Favorites List From A Genre");
			printTableEntries(conn, "GENRE", "GenreTitle");
			System.out.println("Enter The Genre");
			String userInputGenre2 = userEnterString();
			GenreFavorites tempGenreFavorites = new GenreFavorites(
					userInputGenre2, conn);
			System.out.println(tempGenreFavorites.getResultString());
			break;
		case 10: // Favorites Developer List
			System.out.println("Favorites Developer List");
			printTableEntries(conn, "DEVELOPER", "DeveloperName");
			System.out.println("Enter The Developer");
			String userInputDeveloper2 = userEnterString();
			DeveloperFavorites tempDeveloperFavorites = new DeveloperFavorites(
					userInputDeveloper2, conn);
			System.out.println(tempDeveloperFavorites.getResultString());
			break;
		case 11: // Favorites List From A Particular Console
			System.out.println("Favorites List From A Paricular Console");
			printTableEntries(conn, "CONSOLE", "ConsoleName");
			System.out.println("Enter The Console");
			String userInputConsole2 = userEnterString();
			ConsoleFavorites tempConsoleFavorites = new ConsoleFavorites(
					userInputConsole2, conn);
			System.out.println(tempConsoleFavorites.getResultString());
			break;
		default:
			System.out.println("Error:	switchList	value out of" + " range");
			break;
		}
	}

	private static void printTableEntriesMenu() {
		System.out.println("Select A Table\n"
				+ "------------------------------------------\n"
				+ "1: Main Characters\n" + "2: Ally\n" + "3: Game\n"
				+ "4: Genre\n" + "5: Console\n" + "6: Publisher\n"
				+ "7: Developer\n");
	}

	private static void switchTableEntries(Connection conn) {
		int userInputInt = 0;

		userInputInt = userEnterInteger();
		switch (userInputInt) {
		case 1: // Print all Main_Character Entries
			System.out
					.println("List Of Main_Character Entries In The Database");
			printTableEntries(conn, "MAIN_CHARACTER", "CharacterName");
			break;
		case 2: // Print all Ally Entries
			System.out.println("List Of Ally Entries In The Database");
			printTableEntries(conn, "ALLY", "AllyName");
			break;
		case 3: // Print all Game Entries
			System.out.println("List Of Game Entries In The Database");
			printTableEntries(conn, "GAME", "Title");
			break;
		case 4: // Print all Genre Entries
			System.out.println("List Of Genre Entries In The Database");
			printTableEntries(conn, "GENRE", "GenreTitle");
			break;
		case 5: // Print all Console Entries
			System.out.println("List Of Console Entries In The Database");
			printTableEntries(conn, "CONSOLE", "ConsoleName");
			break;
		case 6: // Print all Publisher Entries
			System.out.println("List Of Publisher Entries In The Database");
			printTableEntries(conn, "PUBLISHER", "PublisherName");
			break;
		case 7: // Print all Developer Entries
			System.out.println("List Of Developer Entries In The Database");
			printTableEntries(conn, "DEVELOPER", "DeveloperName");
			break;
		default:
			System.out.println("Error:	switchList	value out of" + " range");
			break;
		}
	}

	private static int userEnterInteger() {
		int tempInt = 0;
		boolean goodEntry = false;

		while (!goodEntry) {
			try {
				tempInt = keyboardObj.nextInt();
				keyboardObj.nextLine();
				goodEntry = true;
			} catch (InputMismatchException a) {
				keyboardObj.nextLine();
				System.out.println(incorrectEntry);
			} catch (Exception a) {
				keyboardObj.nextLine();
				System.out.println(a.getMessage());
			}
		}
		return tempInt;
	}

	private static String userEnterString() {
		String tempString = "";
		boolean goodEntry = false;

		while (!goodEntry) {
			try {
				tempString = keyboardObj.nextLine();
				goodEntry = true;
			} catch (InputMismatchException a) {
				keyboardObj.nextLine();
				System.out.println(incorrectEntry);
			} catch (Exception a) {
				keyboardObj.nextLine();
				System.out.println(a.getMessage());
			}
		}
		return tempString;
	}

	private static void printTableEntries(Connection conn, String tableName,
			String columnName) {

		String tempString = "";
		try {
			Statement stmt = conn.createStatement();
			String qry = "select " + columnName + " from " + tableName;
			ResultSet rs = stmt.executeQuery(qry);

			int count = 0;
			while (rs.next()) {
				if ((count % 2) == 0)// 2 entries per row in string
					tempString += "\n";
				String resultString = rs.getString(columnName);
				tempString += String.format("%-40s", resultString);
				count++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(tempString + "\n\n");
	}
	// -----------------------------------------------------------------------//
	// --------------------------I/O Method End-------------------------------//
	// -----------------------------------------------------------------------//

}