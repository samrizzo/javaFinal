package hr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Sam Rizzo
 */
public abstract class Employee {
    
    // Create a today instance to get current year 
    private final Calendar today = Calendar.getInstance();
    
    private Date date;
    private String birthDate = "";

    private String firstName, lastName, status, gender, address, 
            phoneNumber, position, department;   
    
    private GregorianCalendar dateOfBirth;
    private Date dateOfHire;
    private int idNumber, sin;
    
    private static int employeeCount;

    public Employee() 
    {
        
    }
    
    public Employee(String firstName, String lastName, String gender,
            String address, String phoneNumber, int sin, int year, 
            int month, int day) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;      
        this.sin = sin;
        this.dateOfHire = new GregorianCalendar().getTime();  
        
        //Set the date of birth
        this.dateOfBirth = new GregorianCalendar();
        this.dateOfBirth.set(Calendar.YEAR, year);
        this.dateOfBirth.set(Calendar.MONTH, month-1);
        this.dateOfBirth.set(Calendar.DATE, day);
        this.date = dateOfBirth.getTime();
        
        employeeCount++;
        
    } // end of basic Employee Constructor
    
    public Employee(String firstName, String lastName, String gender,
            String address, String phoneNumber,int sin, int year, int month, 
            int day, String position, String status, String department, 
            int idNumber){
        
        this(firstName, lastName, gender, address, phoneNumber, sin, 
                year, month, day);
        
        this.position = position;
        this.status = status;
        this.department = department;
        this.idNumber = idNumber;
 
    } // end of advanced Employee Constructor
    
    // Mutator Methods

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
 
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setPosition(String position){
        this.position = position;
    }
    
    public void setEmployeeStatus(String status) {
        
        this.status = status;
    }

    public void setDepartment(String department){
        this.department = department;
    }
    
    public void setDateOfBirth(String birth)
    {
        this.birthDate = birth;
    }
    
    public void setDateOfBirth(int year, int month, int day){
        
        this.dateOfBirth = new GregorianCalendar();
        this.dateOfBirth.set(Calendar.YEAR, year);
        this.dateOfBirth.set(Calendar.MONTH,month-1);
        this.dateOfBirth.set(Calendar.DATE,day);

    }

    public void setDateOfHire(int year, int month, int day){
        
        GregorianCalendar tempDate = new GregorianCalendar();
        tempDate.set(Calendar.YEAR, year);
        tempDate.set(Calendar.MONTH,month-1);
        tempDate.set(Calendar.DATE,day);
        
        this.dateOfHire = tempDate.getTime();

    }
    
    public void setSIN(int sin){
        this.sin = sin;
    }

    public void setIDNumber(int idNumber){
        this.idNumber = idNumber;
    }

    // Acessor Methods
    
    /**
     * This method will calculate the earnings of the employee
     * based on which type of employee they are
     * @return 
     */
    public abstract double calculateEarnings();
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getFullName() {
        return this.firstName + ' ' + this.lastName;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getPosition() {
        return this.position;
    }

    public String getDepartment() {
        return this.department;
    }
    
    public String getEmployeeStatus() {
        return this.status;
    }
    
    public String getDateOfBirth(){
        
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        birthDate = sdf.format(date);
        date = sdf.parse(birthDate);
        }
        catch(Exception e)
        {
            
        }
        
        return birthDate;
    }
    
    /**
     * This method will return the employee's age in years
     * @return 
     */
    public int getAge() {       
        int age = today.get(Calendar.YEAR) - this.dateOfBirth.get(Calendar.YEAR);
        return age;
    }
    
    public Date getDateOfHire() {
        return this.dateOfHire;
    }

    public int getSIN() {
        return this.sin;
    }

    public int getIDNumber() {
        return this.idNumber;
    }

    /**
     * This method returns the name, gender, address, 
     * phone number, and sin number of the employee
     * @return 
     */
    @Override
    public String toString() {
        
        return String.format("Name: %s\nAge: %d\nGender: %s\nAddress: %s\n"
                + "PhoneNumber: %s\nSocial Insurance Number: %d\n", 
                getFullName(), getAge(), getGender(), getAddress(), 
                getPhoneNumber(), getSIN());
    }
    
    /**
     * This method returns the name, gender, address, phone number, 
     * sin number, position name, department name, id number,
     * earnings amount, and the pay rate of the employee
     * @return 
     */
    public String advancedToString() {
        return toString() + String.format("Position: %s\nDepartment: %s\n"
                + "ID Number: %d\n", getPosition(), getDepartment(), 
                getIDNumber());
    }
    
} // end of Employee class
