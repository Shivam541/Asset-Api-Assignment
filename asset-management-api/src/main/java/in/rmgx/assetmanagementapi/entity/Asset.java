package in.rmgx.assetmanagementapi.entity;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * Entity class used to hold the data about an asset
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
@Entity
@XmlRootElement
public class Asset {

	/**
	 * Holds the unique name of an asset.
	 */
	@Id
	@Column(name="id")
	private String name;

	/**
	 * A description of the current condition of an asset.
	 */
	@Column(name = "condition_notes", columnDefinition = "varchar(300)",nullable = false)
	private String conditionNotes;

	/**
	 * Date on which the asset is purchased
	 */
	@Column(name = "purchase_date",nullable = false)
	private Date purchaseDate;

	/**
	 * this object specifies the category to which the asset belongs
	 */
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;

	/**
	 * Specifies a string about the current status of the asset
	 * can be one from assigned,recovered,available.
	 */
	@Column(name = "assignment_status", columnDefinition = "varchar(20)",nullable = false)
	private String assignmentStatus;
	
	/**
	 * This class specifies that current asset is assigned to which employee
	 * it is null if asset is not in assigned state.
	 * 
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "employee_id",referencedColumnName = "id")
	private Employee assignedTo;

	public Asset(String name, String conditionNotes, Date purchaseDate, Category category, String assignmentStatus) {
		this.name = name;
		this.conditionNotes = conditionNotes;
		this.purchaseDate = purchaseDate;
		this.category = category;
		this.assignmentStatus = assignmentStatus;
	}

	public Asset() {
	}

	/**
	 * 
	 * @return returns the value of name property for current object
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * sets the value for name property of current object
	 * @param name value of name property for current object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return returns the value of name property for current object
	 */
	public String getConditionNotes() {
		return conditionNotes;
	}

	/**
	 * 
	 * sets the value for conditionNotes property of current object
	 * @param conditionNotes value of conditionNotes property for current object
	 */
	public void setConditionNotes(String conditionNotes) {
		this.conditionNotes = conditionNotes;
	}

	/**
	 * 
	 * @return returns the value of name property for current object
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * 
	 * sets the value for purchaseDate property of current object
	 * @param purchaseDate value of purchaseDate property for current object
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * 
	 * @return returns the value of name property for current object
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * 
	 * sets the value for category property of current object
	 * @param category value of category property for current object
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 
	 * @return returns the value of assignmentStatus property for current object
	 */
	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	/**
	 * 
	 * sets the value for assignmentStatus property of current object
	 * @param assignmentStatus value of assignmentStatus property for current object
	 */
	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	/**
	 * 
	 * @return returns the value of assignedTo property for current object
	 */
	public Employee getAssignedTo() {
		return assignedTo;
	}

	/**
	 * 
	 * sets the value for assignedTo property of current object
	 * @param assignedTo value of assignedTo property for current object
	 */
	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * generated hash code for this class for collections
	 * @return returns hash code based on fields of the class
	 */
	@Override
	public int hashCode() {
		return Objects.hash(assignedTo, assignmentStatus, category, conditionNotes, name, purchaseDate);
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
		Asset other = (Asset) obj;
		return Objects.equals(assignedTo, other.assignedTo) && Objects.equals(assignmentStatus, other.assignmentStatus)
				&& Objects.equals(category, other.category) && Objects.equals(conditionNotes, other.conditionNotes)
				&& Objects.equals(name, other.name) && Objects.equals(purchaseDate, other.purchaseDate);
	}
	
	

}