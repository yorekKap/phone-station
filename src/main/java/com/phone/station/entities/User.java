package com.phone.station.entities;

import java.util.Date;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.DateSQL;
import com.phone.station.dao.annotations.Id;
import com.phone.station.dao.annotations.ParseEnum;
import com.phone.station.dao.annotations.Table;
import com.phone.station.dao.annotations.enums.DateType;
import com.phone.station.entities.enums.Role;

/**
 * @author yuri
 *
 */
@Table("users")
public class User implements Identified<Long> {

	@Id
	@Column("id")
	private Long id;

	@Column("first_name")
	private String firstName;

	@Column("last_name")
	private String lastName;

	@Column("phone")
	private String phone;

	@Column("additional_phone")
	private String additionalPhone;

	@Column("username")
	private String username;

	@Column("password")
	private String password;

	@Column("balance")
	private double balance;

	@Column("tariff_id")
	private Long tariffId;

	@DateSQL(DateType.DATE)
	@Column("registration_date")
	private Date registrationDate;

	private Tariff tariff;

	@Column("user_role")
	@ParseEnum()
	private Role userRole;


	public User() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdditionalPhone() {
		return additionalPhone;
	}

	public void setAdditionalPhone(String additionalPhone) {
		this.additionalPhone = additionalPhone;
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Long getTariffId() {
		return tariffId;
	}

	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", additionalPhone=" + additionalPhone + ", username=" + username + ", password=" + password
				+ ", balance=" + balance + ", tariffId=" + tariffId + ", registrationDate=" + registrationDate
				+ ", tariff=" + tariff + ", userRole=" + userRole + "]";
	}



}
