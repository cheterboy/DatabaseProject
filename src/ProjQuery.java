import java.sql.*;

public abstract class ProjQuery {
	
	private Connection conn;
	private String resultString;
	
	
	public ProjQuery(Connection connP) {
		conn = connP;
	}
	public ProjQuery(ProjQuery original) {
		this.conn = original.conn;
		this.resultString = original.resultString;
	}
	
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultStringP) {
		resultString = resultStringP;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection connP) {
		conn = connP;
	}
	public abstract String toString();
}
