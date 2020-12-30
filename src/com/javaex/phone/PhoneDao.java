package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	// db정보
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// db연결
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
			
		//번호 저장
		public int personInsert(PhoneVo pvo) {	
			int count = 0;
			getConnection();
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			//"INSERT INTO person VALUES (seq_person_id.nextval,?,?,?)"
			String query ="INSERT INTO person VALUES (seq_person_id.nextval,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pvo.getName());
			pstmt.setString(2, pvo.getHp());
			pstmt.setString(3, pvo.getCompany());
			System.out.println(query);
			
			count = pstmt.executeUpdate();
		    // 4.결과처리
			System.out.println("[DAO]: " + count + "건이 저장되었습니다.");
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
			close();
			return 0;
		}
		//번호삭제
		public int persondelete(int personid) {	
			int count = 0;
			getConnection();
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			//"INSERT INTO person VALUES (seq_person_id.nextval,?,?,?)"
			String query ="DELETE FROM person WHERE person_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personid);
			System.out.println(query);
			count = pstmt.executeUpdate();
		    // 4.결과처리
			System.out.println("[DAO]: " + count + "건이 삭제되었습니다.");
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
			close();
			return count;
		}
		
		
		//번호수정
		public int personUpdate(int personId, String name, String hp, String company ) {
			getConnection();
			int count = 0;
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				//UPDATE person SET name =?,hp=?,company=? WHERE person_id = ?				String query = "";
				String query ="";
				pstmt = conn.prepareStatement(query);
				query += " UPDATE person ";
				query += " SET name =?, ";
				query += " hp=? ";
				query += " company=? ";
				query += " WHERE person_id = ? ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,name);
				pstmt.setString(2,hp );
				pstmt.setString(3,company );
				pstmt.setInt(4, personId);
				System.out.println(query);
				count = pstmt.executeUpdate(); // update

				// 4.결과처리
				System.out.println("[DAO]: " + count + "건이 수정되었습니다.");

			}  catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			close();
			return count;

		}
		
		
		//번호 리스트
	public List<PhoneVo> getpersonList() {
		List<PhoneVo> pList = new ArrayList<PhoneVo>();
		getConnection();
		try {
			//"INSERT INTO person VALUES (seq_person_id.nextval,?,?,?)"
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select person_id, ";
			query += " 		  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " FROM person ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();// select

			// 4.결과처리
			while (rs.next()) {

				int pid = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				PhoneVo pvo01 = new PhoneVo(pid, name, hp, company);
				pList.add(pvo01);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}close();

		return pList;

	}
	//검색
public List<PhoneVo> phoneSearch(String searchWord) {
		
		List<PhoneVo> phoneVoList = new ArrayList<PhoneVo>();
		
		getConnection();
		
		searchWord = "%" + searchWord + "%";
		
		try {	
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select	person_id,";
			query += "			name,";
			query += "			hp,";
			query += "			company";
			query += " from person";
			query += " where name like ?";
			query += " or hp like ?";
			query += " or company like ?";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, searchWord);
			pstmt.setString(2, searchWord);
			pstmt.setString(3, searchWord);
			
			rs = pstmt.executeQuery();
	        
			// 4.결과처리
			while(rs.next()){
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PhoneVo phoneVo = new PhoneVo(personId, name, hp, company);
				phoneVoList.add(phoneVo);
			}
		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		close();
		
		return phoneVoList;
}
}
