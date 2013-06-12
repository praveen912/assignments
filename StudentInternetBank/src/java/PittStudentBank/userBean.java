/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PittStudentBank;

import java.util.ArrayList;

/**
 *
 * @author praveen
 */
public class userBean {
    private String name;
    private String LoginStatus;
    private int id;
    private int account;
    private String UserName;    
       
    public userBean(){
        name = null;
        UserName = null;
        LoginStatus = null;
        id =0;
        account = 0;   
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginStatus() {
        return LoginStatus;
    }

    public void setLoginStatus(String LoginStatus) {
        this.LoginStatus = LoginStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}
