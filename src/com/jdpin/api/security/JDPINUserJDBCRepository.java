package com.jdpin.api.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="jdPINUserJDBCRepository")
public class JDPINUserJDBCRepository {

	@Autowired
	DataSource dataSource;

	public User findByUserId(String userId) {

		String sql = "SELECT USER_ID,PASSWORD,CLIENT_ID FROM PIN_WEB_USER WHERE USER_ID = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			User customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new User(rs.getString("USER_ID"),
						rs.getString("PASSWORD"), rs.getString("CLIENT_ID"));
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
