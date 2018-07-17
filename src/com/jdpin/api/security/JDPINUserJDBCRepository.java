package com.jdpin.api.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="jdPINUserJDBCRepository")
public class JDPINUserJDBCRepository {
	
	public static final Logger logger = Logger.getLogger(JDPINUserJDBCRepository.class);
	
	@Autowired
	DataSource dataSource;

	public User findByUserId(String userId) {
		logger.debug("findByUserId() started for authenticating username:"+userId);
		String sql = "SELECT USER_ID,PASSWORD,CLIENT_ID FROM PIN_WEB_USER WHERE USER_ID = ?";
		logger.debug("User validation query:"+sql);
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			User user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("USER_ID"),
						rs.getString("PASSWORD"), rs.getString("CLIENT_ID"));
			}
			rs.close();
			ps.close();
			logger.debug("User details:"+user);
			return user;
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
