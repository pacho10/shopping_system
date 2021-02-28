package com.example.shoppingapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@NotNull
	private String username;
	
	@NotEmpty
	@NotNull
	private String password;
	private String firstName;
	private String lastName;
	private Boolean isBlocked;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "roles_id")
	private Role role;
	
	public User() {
		
	}
	
	public User(String usename, String firstName, String password, String lastName, boolean isBlocked) {
		this.username = usename;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isBlocked = isBlocked;
	}
	
	public User(String usename, String password, String firstName, String lastName, boolean isBlocked, Role role) {
		this(usename, password, firstName, lastName, isBlocked);
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + username.hashCode();
		result = 31 * result + password.hashCode();
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof User)) {
			return false;
		}
		
		User user = (User) obj;
		
		return (this.getUsername().equals(user.getUsername())) && (this.getPassword().equals(user.getPassword()));
	}
	
	@Override
	public String toString() {
		return "User: id = " + id + ", username = " + username + ", firstName = " + firstName 
				+ ", lastName = " + lastName + ", isBlocked = " + isBlocked;
	}
}