import java.sql.*;
import java.lang.Exception;

public class CreateTables extends ProjQuery {

	public CreateTables(Connection connP) {
		super(connP);}
		// TODO Auto-generated constructor stub
		
		/*
		
		
			// Creating a database table
		    Statement sta;
			try {
				sta = getConn().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    //sta.executeUpdate("CREATE TABLE HY_Address (ID INT, StreetName VARCHAR(20), City VARCHAR(20))");
		   

		    try {
				sta.execute("create table GENRE (GenId int not null,GenreTitle varchar(30) not null DEFAULT" +
						 " 'no Title',primary key(GenId),check (GenId >0))");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		  
		    

		    try {
				sta.execute( 		"create table CONSOLE (" +  
						"ConId int not null,"+ 
"		    		ConsoleName varchar(30) not null DEFAULT 'No Name', "+
"		    		LaunchYear int not null DEFAULT 0, "+
"		    		primary key(ConId),"+
"		    		check (ConId > 0), "+
						"check(LaunchYear > 1975 or LaunchYear = 0))");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

 try {
	sta.execute("		    		create table PUBLISHER ("+
	"		    		PId int not null, "+
	"		    		PublisherName varchar(30) not null DEFAULT 'No Name',"+
	"		    		Address varchar(30) not null DEFAULT 'No Address', "+
	"		    		State varchar(30) not null DEFAULT  'No State',"+
	"		    		primary key(PId),"+
	"		    		check (PId > 0));");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

 try {
	sta.execute("		    		create table GAME ("+
	"		    		GId int not null, "+
	"		    		Title varchar(30) not null DEFAULT 'No Title',"+ 
	"		    		YearMade int not null DEFAULT 0, "+
	"		    		PublisherId int not null, "+
	"		    		Statistics int not null DEFAULT 0,"+ 
	"		    		primary key(GId),"+
	"		    		foreign key(PublisherId) references PUBLISHER on update restrict,"+
	"		    		check (GId > 0),"+
	"		    		check (YearMade > 1975 or YearMade = 0),"+
	"		    		check (publisherId > 0),"+
	"		    		check (Statistics > -1));");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


 try {
	sta.execute("		    		create table MAIN_CHARACTER ("+
	"		    		ChId int not null, "+
	"		    		GameId int not null, "+
	"		    		CharacterName varchar(30) not null DEFAULT 'No Name', "+
	"		    		CharacterGender varchar(30) not null DEFAULT 'No Gender' ,"+
	"		    		primary key(ChId), "+
	"		    		foreign key(GameId) references GAME on delete cascade on update restrict,"+
	"		    		check (chid > 0),"+
	"		    		check (GameId > 0));");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

 try {
	sta.execute("		    		create table ALLY ("+
	"		    		AId int not null, "+
	"		    		CharacterId int not null,"+ 
	"		    		AllyName varchar(30) not null DEFAULT 'No Name',"+ 
	"		    		AllyGender varchar(30) not null DEFAULT 'No Gender', "+
	"		    		foreign key(CharacterId) references MAIN_CHARACTER on delete cascade,"+
	"	    		check (AId >0),"+
	"		    		check (characterId > 0));");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}




 try {
	sta.execute("		    		create table GENRE_TYPE ("+
	"		    		GTId int not null, "+
	"		    		GameId int not null, "+
	"		    		GenreId int not null, "+
	"		    		primary key(GTId),"+
	"		    		foreign key(GameId) references GAME on delete cascade,"+
	"		    		foreign key(GenreId) references GENRE,"+
	"		    		check (gtid >0),"+
	"		    		check (gameId >0),"+
	"		    		check (genreId > 0));");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


 try {
	sta.execute("		    		create table CONSOLE_TYPE ("+
	"		    		CTId int not null, "+
	"		    		GameId int not null, "+
	"		    		ConsoleId int not null,"+ 
	"		    		primary key(CTId),"+
	"		    		foreign key(GameId) references GAME on delete cascade,"+
	"		    		foreign key(ConsoleId) references CONSOLE,"+
	"		    		check (Ctid > 0), "+
	"		    		check (gameId > 0), "+
	"		    		check (ConsoleId > 0));");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}







 try {
	sta.execute("		    		create table DEVELOPER ("+
	"		    		DevId int not null, "+
	"		    		DeveloperName varchar(30) not null DEFAULT 'No Name',"+ 
	"		    		EmailAddress varchar(30) not null DEFAULT 'No Email',"+
	"		    		primary key(DevId),"+
	"		    		check (devId > 0) );");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}



 try {
	sta.execute("		    		create table DEVELOPER_TYPE ("+
	"		    		DTId int not null, "+
	"		    		GameId int not null,"+
	"		    		DeveloperId int not null,"+
	"		    		primary key(DTId),"+
	"		    		foreign key(GameId) references GAME on delete cascade,"+
	"		    		foreign key(DeveloperId) references DEVELOPER,"+
	"		    		check (dtid >0),"+
	"		    		check (gameid >0),"+
	"		    		check (DeveloperId >0));");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}



 try {
	sta.execute("		    		create table FAVORITES ("+
	"		    		FId int not null, "+
	"		    		GameId int not null,"+
	"		    		primary key(FId),"+
	"		    		foreign key(GameId) references GAME on delete cascade,"+
	"		    		check (Fid >0),"+
	"		    		check (GameId > 0)"+ 
	"		    		);");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

		    
		    System.out.println("Table created.");
		    try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        

		         


		
		
	}*/

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
