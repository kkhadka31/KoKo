package com.koshish.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class Person implements UserDetails {

	/**
	 * To-do:
	 * Make id auto generated later
	 * Command to use: @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
	private long id;
	private String firstName;
	private String secondName;
	private String fullName;
	private String email;
	private String gender;
	private String phoneNumber;
	private String password;
	private String userName;

	//todo: add Role to the table and modify mock values
	private Role role = Role.USER;
	
	public Person() {}
	
	public Person(long id, String firstName, String secondName, String email, String gender, String phoneNumber) {
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.fullName = firstName + " " + secondName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(this.role.name()));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
