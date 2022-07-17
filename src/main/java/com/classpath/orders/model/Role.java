package com.classpath.orders.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String roleName;
	
	public Role(String roleName) {
		this.roleName = roleName;
	}
	@ManyToMany
	@JoinTable(
			name="roles_users",
			joinColumns = @JoinColumn(name="role_id"),
			inverseJoinColumns = @JoinColumn(name="user_id"))
	private Set<User> users;
	
	public Set<User> getUsers(){
		if(this.users == null) {
			return new HashSet<>();
		}
		return this.users;
	}

}
