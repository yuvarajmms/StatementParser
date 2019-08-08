package com.rabobank.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.opencsv.bean.CsvBindByName;

@XmlRootElement(name="component")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@XmlAttribute(name="reference")
	@CsvBindByName(column = "Reference")
	private long reference;
	
	@XmlElement(name="accountNumber")
	@CsvBindByName(column = "AccountNumber")
	private String accountNumber;
	
	@XmlElement(name="description")
	@CsvBindByName(column = "Description")
	private String description;
	
	@XmlElement(name="startBalance")
	@CsvBindByName(column = "Start Balance")
	private double startBalance;
	
	@XmlElement(name="mutation")
	@CsvBindByName(column = "Mutation")
	private double mutation;
	
	@XmlElement(name="endBalance")
	@CsvBindByName(column = "End Balance")
	private double endBalance;
	
	public long getReference() {
		return reference;
	}
	public void setReference(long reference) {
		this.reference = reference;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}
	public double getMutation() {
		return mutation;
	}
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}
	public double getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}
	@Override
	public String toString() {
		return "Record [reference=" + reference + ", accountNumber=" + accountNumber + ", description=" + description
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", endBalance=" + endBalance + "]";
	}
	
	
	
}
