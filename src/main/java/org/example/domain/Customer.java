package org.example.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Customer {

  @Id
  Long id;

  String name;

  String notes;
  
  @OneToMany(cascade = CascadeType.ALL)
  List<Bill> bills;

  @Version
  Long version;

  public Customer(String name) {
    this.name = name;
  }

  public Customer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public List<Bill> getBills() {
	return bills;
  }

  public void setBills(List<Bill> bills) {
	this.bills = bills;
  }

}
