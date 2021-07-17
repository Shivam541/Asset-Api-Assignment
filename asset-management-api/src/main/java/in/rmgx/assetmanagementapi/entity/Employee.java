package in.rmgx.assetmanagementapi.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * Entity class used to hold the data about an Employee
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
@Entity
public class Employee {
	/**
	 * Holds the unique id of every employee.
	 */
	@Id
	@Column(name = "id")
	private int id;
	/**
	 * Holds the full name of an employee.
	 */
	@Column(name = "full_name",nullable = false)
	private String fullName;
	
	/**
	 * Holds the designation of an employee.
	 */
	@Column(name="designation",nullable = false)
	private String designation;
	
	public Employee() {

	}

	public Employee(int id, String fullName, String designation) {

		this.id = id;
		this.fullName = fullName;
		this.designation = designation;
	}

	/**
	 * 
	 * @return returns the value of id property for current object
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * sets the value for id property of current object
	 * @param id value of id property for current object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return returns the value of fullName property for current object
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 
	 * sets the value for fullName property of current object
	 * @param fullName value of fullName property for current object
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 
	 * @return returns the value of designation property for current object
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * 
	 * sets the value for designation property of current object
	 * @param designation value of designation property for current object
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * generated hash code for this class for collections
	 * @return returns hash code based on fields of the class
	 */
	@Override
	public int hashCode() {
		return Objects.hash(designation, fullName, id);
	}
	/**
	 * this method compares current instance of this objects to the instance passed in.
	 * @param obj takes in the instance to equate with.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(designation, other.designation) && Objects.equals(fullName, other.fullName)
				&& id == other.id;
	}

	
	
}
