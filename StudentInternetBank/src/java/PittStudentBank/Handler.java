/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PittStudentBank;

/**
 *
 * @author praveen
 */
public class Handler {
    int acctnum,tid;
    double balance,amt,limit;
    String transfertofrom,type;
    String dt;
    
    public Handler(int acctnum, double balance)
    {
        this.acctnum = acctnum;        
        this.balance = balance;
    }
    
    public Handler(int tid, double balance, double amt, String type, String transfertofrom, String dt)
    {
        this.tid = tid;
        this.balance = balance;
        this.amt = amt;
        this.type = type;
        this.transfertofrom = transfertofrom;
        this.dt = dt;
    }
    
    public Handler(int tid, double limit, double balance, double amt, String type, String transfertofrom, String dt)
    {
        this.tid = tid;
        this.limit = limit;
        this.balance = balance;
        this.amt = amt;
        this.type = type;
        this.transfertofrom = transfertofrom;
        this.dt = dt;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransfertofrom() {
        return transfertofrom;
    }

    public void setTransfertofrom(String transfertofrom) {
        this.transfertofrom = transfertofrom;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
    
    public int getAcctnum() {
        return acctnum;
    }

    public void setAcctnum(int acctnum) {
        this.acctnum = acctnum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }  

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }    
    
}
