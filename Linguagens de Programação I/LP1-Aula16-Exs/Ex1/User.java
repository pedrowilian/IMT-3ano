public class User {
    // A class that represents one record of information.
    private String Name;
    private String Password;
    // no-argument constructor calls other constructor with default values
    public User() {
        this("", ""); // call two-argument constructor
    } // end no-argument User constructor

    // two-argument constructor to initialize a record
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public User(String name, String password) {
        setName(name);
        setPassword(password);
    } // end two-argument User constructor

    public void setName(String name) // set name
    {
        Name = name;
    } // end method setName

    public String getName() // get name
    {
        return Name;
    } // end method getName

    public String getPassword() // get password
    {
        return Password;
    } // end method getPassword
    public void setPassword(String password) // set password
    {
        Password = password;
    } // end method setPassword
} // end class User