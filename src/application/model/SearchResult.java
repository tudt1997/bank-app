package application.model;

public class SearchResult {
    private String loanID;
    private String accID;
    private String personname;
    private String amount;

    public SearchResult(String loanID, String accID, String personname, String amount) {
        this.loanID = loanID;
        this.accID = accID;
        this.personname = personname;
        this.amount = amount;
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
}
