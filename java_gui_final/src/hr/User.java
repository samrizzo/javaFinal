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
    
    public String printUserStatus()
    {
        String response = "";
        
        if(isAdmin == true)
        {
            response = "Admin";
        }
        if(isAdmin == false)
        {
            response = "Regular";
        }
        
        return response;
    }
    
} // end of User class
