package com.chk0ndanger.springframework.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Basement entity for other entity.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = new Date();
	}

	@Column(updatable = false)
	private Date createdAt;

	private Date updatedAt;

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * return true if {@link #id} are matched.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BaseEntity) {
			return getId() == ((BaseEntity)obj).getId();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		if (name != null) return name.hashCode();
		return (int) id;
	}

	/**
	 * build string for this object as human readable form.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
