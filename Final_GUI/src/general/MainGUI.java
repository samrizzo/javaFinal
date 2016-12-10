package general;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.replace;
import static com.sun.xml.internal.bind.WhiteSpaceProcessor.replace;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.border.TitledBorder;
import static jdk.nashorn.internal.objects.NativeString.replace;


/**
 *
 * @author Sam Rizzo
 */
public class MainGUI extends JFrame
{
    //JPanels
    JPanel welcomePanel, employeePanel = new JPanel(),mainEmployeePanel, 
            employeePanelTop, innerEmployeePanel, employeeDOBPanel, 
            employeePanelBottom, employeePayPanel, 
            inventoryPanel = new JPanel(), inventoryPanelTop, inventoryPanelBottom,
            searchPanel = new JPanel(), searchPanelTop, searchPanelBottom,
            employeeButtonPanel, inventoryButtonPanel, searchButtonPanel;
    
    //JLabels for HR Tab
    private JLabel firstNameLabel, lastNameLabel,searchFirstNameLabel,searchLastNameLabel,
            searchProductNameLabel, searchProductNumberLabel,
            genderLabel, addressLabel, phoneNumberLabel, sinLabel, yearLabel, 
            monthLabel, dayLabel,positionLabel, statusLabel, departmentLabel, 
            idNumberLabel, hourlyRateLabel, commissionRateLabel, baseSalaryLabel,
            welcomeLabel;
    
    //JTextFields for HR & Search Tabs
    private JTextField firstNameText, lastNameText,searchFirstNameText,searchLastNameText,
            searchProductNameText, searchProductNumberText,
            genderText, addressText, phoneNumberText, sinText, yearText, monthText, 
            dayText, positionText, statusText, departmentText, idNumberText, 
            hourlyRateText, commissionRateText, baseSalaryText;
    
    //JLabels for Inventory Tab
    private JLabel productNameLabel, productDescriptionLabel, productCostLabel, 
            productNumberLabel, manufacturerNameLabel, manufacturerAddressLabel, 
            manufacturerPhoneLabel, manufacturerIDLabel;
    
    //JTextFields for Inventory Tab
    private JTextField productNameText, productDescriptionText, productCostText, 
            productNumberText, manufacturerNameText, manufacturerAddressText, 
            manufacturerPhoneText, manufacturerIDText;
    
    //ButtonGroup + JRadio Buttons
    private final ButtonGroup searchBox;
    private JButton exitButton, submitButton, createButton, searchButton;
    
    //JRadioButtons to track search selection
    JRadioButton employeeButton, productButton;
    
    //JComboBox to hold Employee Types
    private JComboBox<String> employeeType;
    private static final String[] typeOfEmployee = 
            {"Hourly","Salary","Commission", "Base + Commission"};
    
    //boolean
    boolean keepGoing = true;
    
    public MainGUI()
    {
        //Create App title and layout
        super("Helix Administration");
        setLayout(new BorderLayout());
        
        //Create Tabbed Interface
        JTabbedPane tabPane = new JTabbedPane();
        
        //Configure the tabs
        tabPane.addTab("HR", null, employeePanel, "Human Resource");
        tabPane.addTab("Inventory", null, inventoryPanel, "Inventory");
        tabPane.addTab("Search", null, searchPanel, "Search");
        
        //Build Panels
        buildMainPanel();
        
        //Tab Panels
        buildEmployeePanel();
        buildInventoryPanel();
        buildSearchPanel();
        
        //Button Panels
        buildEmployeeButtonPanel();
        buildInventoryButtonPanel();
        buildSearchButtonPanel();
        
        //Add Employee Panel to JFrame
        employeePanel.setLayout(new BorderLayout());
        employeePanel.add(mainEmployeePanel, BorderLayout.NORTH);
        employeePanel.add(employeePanelBottom, BorderLayout.CENTER);
        employeePanel.add(employeeButtonPanel, BorderLayout.SOUTH);
        
        //Add welcomePanel and tabbedPane to JFrame
        add(welcomePanel, BorderLayout.NORTH);
        add(tabPane, BorderLayout.CENTER);
        
        //Add Inventory Panel to JFrame
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.add(inventoryPanelTop, BorderLayout.NORTH);
        inventoryPanel.add(inventoryPanelBottom, BorderLayout.CENTER);
        inventoryPanel.add(inventoryButtonPanel, BorderLayout.SOUTH);
        
        //Add Search Panel to JFrame
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchPanelTop, BorderLayout.NORTH);
        searchPanel.add(searchPanelBottom, BorderLayout.CENTER);
        searchPanel.add(searchButtonPanel, BorderLayout.SOUTH);
        
        //ButtonGroup + radio buttons
        searchBox = new ButtonGroup();
        searchBox.add(employeeButton);
        searchBox.add(productButton);
        
        // JComboBox for type of employees
        employeeType = new JComboBox(typeOfEmployee);
        employeeType.setMaximumRowCount(3);

    }
    
    //Build Employee Panel
    private void buildMainPanel() 
    {
        //Create Panel and Greeting
        welcomePanel = new JPanel();
        welcomeLabel = new JLabel("Welcome to Helix");
        
        //Set font of the label
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        //Add label to panel and set border
        welcomePanel.add(welcomeLabel);
        welcomePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        
    }
    
    private void buildEmployeePanel()
    {
        //Create employeePanelTop & set layout
        employeePanelTop = new JPanel();
        employeePanelTop.setLayout(new GridLayout(6, 1));
        
        //Create innerEmployeePanel & set layout
        innerEmployeePanel = new JPanel();
        innerEmployeePanel.setLayout(new GridLayout(4, 1));
        
        //Create employeeDOBPanel & set layout
        employeeDOBPanel = new JPanel();
        employeeDOBPanel.setLayout(new FlowLayout());
        
        //Create the mainEmployeePanel
        mainEmployeePanel = new JPanel();
        mainEmployeePanel.setLayout(new BorderLayout());
        
        //Add the employeePanelTop and innerEmployeePanel
        mainEmployeePanel.add(employeePanelTop, BorderLayout.NORTH);
        mainEmployeePanel.add(innerEmployeePanel, BorderLayout.SOUTH);
        
        //Create the labels 
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        
        genderLabel = new JLabel("Gender");
        
        addressLabel = new JLabel("Address");
        phoneNumberLabel = new JLabel("Phone Number");
        
        sinLabel = new JLabel("SIN");
        yearLabel = new JLabel("Year");
        monthLabel = new JLabel("Month");
        dayLabel = new JLabel("Day");  
        
        positionLabel = new JLabel("Position");
        statusLabel = new JLabel("Status");
        departmentLabel = new JLabel("Department");
        idNumberLabel = new JLabel("ID");    

        //Create the textboxes
        firstNameText = new JTextField(15);
        lastNameText = new JTextField(15);
        
        genderText = new JTextField(10);
        
        addressText = new JTextField(30);
        phoneNumberText = new JTextField(10);
        
        sinText = new JTextField(9);
        yearText = new JTextField(4);
        monthText = new JTextField(2);
        dayText = new JTextField(2);
        
        positionText = new JTextField(40);
        statusText = new JTextField(30);
        departmentText = new JTextField(40);
        idNumberText = new JTextField(8);
        
        //Set Titled Borders for each Panel
        employeePanelTop.setBorder(BorderFactory.createTitledBorder
        ("Employee Information"));
        
        employeeDOBPanel.setBorder(BorderFactory.createTitledBorder
        ("Please enter Employee's Date of birth"));
        
        innerEmployeePanel.setBorder(BorderFactory.createTitledBorder
        ("Position Information"));
        
        //Add labels and textboxes to the employeePanelTop
        employeePanelTop.add(firstNameLabel);
        employeePanelTop.add(firstNameText);
        
        employeePanelTop.add(lastNameLabel);
        employeePanelTop.add(lastNameText);
        
        employeePanelTop.add(genderLabel);
        employeePanelTop.add(genderText);
        
        employeePanelTop.add(addressLabel);
        employeePanelTop.add(addressText);
        
        employeePanelTop.add(phoneNumberLabel);
        employeePanelTop.add(phoneNumberText);

        employeePanelTop.add(sinLabel);
        employeePanelTop.add(sinText);
        
        //Add labels and textboxes to employeeDOBPanel
        employeeDOBPanel.add(yearLabel);
        employeeDOBPanel.add(yearText);
        
        employeeDOBPanel.add(monthLabel);
        employeeDOBPanel.add(monthText);
        
        employeeDOBPanel.add(dayLabel);
        employeeDOBPanel.add(dayText); 
        
        //Add labels and textboxes to innerEmployeePanel
        innerEmployeePanel.add(positionLabel);
        innerEmployeePanel.add(positionText);
        
        innerEmployeePanel.add(statusLabel);
        innerEmployeePanel.add(statusText);
        
        innerEmployeePanel.add(departmentLabel);
        innerEmployeePanel.add(departmentText);
        
        innerEmployeePanel.add(idNumberLabel);
        innerEmployeePanel.add(idNumberText);
        
        //Create employeePayPanel and set layout
        employeePayPanel = new JPanel();
        employeePayPanel.setLayout(new GridLayout(4, 1));
        
        //Create mainPanelBottom and set layout
        employeePanelBottom = new JPanel();
        employeePanelBottom.setLayout(new BorderLayout());
        
        //Add the employeePayPanel and employeeDOBPanel
        employeePanelBottom.add(employeePayPanel, BorderLayout.NORTH);
        employeePanelBottom.add(employeeDOBPanel, BorderLayout.SOUTH);
        
        employeePayPanel.setBorder(BorderFactory.createTitledBorder
        ("Pay Information"));
         
        //Add JComboBox to the mainPanelBottom;      
        employeePayPanel.add(new JLabel("Type of Employee"));
        final JComboBox employeeType = new JComboBox(typeOfEmployee);
        employeePayPanel.add(employeeType);
        
        //Create the labels
        hourlyRateLabel = new JLabel("Hourly Rate");
        baseSalaryLabel = new JLabel("Salary");
        commissionRateLabel = new JLabel("Commission Rate");
        
        
        //Create the textboxes
        hourlyRateText = new JTextField();
        baseSalaryText = new JTextField();
        commissionRateText = new JTextField();
        
       
        // checks the selected index for employee type
        employeeType.addActionListener (new ActionListener () 
        {
            public void actionPerformed(ActionEvent e) 
            {
                //if hourly 
            if (employeeType.getSelectedIndex() == 0)
            {
                employeeType.setSelectedIndex(0);
                employeePayPanel.remove(commissionRateLabel);
                employeePayPanel.remove(commissionRateText);
                employeePayPanel.remove(baseSalaryLabel);
                employeePayPanel.remove(baseSalaryText);
                employeePanelBottom.revalidate();
                employeePayPanel.add(hourlyRateLabel);
                employeePayPanel.add(hourlyRateText);
            }
                //if salary
            if (employeeType.getSelectedIndex() == 1)
            {
                employeeType.setSelectedIndex(1);
                employeePayPanel.remove(hourlyRateLabel);
                employeePayPanel.remove(hourlyRateText);
                employeePayPanel.remove(commissionRateLabel);
                employeePayPanel.remove(commissionRateText);
                employeePayPanel.revalidate();
                employeePayPanel.add(baseSalaryLabel);
                employeePayPanel.add(baseSalaryText);
            }
                //if commission
            if (employeeType.getSelectedIndex() == 2)
            {
                employeeType.setSelectedIndex(2);
                employeePayPanel.remove(hourlyRateLabel);
                employeePayPanel.remove(hourlyRateText);
                employeePayPanel.remove(baseSalaryLabel);
                employeePayPanel.remove(baseSalaryText);
                employeePayPanel.revalidate();
                employeePayPanel.add(commissionRateLabel);
                employeePayPanel.add(commissionRateText); 
            }
                //if commission plus salary
            if (employeeType.getSelectedIndex() == 3)
            {
                employeeType.setSelectedIndex(3);
                employeePayPanel.remove(hourlyRateLabel);
                employeePayPanel.remove(hourlyRateText);
                employeePayPanel.revalidate();
                employeePayPanel.add(baseSalaryLabel);
                employeePayPanel.add(baseSalaryText);
                employeePayPanel.add(commissionRateLabel);
                employeePayPanel.add(commissionRateText); 
            }
            
            }//end of action performed 
        });//end of action listen
    }//end of build employee class
    
    private void buildInventoryPanel()
    {
        //Create inventoryPanelTop & set layout
        inventoryPanelTop = new JPanel();
        inventoryPanelTop.setLayout(new GridLayout(4,1));
        
        //Create the labels for the product
        productNameLabel = new JLabel("Product Name");
        productDescriptionLabel = new JLabel("Description");
        productCostLabel = new JLabel("Cost");
        productNumberLabel = new JLabel("Product Number");
        
        //Create the textboxes for the product
        productNameText = new JTextField(15);
        productDescriptionText = new JTextField(30);
        productCostText = new JTextField(10);
        productNumberText = new JTextField(10);   
        
        //Set the titled border for the inventoryPanelTop
        inventoryPanelTop.setBorder(BorderFactory.createTitledBorder
        ("Product Information"));   
        
        //Add the labels and textboxes to the inventoryPanelTop
        inventoryPanelTop.add(productNameLabel);
        inventoryPanelTop.add(productNameText);
        
        inventoryPanelTop.add(productDescriptionLabel);
        inventoryPanelTop.add(productDescriptionText);
        
        inventoryPanelTop.add(productCostLabel);
        inventoryPanelTop.add(productCostText);
        
        inventoryPanelTop.add(productNumberLabel);
        inventoryPanelTop.add(productNumberText);
        
        //Create mainPanelBottom and set layout
        inventoryPanelBottom = new JPanel();
        inventoryPanelBottom.setLayout(new GridLayout(4, 1));
        
        //Create the labels for the manufacturer
        manufacturerNameLabel = new JLabel("Manufacturer Name");
        manufacturerAddressLabel = new JLabel("Address");
        manufacturerPhoneLabel = new JLabel("Phone Numeber");
        manufacturerIDLabel = new JLabel("ID Number");
   
        //Create the textboxes for the manufacturer
        manufacturerNameText = new JTextField(20);
        manufacturerAddressText = new JTextField(20);
        manufacturerPhoneText = new JTextField(10);
        manufacturerIDText = new JTextField(10);
        
        inventoryPanelBottom.setBorder(BorderFactory.createTitledBorder
        ("Manufacturer Information"));
        
        //Add Labels and textboxes to the inventoryPanelBottom  
        inventoryPanelBottom.add(manufacturerNameLabel);
        inventoryPanelBottom.add(manufacturerNameText);
        
        inventoryPanelBottom.add(manufacturerAddressLabel);
        inventoryPanelBottom.add(manufacturerAddressText);
        
        inventoryPanelBottom.add(manufacturerPhoneLabel);
        inventoryPanelBottom.add(manufacturerPhoneText);
        
        inventoryPanelBottom.add(manufacturerIDLabel);
        inventoryPanelBottom.add(manufacturerIDText);
    }
    
    private void buildSearchPanel()
    {
        //Create searchPanelTop & set layout
        searchPanelTop = new JPanel();

        //Create JRadioButtons
        employeeButton = new JRadioButton("Employee", false);
        productButton = new JRadioButton("Product", false);
        
        //Add listeners to JRadioButtons for changing the bottomPanel labels and textboxes
        RadioButtonHandler rbHandler = new RadioButtonHandler();
        employeeButton.addItemListener(rbHandler);
        productButton.addItemListener(rbHandler);
         
        //Set layout for searchPanelTop
        searchPanelTop.setLayout(new FlowLayout());
        
        //Create a titled border for search selection and add search buttons
        searchPanelTop.setBorder(BorderFactory.createTitledBorder("Search Selection"));
        searchPanelTop.add(employeeButton);
        searchPanelTop.add(productButton);
        
        //Create searchPanelBottom and set layout
        searchPanelBottom = new JPanel();
        searchPanelBottom.setLayout((new GridLayout(5,1)));
        
        //Create two sets of labels for searchPanelBottom based on selection
        
        //Employee Selection Labels
        searchFirstNameLabel = new JLabel("First Name");
        searchLastNameLabel = new JLabel("Last Name");
        
        //Product Selection Labels
        searchProductNameLabel = new JLabel("Product Name");
        searchProductNumberLabel = new JLabel("Product Number");
        
        //Create two sets of textboxes for searchPanelBottom based on selection
        
        //Employee Selection Textboxes
        searchFirstNameText = new JTextField(15);
        searchLastNameText = new JTextField(15);
        
        //Product Selection Textboxes
        searchProductNameText = new JTextField(15);
        searchProductNumberText = new JTextField(10);
    }
    
    private void buildEmployeeButtonPanel()
    {
        //Create employee button panel, submit button, and exit button
        employeeButtonPanel = new JPanel();
        createButton = new JButton("Create");
        
        //Add the specific action listener for each button
        createButton.addActionListener(new CreateButtonListener());    
        
        //Add the JButtons to the buttonPanel
        employeeButtonPanel.add(createButton);
    }
    
    private void buildInventoryButtonPanel()
    {
        //Create button panel, submit button, and exit button
        inventoryButtonPanel = new JPanel();
        submitButton = new JButton("Submit");
        exitButton = new JButton("Exit");
        
        //Add the specific action listener for each button
        submitButton.addActionListener(new SubmitButtonListener());
        exitButton.addActionListener(new ExitButtonListener());     
        
        //Add the JButtons to the buttonPanel
        inventoryButtonPanel.add(submitButton);
        inventoryButtonPanel.add(exitButton);
    }
    
    private void buildSearchButtonPanel()
    {
        //Create searchButtonPanel, search button and exit button
        searchButtonPanel = new JPanel();
        searchButton = new JButton("Search");
        exitButton = new JButton("Exit");
        
        //Add the specific action listener for each button
        //Add search button action listener - Part 2 Functionality
        //Search button will load new form for searchPanel based on selection
        
        searchButton.addActionListener(new SearchButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        //Add the JButtons to the searchButtonPanel
        searchButtonPanel.add(searchButton);
        searchButtonPanel.add(exitButton);
    }
    
    //private inner class for event handling
    private class SearchButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            JOptionPane.showMessageDialog(null, "Searching the database...", 
                      "Search", WIDTH);
            
            //Add functionality later to retrieve the record and display result
        }
    }
    
    private class RadioButtonHandler implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            
            // code for changing labels and textboxes based on search selection
            if(employeeButton.isSelected() == true)
            {
                //Titled border
                TitledBorder title;
                title = BorderFactory.createTitledBorder("Employee Information");
                searchPanelBottom.setBorder(title);
                title.setTitleJustification(TitledBorder.CENTER);
                
                //Remove Product Labels and textboxes if there
                searchPanelBottom.remove(searchProductNameLabel);
                searchPanelBottom.remove(searchProductNameText);
                searchPanelBottom.remove(searchProductNumberLabel);
                searchPanelBottom.remove(searchProductNumberText);
                
                //Reload Panel
                searchPanelBottom.revalidate();

                //Add Employee Labels and textboxes
                searchPanelBottom.add(searchFirstNameLabel);
                searchPanelBottom.add(searchFirstNameText);
                searchPanelBottom.add(searchLastNameLabel);
                searchPanelBottom.add(searchLastNameText);
 
            }
            else if(productButton.isSelected() == true)
            {
                //Titled borders
                TitledBorder title;
                title = BorderFactory.createTitledBorder("Product Information");
                searchPanelBottom.setBorder(title);
                title.setTitleJustification(TitledBorder.CENTER);
                
                //Remove Employee Labels and textboxes if there
                searchPanelBottom.remove(searchFirstNameLabel);
                searchPanelBottom.remove(searchFirstNameText);
                searchPanelBottom.remove(searchLastNameLabel);
                searchPanelBottom.remove(searchLastNameText);
                
                //Reload Panel
                searchPanelBottom.revalidate();
                
                //Add Product Labels and textboxes
                searchPanelBottom.add(searchProductNameLabel);
                searchPanelBottom.add(searchProductNameText);
                searchPanelBottom.add(searchProductNumberLabel);
                searchPanelBottom.add(searchProductNumberText);
            } 
        }
    }
    
    
    
    //private inner class for event handling
    private class ExitButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            //Check if user wants to exit the application
            int reply = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to Exit?", 
                    "Exit", JOptionPane.YES_NO_OPTION);
            
            if (reply == JOptionPane.YES_OPTION) 
            {
              System.exit(0);
            }
        }
    }
    
    //private inner class for event handling
    private class SubmitButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            //Check if user is ready to submit employee details
            int response = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to submit this form", 
                    "Create", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) 
            {
              JOptionPane.showMessageDialog(null, "Form Submitted", 
                      "Confirmation", WIDTH);
              
            }
           
        }
    }
    
    //private inner class for event handling
    private class CreateButtonListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
            
            
            //**************************************************************
            
            
            // if no employee type is selected
            keepGoing = true; //resets to true before condition checks
            checkEmployeeInformation();//checks firstname,lastname,gender,address,phone,sin,year,month,day
 
            // if hourly employee is selected
            checkHourlyEmployeeInformation();
            
            // if commission employee is selected
            checkCommissionEmployeeInformation();
            
            // if salary + commission employee is selected
            checkSalaryPlusCommissionEmployeeInformation();
            
            // if salary employee is selected
            checkSalaryEmployeeInformation();
            
            
            //***************************************************************  
            
            
            
            
            // If all variables have been validated and aren't empty, create employee
            if(keepGoing == true)
            {
                int response = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to create this employee?", 
                    "Create", JOptionPane.YES_NO_OPTION);
            }
            
            
            // if all fields are not empty and validated then create employee and reset textfields
            /*
            if (response == JOptionPane.YES_OPTION && keepGoing == true ) 
            {
              JOptionPane.showMessageDialog(null, "Employee Created", 
                      "Confirmation", WIDTH);
              
              //Create employee and store in db **
              
              //Clear textboxes when create button clicked
              
              genderText.setText("");
              addressText.setText("");
              phoneNumberText.setText("");
              sinText.setText("");
            
              yearText.setText("");
              monthText.setText("");
              dayText.setText("");
            
              positionText.setText("");
              statusText.setText("");
              departmentText.setText("");
              idNumberText.setText("");
            
              hourlyRateText.setText("");
              baseSalaryText.setText("");
              commissionRateText.setText("");
              
              firstNameText.setText("");
              lastNameText.setText("");
            }*/
        }
    }
    private void checkCommissionEmployeeInformation()
    {
        
    }
    
    private void checkSalaryEmployeeInformation()
    {
        
    }
    
    private void checkSalaryPlusCommissionEmployeeInformation()
    {
        
    }
    
    private void checkHourlyEmployeeInformation()
    {
        
    }
    
    /*
    * Checks to make sure textfields aren't empty
    * 
    */
    private void checkEmployeeInformation()
    {
        //if string is empty, keepGoing turns false
            if(firstNameText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(lastNameText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(genderText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(addressText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(phoneNumberText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(sinText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(yearText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(monthText.getText().isEmpty())
            {
                keepGoing = false;
            }
            if(dayText.getText().isEmpty())
            {
                keepGoing = false;
            }
    }
    
    //main
    public static void main(String[] args)
    {
        MainGUI gui = new MainGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
             
}
