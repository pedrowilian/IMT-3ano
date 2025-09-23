public class AccountRecord {
    // A class that represents one record of information.
    private int account;
    private String firstName;
    private String lastName;
    private double balance;

    // no-argument constructor calls other constructor with default values
    public AccountRecord() {
        this(0, "", "", 0.0); // call four-argument constructor
    } // end no-argument AccountRecord constructor

    // initialize a record
    public AccountRecord(int acct, String first, String last, double bal) {
        setAccount(acct);
        setFirstName(first);
        setLastName(last);
        setBalance(bal);
    } // end four-argument AccountRecord constructor

    public void setAccount(int acct) // set account number
    {
        account = acct;
    } // end method setAccount

    public int getAccount() // get account number
    {
        return account;
    } // end method getAccount

    public void setFirstName(String first) // set first name
    {
        firstName = first;
    } // end method setFirstName

    public String getFirstName() // get first name
    {
        return firstName;
    } // end method getFirstName

    public void setLastName(String last) // set last name
    {
        lastName = last;
    } // end method setLastName

    public String getLastName() // get last name
    {
        return lastName;
    } // end method getLastName

    public void setBalance(double bal) // set balance
    {
        balance = bal;
    } // end method setBalance

    public double getBalance() // get balance
    {
        return balance;
    } // end method getBalance
} // end class AccountRecord