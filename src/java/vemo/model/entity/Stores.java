package vemo.model.entity;
// Generated Sep 28, 2015 1:49:57 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Stores generated by hbm2java
 */
public class Stores  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String phone;
     private String email;
     private String address;
     private String details;
     private Set movieses = new HashSet(0);

    public Stores() {
    }

    public Stores(String name, String phone, String email, String address, String details, Set movieses) {
       this.name = name;
       this.phone = phone;
       this.email = email;
       this.address = address;
       this.details = details;
       this.movieses = movieses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDetails() {
        return this.details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    public Set getMovieses() {
        return this.movieses;
    }
    
    public void setMovieses(Set movieses) {
        this.movieses = movieses;
    }




}


