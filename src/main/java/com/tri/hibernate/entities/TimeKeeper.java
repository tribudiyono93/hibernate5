package com.tri.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TIMEKEEPER")
public class TimeKeeper {

	public static final char IN = 'I';
	public static final char OUT = 'O';
	private String timeKeeperId;
	private Date dateTime;
	private Employee employee;
	private char inOut;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "Timekeeper_Id", length = 36)
	public String getTimeKeeperId() {
		return this.timeKeeperId;
	}
	
	public void setTimeKeeperId(String timeKeeperId) {
		this.timeKeeperId = timeKeeperId;
	}
	
	@Column(name = "Date_Time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Column(name = "In_Out", nullable = false, length = 1)
	public char getInOut() {
		return inOut;
	}

	public void setInOut(char inOut) {
		this.inOut = inOut;
	}
}
