package notUsed;

// import Employee packages
import hr.Employee;
import hr.HourlyEmployee;
import hr.BasePlusCommissionEmployee;
import hr.CommissionSalesEmployee;
import hr.SalaryEmployee;

//import product package
import product.Product;

//import java utils
import java.util.ArrayList;
import java.util.Scanner;
import manufacturer.Manufacturer;

/**
 *
 * @author Sam Rizzo
 */
public class main {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //ArrayList that will hold student objects
        ArrayList<Employee> employeeList = new ArrayList<>();
        
        //Scanner used for initial selection input
        Scanner input = new Scanner(System.in);
        
        boolean inputCheck = true;
        
        //automatically selected as the selection for application exit
        int defaultInput = 7;
        
        //Create fake product for user to search
        Manufacturer phoneManu = new Manufacturer();
        Product myProduct = new Product("This product is the new "
                + "Google Pixel Phone", 
        "Pixel", 4352, 649.99, phoneManu);
       
        
        //Start up main option menu
        ServiceClass.getInitialMessage();
        

        //Start input check for creating the specific object
        while(inputCheck) 
        {
            while(!input.hasNextInt())
            {
                ServiceClass.validateInput();
                input.next();
            }
            defaultInput = input.nextInt();

            //Validate input entries for creating a hourly Employee
            if (defaultInput == 1) 
            {
                //Local Scanner helps delete initial duplication
                Scanner keyboard = new Scanner(System.in);  
                
                //gather hourly employee information
                String firstName, lastName, gender, address, phoneNumber;
                int sin, year, month, day;
                boolean innerInputCheck = true;
                
                do
                {
                    ServiceClass.inputPrompt("first name");
                    if(ServiceClass.checkEmptyString(firstName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("last name");
                    if(ServiceClass.checkEmptyString(lastName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("gender");
                    if(ServiceClass.checkEmptyString(gender = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("address");
                    if(ServiceClass.checkEmptyString(address = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                       
                do
                {
                    ServiceClass.inputPrompt("phone number");
                    if(ServiceClass.checkEmptyString(phoneNumber = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                //Prompt for sin number if input is invalid keep prompting
                ServiceClass.inputPrompt("social insurance number");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("social insurance number");
                    keyboard.next();
                }
                sin = keyboard.nextInt();
                
                //Prompt for year if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth year");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth year");
                    keyboard.next();
                }
                year = keyboard.nextInt();
                
                //Prompt for month if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth month");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth month");
                    keyboard.next();
                }
                month = keyboard.nextInt();
                
                //Prompt for day if input is invalid keep prompting
                ServiceClass.inputPrompt("day the employee was born on");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("day the employee was born on");
                    keyboard.next();
                }
                day = keyboard.nextInt();
                
                    //create new hourly employee object
                    HourlyEmployee hourlyEmp = new HourlyEmployee(firstName, 
                    lastName, gender, address, phoneNumber, sin, year, month, 
                    day);
                    
                    //add hourly employee to arraylist
                    employeeList.add(hourlyEmp);
                    
                    //show confirmation message of employee addition
                    ServiceClass.employeeConfirmationMessage();

                    //Show message to find next Selection
                    ServiceClass.continueMessage();
            }
            
            //Validate input entries for creating a Salary Employee
            else if (defaultInput == 2)
            {
                //Local Scanner helps delete initial duplication
                Scanner keyboard = new Scanner(System.in);
                
                //gather salaried employee information
                String firstName, lastName, gender, address, phoneNumber;
                int sin, year, month, day;
                boolean innerInputCheck = true;
                
                do
                {
                    ServiceClass.inputPrompt("first name");
                    if(ServiceClass.checkEmptyString(firstName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("last name");
                    if(ServiceClass.checkEmptyString(lastName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("gender");
                    if(ServiceClass.checkEmptyString(gender = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("address");
                    if(ServiceClass.checkEmptyString(address = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                
                do
                {
                    ServiceClass.inputPrompt("phone number");
                    if(ServiceClass.checkEmptyString(phoneNumber = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                //Prompt for sin number if input is invalid keep prompting
                  ServiceClass.inputPrompt("social insurance number");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("social insurance number");
                    keyboard.next();
                }
                sin = keyboard.nextInt();
                
                //Prompt for year if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth year");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth year");
                    keyboard.next();
                }
                year = keyboard.nextInt();
                
                //Prompt for month if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth month");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth month");
                    keyboard.next();
                }
                month = keyboard.nextInt();
                
                //Prompt for day if input is invalid keep prompting
                ServiceClass.inputPrompt("day the employee was born on");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("day the employee was born on");
                    keyboard.next();
                }
                day = keyboard.nextInt();
                    
                    //create new salaried employee object
                    SalaryEmployee salaryEmp = new SalaryEmployee(firstName, 
                    lastName, gender, address, phoneNumber, sin, year, month, 
                    day);
                    
                    //add salaried employee to arraylist
                    employeeList.add(salaryEmp);
                    
                    //show confirmation message of employee addition
                    ServiceClass.employeeConfirmationMessage();
                    
                    //Show message to find next Selection
                    ServiceClass.continueMessage();
                
            }
            
            //Validate input entries for creating a Commission Employee
            else if (defaultInput == 3)
            {
                //Local Scanner helps delete initial duplication
                Scanner keyboard = new Scanner(System.in);
                
                //gather commission employee information
                String firstName, lastName, gender, address, phoneNumber;
                int sin, year, month, day;
                boolean innerInputCheck = true;
                
                do
                {
                    ServiceClass.inputPrompt("first name");
                    if(ServiceClass.checkEmptyString(firstName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("last name");
                    if(ServiceClass.checkEmptyString(lastName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("gender");
                    if(ServiceClass.checkEmptyString(gender = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("address");
                    if(ServiceClass.checkEmptyString(address = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                
                do
                {
                    ServiceClass.inputPrompt("phone number");
                    if(ServiceClass.checkEmptyString(phoneNumber = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                //Prompt for sin number if input is invalid keep prompting
                  ServiceClass.inputPrompt("social insurance number");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("social insurance number");
                    keyboard.next();
                }
                sin = keyboard.nextInt();
                
                //Prompt for year if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth year");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth year");
                    keyboard.next();
                }
                year = keyboard.nextInt();
                
                //Prompt for month if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth month");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth month");
                    keyboard.next();
                }
                month = keyboard.nextInt();
                
                //Prompt for day if input is invalid keep prompting
                ServiceClass.inputPrompt("day the employee was born on");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("day the employee was born on");
                    keyboard.next();
                }
                day = keyboard.nextInt();  
                    
                    //create new commission employee object
                    CommissionSalesEmployee comissionEmp = new 
                            CommissionSalesEmployee(firstName, lastName, gender,
                                    address, phoneNumber, sin, year, month, day);
                    
                    //add commission employee to arraylist
                    employeeList.add(comissionEmp);
                    
                    //show confirmation message of employee addition
                    ServiceClass.employeeConfirmationMessage();
                    
                    //Show message to find next Selection
                    ServiceClass.continueMessage();
            }
            
            //Validate input entries for creating a Base + Commission Employee
            else if (defaultInput == 4)
            { 
               //Local Scanner helps delete initial duplication
                Scanner keyboard = new Scanner(System.in); 
                
               //gather base + commission employee information
                String firstName, lastName, gender, address, phoneNumber;
                int sin, year, month, day;
                boolean innerInputCheck = true;
                
                do
                {
                    ServiceClass.inputPrompt("first name");
                    if(ServiceClass.checkEmptyString(firstName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("last name");
                    if(ServiceClass.checkEmptyString(lastName = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("gender");
                    if(ServiceClass.checkEmptyString(gender = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                do
                {
                    ServiceClass.inputPrompt("address");
                    if(ServiceClass.checkEmptyString(address = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                
                do
                {
                    ServiceClass.inputPrompt("phone number");
                    if(ServiceClass.checkEmptyString(phoneNumber = keyboard.nextLine()))
                    {
                        innerInputCheck = false;    
                    } 
                } while(innerInputCheck);
                
                //Prompt for sin number if input is invalid keep prompting
                  ServiceClass.inputPrompt("social insurance number");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("social insurance number");
                    keyboard.next();
                }
                sin = keyboard.nextInt();
                
                //Prompt for year if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth year");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth year");
                    keyboard.next();
                }
                year = keyboard.nextInt();
                
                //Prompt for month if input is invalid keep prompting
                ServiceClass.inputPrompt("employee's birth month");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("employee's birth month");
                    keyboard.next();
                }
                month = keyboard.nextInt();
                
                //Prompt for day if input is invalid keep prompting
                ServiceClass.inputPrompt("day the employee was born on");
                while (!keyboard.hasNextInt())
                {
                    ServiceClass.invalidIntInput();
                    ServiceClass.inputPrompt("day the employee was born on");
                    keyboard.next();
                }
                day = keyboard.nextInt();
                    
                    //create new base + commission employee object
                    BasePlusCommissionEmployee basePlusEmp = new 
                        BasePlusCommissionEmployee(firstName, lastName, gender, 
                                address, phoneNumber, sin, year, month, day);
                    
                    //add base + commission employee to arraylist
                    employeeList.add(basePlusEmp);
                    
                    //show confirmation message of employee addition
                    ServiceClass.employeeConfirmationMessage();
                    
                    //Show message to find next Selection
                    ServiceClass.continueMessage();
            }
            
            //Search for an Employee
            else if (defaultInput == 5)
            {
                //Local Scanner
                Scanner keyboard = new Scanner(System.in);

                //Store employee search name
                String searchString;
                
                boolean innerInputCheck = false;
                
                do
                {
                    ServiceClass.inputPrompt("Employee's first name to search");
                    searchString = keyboard.nextLine();
                    
                    if(ServiceClass.checkEmptyString(searchString)== false)
                    {
                        innerInputCheck = true;    
                    } 

                } while(innerInputCheck);
                
                //Store employee position in int
                int employeePosition = 0;
                
                //Boolean to see if employee is found
                boolean isFound = false;
                
                for(int i = 0; i <employeeList.size(); i++) {
                    //Check if product name entered matches one in system
                    if(searchString.equalsIgnoreCase(employeeList.get(i)
                            .getFirstName()))
                    {
                        employeePosition = i;
                        isFound = true;
                    }
                }
                if(isFound == true){
                    //Display product if found
                    System.out.println("\n" + employeeList.get(employeePosition)
                            .toString());
                }

                else 
                {
                    //Display message stating search not found
                    ServiceClass.searchError();
                }
                
                //Show message to find next Selection
                    ServiceClass.continueMessage();
            }
            
            
            //Search for a Product
            else if (defaultInput == 6)
            {            
                //Local Scanner helps delete initial duplication
                Scanner keyboard = new Scanner(System.in);
                
                //Display product search message
                ServiceClass.productSearchMessage();
                
                //Store Product name
                String productName;
                
                boolean innerInputCheck = false;
                
                do
                {
                    ServiceClass.inputPrompt("product name to search");
                    productName = keyboard.nextLine();
                    
                    if(ServiceClass.checkEmptyString(productName)== false)
                    {
                        innerInputCheck = true;    
                    } 

                } while(innerInputCheck);
                
                //Check if product name entered matches one in system
                if(productName.equalsIgnoreCase(myProduct.getProductName()))
                {
                    //Display product if found
                    System.out.println("\n" + myProduct.toString());
                }
                
                else 
                {
                    //Display message stating search not found
                    ServiceClass.searchError();
                }
                
                //Show message to find next Selection
                    ServiceClass.continueMessage();
            }
            
            //End the Application if selection is 7
            else if (defaultInput == 7)
            {
                //set loop check to false and exit the loop
                ServiceClass.applicationExit();
                inputCheck = false;
            }
        }
    }
    
} // end of main 
