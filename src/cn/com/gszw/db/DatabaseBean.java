package cn.com.gszw.db;
import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;

public class DatabaseBean {
	 private Connection conn;
	 private Statement stmt;
	 private PreparedStatement prestmt;
	 
	 public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Statement getStmt() {
		return stmt;
	}
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	public PreparedStatement getPrestmt() {
		return prestmt;
	}
	public void setPrestmt(PreparedStatement prestmt) {
		this.prestmt = prestmt;
	}
	public DatabaseBean(){
		 try{
			 OracleDataSource ods = new OracleDataSource();
			 ods.setUser("db_xtwh");
			 ods.setPassword("db_xtwh");
			 ods.setURL("jdbc:oracle:thin:@//localhost:1521/orcl");
			 conn=ods.getConnection();
			 stmt=conn.createStatement();
		 } catch(SQLException e){
			 	e.printStackTrace();
		 }
	 }

	 public ResultSet executeQuery(String sql) throws SQLException{
		 return stmt.executeQuery(sql);
	 }
	 public boolean executeUpdate(String sql){
		 try{
			 return stmt.executeUpdate(sql)!=0;
		 }catch(SQLException e){
			 e.printStackTrace();
			 return false;
		 }
	 }
	 public boolean batchUpdate(String[] sqls){
		 int[] temp =null;
		 try{
			 conn.setAutoCommit(false);
			 for(String sql:sqls){
				 stmt.addBatch(sql);
			 }
			 temp=stmt.executeBatch();
			 conn.commit();
		 }catch(SQLException e){
			 try{
				 conn.rollback();
			 }catch(SQLException e1){
				 e1.printStackTrace();
			 }
			 e.printStackTrace();
			 return false;
		 }
		 return temp.length!=0;
	 }
	 public void close(){
		 if(stmt!=null){
			 try{
				 stmt.close();
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
			 if(conn!=null){
				 try{
					 conn.close();
				 }catch(SQLException e){
					 e.printStackTrace();
				 }
			 }
		 }
	 }
}
