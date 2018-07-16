package com.jdpin.api.security;

import org.springframework.stereotype.Component;

@Component
public class User {

	private String username;

	private String password;
	
	private String clientId;
	
	private String lastUpdateDTS;
	
	private String lastUpdateUser;
	
	private String rowInsertDTS;
	
	private String rowInsertUser;

	public User() {
		super();
	}
	
	public User(String username,String password,String clientId) {
		this.username=username;
		this.password=password;
		this.clientId=clientId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getLastUpdateDTS() {
		return lastUpdateDTS;
	}

	public void setLastUpdateDTS(String lastUpdateDTS) {
		this.lastUpdateDTS = lastUpdateDTS;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getRowInsertDTS() {
		return rowInsertDTS;
	}

	public void setRowInsertDTS(String rowInsertDTS) {
		this.rowInsertDTS = rowInsertDTS;
	}

	public String getRowInsertUser() {
		return rowInsertUser;
	}

	public void setRowInsertUser(String rowInsertUser) {
		this.rowInsertUser = rowInsertUser;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password="
				+ password + ", clientId=" + clientId + ", lastUpdateDTS="
				+ lastUpdateDTS + ", lastUpdateUser=" + lastUpdateUser
				+ ", rowInsertDTS=" + rowInsertDTS + ", rowInsertUser="
				+ rowInsertUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result
				+ ((lastUpdateDTS == null) ? 0 : lastUpdateDTS.hashCode());
		result = prime * result
				+ ((lastUpdateUser == null) ? 0 : lastUpdateUser.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((rowInsertDTS == null) ? 0 : rowInsertDTS.hashCode());
		result = prime * result
				+ ((rowInsertUser == null) ? 0 : rowInsertUser.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (lastUpdateDTS == null) {
			if (other.lastUpdateDTS != null)
				return false;
		} else if (!lastUpdateDTS.equals(other.lastUpdateDTS))
			return false;
		if (lastUpdateUser == null) {
			if (other.lastUpdateUser != null)
				return false;
		} else if (!lastUpdateUser.equals(other.lastUpdateUser))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rowInsertDTS == null) {
			if (other.rowInsertDTS != null)
				return false;
		} else if (!rowInsertDTS.equals(other.rowInsertDTS))
			return false;
		if (rowInsertUser == null) {
			if (other.rowInsertUser != null)
				return false;
		} else if (!rowInsertUser.equals(other.rowInsertUser))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
