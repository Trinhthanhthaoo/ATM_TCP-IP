/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vku.Model;

/**
 *
 * @author admin!
 */
public class History {
    private String id;
     private String username;
    private String Details;
    private String updateDate;
   public History(){
       
   }

    public History(String id, String username, String Details, String updateDate) {
        this.id = id;
        this.username = username;
        this.Details = Details;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public String getupdateDate() {
        return updateDate;
    }

    public void setupdateDate(String Update_Date) {
        this.updateDate = Update_Date;
    }

    @Override
    public String toString() {
        return "History{" + "id=" + id + ", username=" + username + ", Details=" + Details + ", Update_Date=" + updateDate + '}';
    }
   
}
