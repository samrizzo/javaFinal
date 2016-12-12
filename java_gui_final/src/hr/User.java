package hr;

/**
 *
 * @author Sam Rizzo
 */
public class User 
{
    private String username, password;
    private boolean isAdmin;
    
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        
    } // end of User constructor
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setAdmin(boolean selection)
    {
        isAdmin = selection;
    }
    
    //This methods sends true if the user is an admin
    public boolean userIsAdmin()
    {
        return isAdmin;
    }

    
} // end of User class
