package sg.edu.iss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="userId")
	private int userId;
	
	
	@Column(name ="userName")
	@NotEmpty(message="Please fill in username")
	@Size(min=2, message = "The username must be more than 2 characters")
	@Size(max=50, message= "The username cannot be more than 50 characters")
	@Pattern(regexp = "[a-zA-Z0-9]+", message="The username should not include special characters")
	private String userName;
	
	@Column(name ="password")
	@NotEmpty(message="Please fill in password")
	@Size(min=2, message = "The password must be more than 2 characters")
	@Size(max=50, message= "The password cannot be more than 50 characters")	
	private String password;
	
	@NotEmpty(message="Please fill in email address")
	@Column(name="email")
	@Email(message="please enter valid email")
	private String email;
	
	@Column(name ="roleType")
	private RoleType roleType;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userId, String userName, String password, RoleType roleType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.roleType = roleType;
	}
	public User(String userName, String password, RoleType roleType) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleType = roleType;
	}
	public User(String userName, String password, String email, RoleType roleType) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roleType = roleType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", roleType=" + roleType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roleType == null) ? 0 : roleType.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleType != other.roleType)
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
}

