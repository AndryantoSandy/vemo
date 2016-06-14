package vemo.model.entity;
// Generated Sep 28, 2015 1:49:57 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * TransactionTypes generated by hbm2java
 */
public class TransactionTypes  implements java.io.Serializable {


     private Integer id;
     private String description;
     private Set customersRentalses = new HashSet(0);

    public TransactionTypes() {
    }

    public TransactionTypes(String description, Set customersRentalses) {
       this.description = description;
       this.customersRentalses = customersRentalses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set getCustomersRentalses() {
        return this.customersRentalses;
    }
    
    public void setCustomersRentalses(Set customersRentalses) {
        this.customersRentalses = customersRentalses;
    }




}

