package vemo.model.entity;
// Generated Sep 28, 2015 1:49:57 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Accounts generated by hbm2java
 */
public class Accounts  implements java.io.Serializable {


     private Integer id;
     private Customers customers;
     private Payments payments;
     private String name;
     private String details;
     private Set customersRentalses = new HashSet(0);

    public Accounts() {
    }

    public Accounts(Customers customers, Payments payments, String name, String details, Set customersRentalses) {
       this.customers = customers;
       this.payments = payments;
       this.name = name;
       this.details = details;
       this.customersRentalses = customersRentalses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Customers getCustomers() {
        return this.customers;
    }
    
    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
    public Payments getPayments() {
        return this.payments;
    }
    
    public void setPayments(Payments payments) {
        this.payments = payments;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDetails() {
        return this.details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    public Set getCustomersRentalses() {
        return this.customersRentalses;
    }
    
    public void setCustomersRentalses(Set customersRentalses) {
        this.customersRentalses = customersRentalses;
    }




}


