package vemo.model.entity;
// Generated Sep 28, 2015 1:49:57 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import vemo.util.Helper;

/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {

     private Helper help = new Helper();
     private Integer id;
     private Date dateRegistered;
     private String realName;
     private String gender;
     private String position;
     private String phone;
     private String email;
     private String address;
     private String username;
     private String password;
     private Integer actived;
     private Set customersRentalses = new HashSet(0);

    public Users() {
    }

    public Users(Date dateRegistered, String realName, String gender, String position, String phone, String email, String address, String username, String password, Integer actived, Set customersRentalses) {
       this.dateRegistered = dateRegistered;
       this.realName = realName;
       this.gender = gender;
       this.position = position;
       this.phone = phone;
       this.email = email;
       this.address = address;
       this.username = username;
       this.password = password;
       this.actived = actived;
       this.customersRentalses = customersRentalses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getDateRegistered() {
        return this.dateRegistered;
    }
    
    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered =  new Date();
    }
    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
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
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getActived() {
        return this.actived;
    }
    
    public void setActived(Integer actived) {
        this.actived = actived;
    }
    public Set getCustomersRentalses() {
        return this.customersRentalses;
    }
    
    public void setCustomersRentalses(Set customersRentalses) {
        this.customersRentalses = customersRentalses;
    }




}


