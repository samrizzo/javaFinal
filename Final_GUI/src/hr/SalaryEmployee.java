package hr;

/**
 *
 * @author Sam Rizzo
 */
public class SalaryEmployee extends Employee {
    
    private double salary;
    
    public SalaryEmployee() 
    {
        
    }
    
    public SalaryEmployee(String firstName, String lastName, 
            String gender,String address, String phoneNumber, int sin, 
            int year, int month, int day) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day);
        
    } // end of basic SalaryEmployee constructor
    
    public SalaryEmployee(String firstName, String lastName, 
            String gender, String address, String phoneNumber,int sin, 
            int year, int month, int day, String position,String status, 
            String department, int idNumber, double salary) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day, position, status, department, idNumber);
        
        this.salary = salary;
        
    } // end of advanced SalaryEmployee constructor
    
    // Mutator Methods
    
    public void setSalary(double salary) {
        
        this.salary = salary;
    }
    
    // Acessor Methods
    
    public double getSalary() {
        
        return this.salary;
    }
    
    /**
     * This method calculates the earnings by returning the salary
     * @return 
     */
    @Override
    public double calculateEarnings() {
        return this.salary;
    }
    
    @Override
    public String advancedToString() {
        
        return super.advancedToString() + String.format("Salary: %.2f\n", 
                getSalary());
    }
    
} // end of SalaryEmployee class
