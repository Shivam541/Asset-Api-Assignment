package in.rmgx.assetmanagementapi.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Entity class used to hold the data about a category
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
@Entity
@XmlRootElement
public class Category {

	/**
	 * Holds the unique id of a category instance.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * Holds the name of a category.
	 */
	@Column(name="name",nullable = false)
	private String name;
	/**
	 * A description of the current category.
	 */
	private String description;

	public Category() {
	}

	public Category(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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
	 * @return returns the value of description property for current object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * sets the value for description property of current object
	 * @param description value of description property for current object
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * generated hash code for this class for collections
	 * @return returns hash code based on fields of the class
	 */
	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
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
		Category other = (Category) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}
	
	
	
}
