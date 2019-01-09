/**
 * Step 2: Writing the model class
 */

/**
 * There are various ways to put annotation for fields.
 * 	1. Put all the annotations on the top of the field
 * 	2. Put all the annotations on the top of the getter
 */
package xyz.theefficientcodes.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

// Imports non-compliant with JPA
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
// @Entity (name="USER_DETAILS") // Changes the name of whole entity(HQL will clarify)
@Table (name="USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE) // Generate primary key, parameters are optional
	@Column(name="id")
	private int userId;	
	
	@Column(name="name",nullable=false)
	private String userName;
	
	@Temporal(TemporalType.DATE) // Only stores the date instead of entire time stamp
	private Date joinedDate;
	
	@Embedded // Not mandatory if the class is annotated(but good practice)
	@AttributeOverrides({
		@AttributeOverride(name="street",column=@Column(name="HOME_STREET_NAME")),
		@AttributeOverride(name="city",column=@Column(name="HOME_CITY_NAME")),
		@AttributeOverride(name="state",column=@Column(name="HOME_STATE_NAME")),
		@AttributeOverride(name="pincode",column=@Column(name="HOME_PIN_CODE"))
	})
	
	private Address homeAddress;
	
	@Embedded // Not mandatory if the class is annotated(but good practice)
	private Address officeAddress;

	// Used list instead of set because it supports indexing
	@ElementCollection
	@JoinTable(
			name="gfAddresses",
			joinColumns=@JoinColumn(name="USER_ID")
	)	
	@GenericGenerator(name = "inc-gen", strategy = "increment")
	@CollectionId(columns = { @Column(name="address_id") }, generator = "inc-gen", type = @Type(type="long"))
	private Collection<Address> gfAddresses=new ArrayList<Address>();
	
	@Lob
	private String description; // Store very large object(more than 255 characters)
	
	@Transient
	private boolean isIdiot; // this field will not be added to the database

	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public boolean isIdiot() {
		return isIdiot;
	}
	public void setIdiot(boolean isIdiot) {
		this.isIdiot = isIdiot;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	public Collection<Address> getGfAddresses() {
		return gfAddresses;
	}
	public void setGfAddresses(Collection<Address> gfAddresses) {
		this.gfAddresses = gfAddresses;
	}
	

}
