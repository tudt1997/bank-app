package application.model;

import java.util.Date;

public class SearchResult {
    private int id;
    private String loanID;
    private String accID;
    private String personname;
    private String amount;
    private String paymentMethod;
    private Date dtPayment;
    private float interest;

    public SearchResult(int id, String loanID, String accID, String personname, String amount, String paymentMethod, Date dtPayment, float interest) {
        this.id = id;
        this.loanID = loanID;
        this.accID = accID;
        this.personname = personname;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.dtPayment = dtPayment;
        this.interest = interest;
    }

    public SearchResult(String loanID, String accID, String personname, String amount) {
        this.loanID = loanID;
        this.accID = accID;
        this.personname = personname;
        this.amount = amount;
    }

    public SearchResult(String loanID, String accID, String personname, String amount, String paymentMethod) {
        this.loanID = loanID;
        this.accID = accID;
        this.personname = personname;
        this.amount = amount;
        paymentMethod = paymentMethod;
    }

    public void print() {
        System.out.println(id);
        System.out.println(loanID);
        System.out.println(accID);
        System.out.println(personname);
        System.out.println(amount);
        System.out.println(paymentMethod);
    }

    public SearchResult(int id, String loanID, String accID, String personname, String amount, String paymentMethod) {
        this.id = id;
        this.loanID = loanID;
        this.accID = accID;
        this.personname = personname;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getLoanID() {
        return loanID;
    }

    public String getAccID() {
        return accID;
    }

    public String getPersonname() {
        return personname;
    }

    public String getAmount() {
        return amount;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getDtPayment() {
        return dtPayment;
    }

    public void setDtPayment(Date dtPayment) {
        this.dtPayment = dtPayment;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
