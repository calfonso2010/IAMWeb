package fr.tbr.iamcore.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tbrou
 *
 */
@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue
	long id;
	
	private String login;
	private String password;
	
	
	public User(String username, String password) {
		this.login = username;
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public final String getLogin() {
		return login;
	}
	public final void setLogin(String login) {
		this.login = login;
	}

}