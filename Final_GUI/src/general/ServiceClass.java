package general;

/**
 *
 * @author Sam Rizzo
 */
public class ServiceClass {
    
    private static int employeeId = 10000;
    
    public static int getEmployeeId()
    {
        return employeeId++;
    }
    
    /**
     * This method prints the initial menu message to the screen
     */
    public static void getInitialMessage()
    {
        System.out.println("Welcome to the Company Administration Menu\n-------"
              + "-----------------------------------\nPlease enter an option:\n"
                + "\n1. Create new Hourly Employee"
                + "\n2. Create new Salary Employee"
                + "\n3. Create new Commission Employee"
                + "\n4. Create new Base + Commission Employee"
                + "\n5. Search for an Employee"
                + "\n6. Search for a Product"
                + "\n7. Exit\n"); 
    }
    
    /**
     * This method prints the demo information for 
     * the product search choice
     */
    public static void productSearchMessage()
    {
        System.out.println("\nA test product has been created for you.\nTo search"
                + " this product enter in the word 'Pixel' when prompted\n");
    }
    
    /**
     * This method prints a message when exiting the application
     */
    public static void applicationExit() 
    {
        System.out.println("\nExiting the application..."); 
    }
    
    /**
     * This method prints a input validation error, followed by
     * the menu options if an invalid input is entered.
     */
    public static void validateInput() 
    {
        System.out.println("\nInvalid Input!\nPlease enter a valid selection\n");
        System.out.println("Please enter an option:\n"
                + "\n1. Create new Hourly Employee"
                + "\n2. Create new Salary Employee"
                + "\n3. Create new Commission Employee"
                + "\n4. Create new Base + Commission Employee"
                + "\n5. Search for an Employee"
                + "\n6. Search for a Product"
                + "\n7. Exit\n\n");
        
    }
    
    /**
     * This method prints an input error message when
     * the user enters something other than an integer
     */
    public static void invalidIntInput() 
    {
        System.out.println("\nInvalid Input!\nPlease enter a number value");
    }
    
    /**
     * This method returns a boolean true or false
     * when checking if the user's string is empty
     * @param testString
     * @return 
     */
    public static boolean checkEmptyString(String testString) 
    {
        return !testString.isEmpty();
    }
    
    /**
     * This method prints an input prompt specifying which
     * piece of information is needed
     * @param text 
     */
    public static void inputPrompt(String text) 
    {   
        System.out.printf("\nPlease enter the %s: ", text);
    }    

    /**
     * This method prints a message stating that no
     * results were found when the search string is not
     * in the system
     */
    public static void searchError()
    {
        System.out.println("No results found");
    }
   
    /**
     * This method prints a message stating that
     * the employee has successfully been
     * added to the system
     */
    public static void employeeConfirmationMessage()
    {
        System.out.println("\nEmployee has successfully been added");
    }
    
    public static void continueMessage() {
        
        System.out.println("\nPlease enter an option:\n"
                + "\n1. Create new Hourly Employee"
                + "\n2. Create new Salary Employee"
                + "\n3. Create new Commission Employee"
                + "\n4. Create new Base + Commission Employee"
                + "\n5. Search for an Employee"
                + "\n6. Search for a Product"
                + "\n7. Exit\n"); 
    }

}
