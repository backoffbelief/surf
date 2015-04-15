package com.kael;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Sql {
	

	public static void main(String[] args) {
//		insert();
        //System.out.println(delete());
		
		find();
		
	}
	
	static void find(){
		try(Connection con = getCon()){
			PreparedStatement ps = null;
			ResultSet rs = null;
//			con = getCon();
			ps = con.prepareStatement("select * from (select * from posts order by dateline desc) tb group by tid order by dateline desc limit 0,10");
			rs = ps.executeQuery();
		while (rs.next())
		{
		    Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5).getTime());
		    System.out.println(post);
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	static boolean delete(){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = getCon();
			ps = con.prepareStatement("delete from posts");
			return ps.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try {
				if(ps != null){
					ps.close();
				}
				
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	static int insert() {
		Connection con = null;
		PreparedStatement ps = null;
		int[] ret = null;
		try {
			con = getCon();
			ps = con.prepareStatement("insert into posts (tid,subject,message,dateline) values(?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(int i = 0; i <= 99; i++){
				ps.setInt(1, i % 10 + 1);
				ps.setString(2,"subject" +Math.round((100 * Math.random())));
				ps.setString(3, "message"+Math.random());
				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(Math.round((10000 * Math.random())))));
				ps.addBatch();
			}
			
			ret = ps.executeBatch();
			//con.commit();
			
			return ret.length;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if(ps != null){
					ps.close();
				}
				
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/t_db", "", "");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
