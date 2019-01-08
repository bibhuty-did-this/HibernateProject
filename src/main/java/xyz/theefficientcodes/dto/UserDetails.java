/**
 * Step 2: Writing the model class
 */

/**
 * There are various ways to put annotation for fields.
 * 	1. Put all the annotations on the top of the field
 * 	2. Put all the annotations on the top of the getter
 */
package xyz.theefficientcodes.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
// @Entity (name="USER_DETAILS") // Changes the name of whole entity(HQL will clarify)
@Table (name="USER_DETAILS")
public class UserDetails {
	
	@Id
	@Column(name="id")
	private int userId;	
	@Column(name="name",nullable=false)
	private String userName;
	@Temporal(TemporalType.DATE) // Only stores the date instead of entire time stamp
	private Date joinedDate;
	private String address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
}
