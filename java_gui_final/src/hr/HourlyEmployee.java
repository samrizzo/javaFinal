package hr;

/**
 *
 * @author Sam Rizzo
 */
public class HourlyEmployee extends Employee {
    
    private double hourlyRate;
    private double hoursWorked;
    
    public HourlyEmployee()
    {
        // empty HourlyEmployee constructor
    }
    
    public HourlyEmployee(String firstName, String lastName, String gender,
            String address, String phoneNumber, int sin, int year, 
            int month, int day) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day);
        
    } // end of basic HourlyEmployee constructor
    
    public HourlyEmployee(String firstName, String lastName, 
            String gender, String address, String phoneNumber,int sin, 
            int year, int month, int day, String position,String status, 
            String department, int idNumber, double hourlyRate) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day, position, status, department, idNumber);
        
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        
    } // end of advanced HourlyEmployee constructor
    
    // Mutator Methods
    
    public void setHourlyRate(double hourlyRate) {
        
        this.hourlyRate = hourlyRate;
    }
    
    public void setHoursWorked(double hoursWorked) {
        
        this.hoursWorked = hoursWorked;
    }
    
    
    // Acessor Methods
    
    public double getHourlyRate() {
        
        return this.hourlyRate;
    }
    
    public double getHoursWorked() {
        
        return this.hoursWorked;
    }
    
    /**
     * This method calculates the employee's earnings by
     * multiplying the hourlyRate by the hoursWorked
     * @return 
     */
    @Override
    public double calculateEarnings() {
        
        return this.hoursWorked *  this.hourlyRate;
    }
    
    @Override
    public String advancedToString() {
        
        return super.advancedToString() + String.format("Hourly Rate: %.2f\n"
                + "Hours Worked: %.2f", getHourlyRate(), getHoursWorked());
    }
    
} // end of HourlyEmployee class
