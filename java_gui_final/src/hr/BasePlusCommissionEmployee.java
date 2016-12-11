package hr;

/**
 *
 * @author Sam Rizzo
 */
public class BasePlusCommissionEmployee extends CommissionSalesEmployee{
      
    private double baseSalary;

    public BasePlusCommissionEmployee() 
    {
    
    }

    public BasePlusCommissionEmployee (String firstName, String lastName, 
            String gender,String address, String phoneNumber, int sin, 
            int year, int month, int day) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day);
        
    } // end of basic BasePlusCommissionEmployee constructor
    
    public BasePlusCommissionEmployee (String firstName, String lastName, 
            String gender, String address, String phoneNumber,int sin, 
            int year, int month, int day, String position, String status, 
            String department, int idNumber, 
            double commissionRate, double baseSalary) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day, position, status, department, idNumber,
                commissionRate);
        
        this.baseSalary = baseSalary;
        
    } // end of advanced BasePlusCommissionEmployee constructor
    
    // Mutator Methods 

    public void setBaseSalary(double baseSalary) {
        
        this.baseSalary = baseSalary;
    } 
    
    // Accessor Methods 

    public double getBaseSalary(){
        
        return this.baseSalary;
    }
    
    /**
     * This method calculates the employee's earnings
     * based on the commissionRate, grossSales and the base salary
     * @return 
     */
    @Override
    public double calculateEarnings() {
        
        return super.calculateEarnings() + this.baseSalary;
    }
    
    /**
     * This method returns an advanced string representation
     * of the employee
     * @return
     */
    @Override
    public String advancedToString() {
        
        return super.advancedToString() + String.format("Base Salary: %.2f", 
                getBaseSalary());
    }
    
} // end of BasePlusCommissionEmployee class
