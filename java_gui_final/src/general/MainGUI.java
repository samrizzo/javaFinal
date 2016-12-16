package general;

import customers.Customer;
import hr.BasePlusCommissionEmployee;
import hr.CommissionSalesEmployee;
import hr.Employee;
import hr.HourlyEmployee;
import hr.SalaryEmployee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.border.TitledBorder;
import hr.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import manufacturer.Manufacturer;
import product.Product;


/**
 *
 * @author Sam Rizzo
 */
public class MainGUI extends JFrame
{
    //Set JFrame to center screen
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    //ArrayList for products sold
    private ArrayList<Product> soldItems = new ArrayList();
    
    //Current product Object
    private Product currentProd;
    
    //Counter to keep track of sales
    private int itemsSoldCount = 0;
    
    //Temp total and total cost
    private double tempTotal = 0.00, totalCost = 0.00;
    
    //Employee and Product Search Objects
    private Employee searchEmp;
    private Product searchProd;
    
    //ArrayLists for product and employee search
    private final ArrayList<Employee> employeeList = new ArrayList<>();
    private final ArrayList<Product> productList = new ArrayList<>();
    
    //String array to hold product, manufacturer and employee names
    String[] productNameArray;
    String[] employeeNameArray;
    String[] manufacturerNameArray;
    
    //Manufacturer object to hold data for product tab
    private Manufacturer temp;
    
    //Arraylist to hold manufacturer objects
    private final ArrayList<Manufacturer> manufacturerList = new ArrayList<>();
    
    //Dimension for search JTable 
    private final Dimension tableSize = new Dimension(800, 200);
    
    //JFrame for login page
    private final JFrame loginFrame = new JFrame();
    
    //BufferedWriter for writing to a file
    private BufferedWriter bufferWriter = null;

    //Date reference for IO Error log
    private final Calendar today = Calendar.getInstance();
    
    //JPanels
    private JPanel  loginPanelTop, loginPanelBottom, welcomePanel, 
            employeePanel = new JPanel(),mainEmployeePanel, employeePanelTop, 
            innerEmployeePanel, employeeDOBPanel, employeePanelBottom, employeePayPanel, 
            inventoryPanel = new JPanel(), inventoryPanelTop, innerInventoryPanel, inventoryPanelNorth, inventoryPanelBottom,
            searchPanel = new JPanel(), innerSearchPanel, searchPanelTop, searchPanelNorth, searchPanelBottom, 
            searchSalesPanel = new JPanel(), innerSearchSalesPanel, searchSalesPanelTop, searchSalesPanelNorth, searchSalesPanelBottom,
            salesPanel = new JPanel(), salesPanelTop, salesPanelBottom, salesButtonPanel, innerSalesPanel, innerSalesButtonPanel,
            customerPanel = new JPanel(), customerPanelTop, customerPanelBottom, 
            employeeButtonPanel, inventoryButtonPanel, searchButtonPanel,
            adminPanel = new JPanel(),adminSelectPanel, adminPanelTop, adminPanelBottom;
    
    //Create Tabbed Interface
    private final JTabbedPane tabPane = new JTabbedPane();
    
    //Create a Tabbed Interface for the Sales tab
    private final JTabbedPane salesPane = new JTabbedPane();
    
    //JLabels for loginPanel
    private JLabel loginUsernameLabel, loginPasswordLabel;
    
    //JTextField for loginPanel
    private JTextField loginUsernameText;
    
    //JPasswordField for loginPanel
    private JPasswordField loginPasswordText;
   
    //JLabels for HR Tab
    private JLabel firstNameLabel, lastNameLabel,genderLabel, addressLabel, 
            phoneNumberLabel, sinLabel, yearLabel, monthLabel, dayLabel,positionLabel, 
            statusLabel, departmentLabel, idNumberLabel, hourlyRateLabel, commissionRateLabel, baseSalaryLabel,
            welcomeLabel;
    
    //JTextFields for HR & Search Tabs
    private JTextField firstNameText, lastNameText,searchFirstNameText,searchLastNameText,
            searchProductNameText, searchProductNumberText,
            genderText, addressText, phoneNumberText, sinText, yearText, monthText, 
            dayText, positionText, statusText, departmentText, idNumberText, 
            hourlyRateText, commissionRateText, baseSalaryText;
    
    //JLabels for Search Tab
    private JLabel searchFirstNameLabel,searchLastNameLabel,
            searchProductNameLabel, searchProductNumberLabel;
    
    //JTable for searchData
    private JTable table = new JTable(0,0);
    
    //JScrollPane for JTable
    private JScrollPane scrollPane = new JScrollPane();
    
    private JTextField resultFirstNameText, resultLastNameText, searchGenderText, searchAddressText, 
            searchPhoneNumberText, searchSinNumberText, searchDateOfBirthText, searchPositionText, 
            searchStatusText, searchDepartmentText, searchIDNumberText, resultProductNameText, 
            searchProductDescriptionText, searchProductCostText, resultProductNumberText;
    
    //JLabels for Inventory Tab
    private JLabel productNameLabel, productDescriptionLabel, productCostLabel, 
            productNumberLabel, manufacturerSelectionLabel, manufacturerNameLabel, manufacturerAddressLabel, 
            manufacturerPhoneLabel, manufacturerIDLabel;
    
    //JTextFields for Inventory Tab
    private JTextField productNameText, productDescriptionText, productCostText, 
            productNumberText, manufacturerNameText, manufacturerAddressText, 
            manufacturerPhoneText, manufacturerIDText;
    
    //JComboBox for manufacturer selection
    private JComboBox manufacturerBox = new JComboBox();
    
    //JLabels for Customer Tab
    private JLabel customerFirstNameLabel, customerLastNameLabel, customerAddressLabel,
            customerPhoneNumberLabel, customerCityLabel, customerPostalCodeLabel;
    
    //JTextField for Customer Tab
    private JTextField customerFirstNameText, customerLastNameText, customerAddressText, 
            customerPhoneNumberText, customerCityText, customerPostalCodeText;
    
    //JButton for customer Tab
    private JButton addCustomerButton;
    
    //JLabels for Create Sales Tab
    private JLabel orderNumberLabel, employeeSelectionLabel, employeeIDLabel, productSelectionLabel, 
            saleProductNameLabel, saleProductNumberLabel, saleProductDescriptionLabel, 
            saleProductCostLabel, totalCostLabel, itemsSoldLabel;
    
    //JTextField for Create Sales Tab
    private JTextField orderNumberText, employeeIDText, saleProductNameText, 
            saleProductNumberText, saleProductDescriptionText, saleProductCostText, 
            totalCostText, itemsSoldText;
    
    //JButtons for Create Sales Tab
    private JButton addItemButton, addTransactionButton;
    
    //JComboBoxes for Create Sales Tab
    private JComboBox employeeSaleBox = new JComboBox();
    private JComboBox productSaleBox = new JComboBox();
    
    //ButtonGroup + JRadio Buttons
    private final ButtonGroup searchBox = new ButtonGroup();
    private JButton exitButton, submitButton, createButton, 
            searchButton, createUserButton;
    
    //ButtonGroup for userStatus selection
    private final ButtonGroup userStatusBox = new ButtonGroup();
    
    //JRadioButtons to track search selection
    private JRadioButton employeeButton, productButton, regularUserButton, adminUserButton;
    
    //ButtonGroup and JRadioButtons for buildAdminPanel
    private final ButtonGroup adminButtonGroup = new ButtonGroup();
    private JRadioButton adminEmployeeButton, adminProductButton, adminSalesButton, 
            adminManufacturerButton;
    
    //JComboBox to hold Employee Types
    private JComboBox<String> employeeType;
    private final String[] typeOfEmployee = 
            {"Hourly","Salary","Commission", "Base + Commission"};
    
    //boolean
    private boolean keepGoing = true;
    
    //gets the typeOfEmployee.getSelectedIndex() and gives the int the number
    private int employeeIndex=10;
    
    // first response
    private int response,response2;
    
    //boolean for determining if the user is an Admin
    boolean isAdmin;
    
    //User Object
    private User user;
    
    //User information for creating a user object
    private String username, password;
    
    //username and password for db connection
    //private String dbUsername = "gc200271677";
    //private String dbPassword = "Y-xX3iij";
    private String dbUsername = "gc200298516";
    private String dbPassword = "E-6!t7Xe";
    
    //Setup db query and connection
    //private final String dbURL = "jdbc:mysql://sql.computerstudi.es:3306/gc200271677";
    private final String dbURL = "jdbc:mysql://sql.computerstudi.es:3306/gc200298516";
        
    //Create Connection objects
    private Statement statement = null;
    private ResultSet result = null;
    private Connection conn = null;
   
    public MainGUI()
    {
        //Create App title and layout
        super("Helix Administration");
        setLayout(new BorderLayout());
        
        //Build loginPanel
        buildLoginPanel();  
        
        //Configure the tabs
        tabPane.addTab("HR", null, employeePanel, "Human Resource");
        tabPane.addTab("Inventory", null, inventoryPanel, "Inventory");
        tabPane.addTab("Search", null, searchPanel, "Search");
        tabPane.addTab("Sales", null, salesPane, "Sales");
        tabPane.addTab("Customers", null, customerPanel, "Customers");
        
        //Create inner tabs
        salesPane.addTab("Create",null, salesPanel,"Sales");
        salesPane.addTab("Search",null,searchSalesPanel ,"Sales");
        
        //Build Panels
        buildMainPanel();

        //Tab Panels
        buildEmployeePanel();
        buildInventoryPanel();
        buildSearchPanel();
        buildSalesPanel();
        buildSearchSalesPanel();
        buildCustomerPanel();

        //Button Panels
        buildEmployeeButtonPanel();
        buildInventoryButtonPanel();
        buildSearchButtonPanel();
        buildSalesButtonPanel();
        
        //Add Employee Panel
        employeePanel.setLayout(new BorderLayout());
        employeePanel.add(mainEmployeePanel, BorderLayout.NORTH);
        employeePanel.add(employeePanelBottom, BorderLayout.CENTER);
        employeePanel.add(employeeButtonPanel, BorderLayout.SOUTH);

        //Add welcomePanel and tabbedPanel
        add(welcomePanel, BorderLayout.NORTH);
        add(tabPane, BorderLayout.CENTER);

        //Add Inventory Panel
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.add(inventoryPanelTop, BorderLayout.NORTH);
        inventoryPanel.add(inventoryPanelNorth, BorderLayout.CENTER);
        inventoryPanel.add(inventoryButtonPanel, BorderLayout.SOUTH);

        //Add Search Panel
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchPanelNorth, BorderLayout.NORTH);
        searchPanel.add(searchPanelBottom, BorderLayout.CENTER);
        searchPanel.add(searchButtonPanel, BorderLayout.SOUTH);

        //ButtonGroup + radio buttons for user selection
        searchBox.add(employeeButton);
        searchBox.add(productButton);

        userStatusBox.add(regularUserButton);
        userStatusBox.add(adminUserButton);
        
        //ButtonGroup & JRadioButtons for adminPanel
        adminButtonGroup.add(adminEmployeeButton);
        adminButtonGroup.add(adminProductButton);
        adminButtonGroup.add(adminSalesButton);
        adminButtonGroup.add(adminManufacturerButton);
        
        //Add panels to Sales Panel
        salesPanel.setLayout(new BorderLayout());
        salesPanel.add(salesPanelTop, BorderLayout.NORTH);
        salesPanel.add(innerSalesPanel, BorderLayout.CENTER);
        salesPanel.add(salesButtonPanel, BorderLayout.SOUTH);
        
        //Add seearchSalesPanel
        searchSalesPanel.setLayout(new BorderLayout());
        searchSalesPanel.add(innerSearchSalesPanel, BorderLayout.NORTH);
        searchSalesPanel.add(searchSalesPanelBottom, BorderLayout.SOUTH);
        
        //Add panels to Customer Panel
        customerPanel.setLayout(new BorderLayout());
        customerPanel.add(customerPanelTop, BorderLayout.NORTH);
        customerPanel.add(customerPanelBottom, BorderLayout.SOUTH);

        // JComboBox for type of employees
        employeeType = new JComboBox(typeOfEmployee);
        employeeType.setMaximumRowCount(3);
    }

    private void buildLoginPanel()
    {
        // Set the title and layout of the loginFrame
        loginFrame.setTitle("Login");
        loginFrame.setLayout(new BorderLayout());
       
        //Create the loginPanel and set the layout
        loginPanelTop = new JPanel();
        loginPanelTop.setLayout(new GridLayout(4, 2));
        
        //Create a title for the panel
        loginPanelTop.setBorder(BorderFactory.createTitledBorder("Select Status of User"));
        
        //JLabels for username and password
        loginUsernameLabel = new JLabel("Username:");
        loginPasswordLabel = new JLabel("Password:");
        
        //JTextField and JPasswordField for username and password
        loginUsernameText = new JTextField(10);
        loginPasswordText = new JPasswordField(10);
        
        //JRadioButtons for selecting user as regular of admin
        regularUserButton = new JRadioButton("Regular");
        adminUserButton = new JRadioButton("Admin");
        
        //Add ItemListener to the JRadioButtons
        UserSelectButtonHandler userSelection = new UserSelectButtonHandler();
        regularUserButton.addItemListener(userSelection);
        adminUserButton.addItemListener(userSelection);
        
        //On load set regular user as selected
        regularUserButton.setSelected(true);
       
        //Set the username and password variables equal to what was entered
        username = loginUsernameText.getText();
        password = Arrays.toString(loginPasswordText.getPassword());
        
        //Add labels, textboxes and JRadioButtons to the panel
        loginPanelTop.add(regularUserButton);
        loginPanelTop.add(adminUserButton);
        
        loginPanelTop.add(loginUsernameLabel);
        loginPanelTop.add(loginUsernameText);
        
        loginPanelTop.add(loginPasswordLabel);
        loginPanelTop.add(loginPasswordText);
        
        //Create the loginPanelBottom and set the layout
        loginPanelBottom = new JPanel();
        loginPanelBottom.setLayout(new FlowLayout());
        
        //Set info title
        loginPanelBottom.setBorder(BorderFactory.createTitledBorder
        ("Press enter after you add the username & password to enable button"));
        
        //JButton for creating a user
        createUserButton = new JButton("Create User");
        
        //Set disabled on load
        createUserButton.setEnabled(false);
        
        //Add itemStateChanged to the JButton
        ButtonEnabledListener buttonListener = new ButtonEnabledListener();
        loginUsernameText.addActionListener(buttonListener);
        loginPasswordText.addActionListener(buttonListener);
        
        //Add ActionListener to the JButton
        UserButtonListener userListener = new UserButtonListener();
        createUserButton.addActionListener(userListener);
        
        //Add the create user button to the loginPanelBottom
        loginPanelBottom.add(createUserButton);
        
        //Add loginPanelTop and loginPanelBottom to the frame
        loginFrame.add(loginPanelTop, BorderLayout.NORTH);
        loginFrame.add(loginPanelBottom, BorderLayout.SOUTH);
        
        //Pack the frame and setVisible to true
        loginFrame.setSize(440, 250);
        loginFrame.setVisible(true);
        
        //Set Location of JFrames to center
        loginFrame.setLocationRelativeTo(null);
    }
    
    //Build Admin Panel
    private void buildAdminPanel()
    {
        //Set the layout of the adminPanel
        adminPanel.setLayout(new BorderLayout());
        
        //Create the adminSelectPanel and set layout
        adminSelectPanel = new JPanel();
        adminSelectPanel.setLayout(new GridLayout(1, 4));
        
        //Add a title to the selectPanel
        adminSelectPanel.setBorder(BorderFactory.createTitledBorder
        ("Please select the object you want to edit/delete"));
        
        //Create the JRadioButtons
        adminEmployeeButton = new JRadioButton("Employee");
        adminProductButton = new JRadioButton("Product");
        adminSalesButton = new JRadioButton("Sales");
        adminManufacturerButton = new JRadioButton("Manufacturer");
        
        //Create the ItemListener for the JRadioButtons
        AdminButtonHandler adminListener = new AdminButtonHandler();
        
        //Add ActionListener to all JRadioButtons for adminPanel
        adminEmployeeButton.addItemListener(adminListener);
        adminProductButton.addItemListener(adminListener);
        adminSalesButton.addItemListener(adminListener);
        adminManufacturerButton.addItemListener(adminListener);
        
        //Add JRadioButtons to the adminPanelTop
        adminSelectPanel.add(adminEmployeeButton);
        adminSelectPanel.add(adminProductButton);
        adminSelectPanel.add(adminSalesButton);
        adminSelectPanel.add(adminManufacturerButton);
        
        adminPanel.add(adminSelectPanel, BorderLayout.NORTH);
    }
    
    //Build Sales Panel
    private void buildSalesPanel()
    {
        //Create salesPanelTop and set layout
        salesPanelTop = new JPanel();
        salesPanelTop.setLayout(new GridLayout(4,1));
        
        //Create the labels and textboxes
        orderNumberLabel = new JLabel("Order Number");
        orderNumberText = new JTextField(6);
        
        employeeSelectionLabel = new JLabel("Select Employee");
        productSelectionLabel = new JLabel("Select Product");
        saleProductNameLabel = new JLabel("Product Name");
        saleProductNumberLabel = new JLabel("Product Number");
        saleProductDescriptionLabel = new JLabel("Product Description");
        saleProductCostLabel = new JLabel("Product Cost");
        itemsSoldLabel = new JLabel("Items Sold");
        employeeIDLabel = new JLabel("Employee ID");
        totalCostLabel = new JLabel("Total Cost");
        
        employeeIDText = new JTextField(6);
        itemsSoldText = new JTextField(2);
        saleProductNameText = new JTextField(30);
        saleProductNumberText = new JTextField(6);
        saleProductDescriptionText = new JTextField(100);
        saleProductCostText = new JTextField(6);
        totalCostText = new JTextField(8);
        
        //Fill ComboBox with data from db
        getEmployeeInfo();
        getProductInfo();
        
        //JComboBoxes for selections
        employeeSaleBox = new JComboBox(employeeNameArray);
        productSaleBox = new JComboBox(productNameArray);
        
        //Add to salesPanelTop
        salesPanelTop.add(employeeSelectionLabel);
        salesPanelTop.add(employeeSaleBox);
        salesPanelTop.add(productSelectionLabel);
        salesPanelTop.add(productSaleBox);

        //Create salesPanelBottom and set Layout
        salesPanelBottom = new JPanel();
        salesPanelBottom.setLayout(new GridLayout(14, 1));
        
        //Create the innerSalesButtonPanel
        innerSalesButtonPanel = new JPanel();
        innerSalesButtonPanel.setLayout(new FlowLayout());
        
        //Add the addItem button
        addItemButton = new JButton("Add to Order");
        
        //Create and add actionListener to JButton
        AddItemButtonListener addItemListener = new AddItemButtonListener();
        addItemButton.addActionListener(addItemListener);
        
        //Add button to panel
        innerSalesButtonPanel.add(addItemButton);
        
        //Create innerSalesPanel and set layout
        innerSalesPanel = new JPanel();
        innerSalesPanel.setLayout(new BorderLayout());
        
        //Add panels to innerSalesPanel
        innerSalesPanel.add(salesPanelBottom, BorderLayout.NORTH);
        innerSalesPanel.add(innerSalesButtonPanel, BorderLayout.SOUTH);
        
        //add labels and textboxes to salesPanelBottom
        salesPanelBottom.add(employeeIDLabel);
        salesPanelBottom.add(employeeIDText);
        salesPanelBottom.add(itemsSoldLabel);
        salesPanelBottom.add(itemsSoldText);
        salesPanelBottom.add(saleProductNumberLabel);
        salesPanelBottom.add(saleProductNumberText);
        salesPanelBottom.add(saleProductNameLabel);
        salesPanelBottom.add(saleProductNameText);
        salesPanelBottom.add(saleProductDescriptionLabel);
        salesPanelBottom.add(saleProductDescriptionText);
        salesPanelBottom.add(saleProductCostLabel);
        salesPanelBottom.add(saleProductCostText);
        salesPanelBottom.add(totalCostLabel);
        salesPanelBottom.add(totalCostText);

    }
    
    //Build SearchSales Panel
    private void buildSearchSalesPanel()
    {
        //Create the salesPanel North and set layout
        searchSalesPanelNorth = new JPanel();
        searchSalesPanelNorth.setLayout(new BorderLayout());
        
        //Create the innerSearchSalesPanel and set layout
        innerSearchSalesPanel = new JPanel();
        innerSearchSalesPanel.setLayout(new FlowLayout());
        
        //Create the searchSalesPanelBottom and set layout
        searchSalesPanelBottom = new JPanel();
        searchSalesPanelBottom.setLayout(new GridLayout(12,1));
    }

    //Build Customer Panel
    private void buildCustomerPanel()
    {
        customerPanelTop = new JPanel();
        customerPanelTop.setLayout(new GridLayout(12, 1));
        
        //Add a titled border to the customerPanelTop
        customerPanelTop.setBorder(BorderFactory.createTitledBorder("Enter Customer Details"));
        
        //Create Labels for Customer form
        customerFirstNameLabel = new JLabel("First Name");
        customerLastNameLabel = new JLabel("Last Name");
        customerAddressLabel = new JLabel("Address");
        customerPhoneNumberLabel = new JLabel("Phone Number");
        customerCityLabel = new JLabel("City");
        customerPostalCodeLabel = new JLabel("Postal Code");
        
        //Create the textboxes for the Customer form
        customerFirstNameText = new JTextField(30);
        customerLastNameText = new JTextField(30);
        customerAddressText = new JTextField(30);
        customerPhoneNumberText = new JTextField(10);
        customerCityText = new JTextField(20);
        customerPostalCodeText = new JTextField(6);
        
        //Add labels and textboxes to the customerPanelTop
        customerPanelTop.add(customerFirstNameLabel);
        customerPanelTop.add(customerFirstNameText);
        customerPanelTop.add(customerLastNameLabel);
        customerPanelTop.add(customerLastNameText);
        customerPanelTop.add(customerAddressLabel);
        customerPanelTop.add(customerAddressText);
        customerPanelTop.add(customerPhoneNumberLabel);
        customerPanelTop.add(customerPhoneNumberText);
        customerPanelTop.add(customerCityLabel);
        customerPanelTop.add(customerCityText);
        customerPanelTop.add(customerPostalCodeLabel);
        customerPanelTop.add(customerPostalCodeText);
        
        //Create and set layout for customer panel bottom
        customerPanelBottom = new JPanel();
        customerPanelBottom.setLayout(new FlowLayout());
        
        //Add the customer button
        addCustomerButton = new JButton("Add Customer");
        
        //Create and add actionListener to JButton
        CreateUserListener custListener = new CreateUserListener();
        addCustomerButton.addActionListener(custListener);
        
        //Add button to panel
        customerPanelBottom.add(addCustomerButton);
    }
    
    //Build Employee Panel
    private void buildMainPanel() 
    {
        //Create Panel and Greeting
        welcomePanel = new JPanel();
        welcomeLabel = new JLabel(" Welcome to Helix");
        
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
             System.out.println("T1"+employeeType.getSelectedIndex());
               //if hourly
            if (employeeType.getSelectedIndex() == 0)
            {
                
                employeeIndex=0;
                
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
                
                employeeIndex=1;
                
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
                
                employeeIndex=2;
                
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
                
                employeeIndex=3;
                
                employeePayPanel.remove(hourlyRateLabel);
                employeePayPanel.remove(hourlyRateText);
                
                employeePayPanel.revalidate();
                
                employeePayPanel.add(baseSalaryLabel);
                employeePayPanel.add(baseSalaryText);
                
                employeePayPanel.add(commissionRateLabel);
                employeePayPanel.add(commissionRateText); 
            }
                         System.out.println("T2"+employeeType.getSelectedIndex());

            }//end of action performed 
        });//end of action listen
    }//end of buildEmployeePanel
    
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
        
        //Create and set layout for inventoryPanelNorth
        inventoryPanelNorth = new JPanel();
        inventoryPanelNorth.setLayout(new BorderLayout());

        //Create innerInventoryPanel and select layout
        innerInventoryPanel = new JPanel();
        innerInventoryPanel.setLayout(new GridLayout(2,1));
        innerInventoryPanel.setBorder(BorderFactory.createTitledBorder
        ("Manufacturer Information"));

        getManufacturerInfo();
        
        manufacturerBox = new JComboBox(manufacturerNameArray);
        
        //Add ComboBox Handler for selection
        ProductComboBoxHandler prodHandler = new ProductComboBoxHandler();
        manufacturerBox.addItemListener(prodHandler);
        
        //Create JComboBox and JLabel for selection and add to panel
        manufacturerSelectionLabel = new JLabel("Selection");
        innerInventoryPanel.add(manufacturerSelectionLabel);
        innerInventoryPanel.add(manufacturerBox);
        
        //Create mainPanelBottom and set layout
        inventoryPanelBottom = new JPanel();
        inventoryPanelBottom.setLayout(new GridLayout(8,1));
            
        //add panels to inventoryPanelNorth
        inventoryPanelNorth.add(innerInventoryPanel, BorderLayout.NORTH);
        inventoryPanelNorth.add(inventoryPanelBottom, BorderLayout.CENTER);
        
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
        
        //Add Labels and textboxes to the inventoryPanelBottom  
        inventoryPanelBottom.add(manufacturerNameLabel);
        inventoryPanelBottom.add(manufacturerNameText);
        
        inventoryPanelBottom.add(manufacturerAddressLabel);
        inventoryPanelBottom.add(manufacturerAddressText);
        
        inventoryPanelBottom.add(manufacturerPhoneLabel);
        inventoryPanelBottom.add(manufacturerPhoneText);
        
        inventoryPanelBottom.add(manufacturerIDLabel);
        inventoryPanelBottom.add(manufacturerIDText);
        
    } // end of buildInventoryPanel()
    
    private void buildSearchPanel()
    {
        //Create and set layout for searchPanelNorth
        searchPanelNorth = new JPanel();
        searchPanelNorth.setLayout(new BorderLayout());
        
        //Create and set layout for innerSearchPanel
        innerSearchPanel = new JPanel();
        innerSearchPanel.setLayout(new FlowLayout());

        //Create JRadioButtons
        employeeButton = new JRadioButton("Employee", false);
        productButton = new JRadioButton("Product", false);
        
        //Add listeners to JRadioButtons for changing the bottomPanel labels and textboxes
        RadioButtonHandler rbHandler = new RadioButtonHandler();
        employeeButton.addItemListener(rbHandler);
        productButton.addItemListener(rbHandler);
        
        //Create a titled border for search selection and add search buttons
        innerSearchPanel.setBorder(BorderFactory.createTitledBorder("Search Selection"));
        innerSearchPanel.add(employeeButton);
        innerSearchPanel.add(productButton);
        
        //Create searchPanelTop & set layout
        searchPanelTop = new JPanel();
        searchPanelTop.setLayout(new FlowLayout());

        //Create two sets of labels for searchPanelTop based on selection
        
        //Employee Selection Labels
        searchFirstNameLabel = new JLabel("First Name");
        searchLastNameLabel = new JLabel("Last Name");
        
        //Product Selection Labels
        searchProductNameLabel = new JLabel("Product Name");
        searchProductNumberLabel = new JLabel("Product Number");
        
        //Create two sets of textboxes for searchPanelTop based on selection
        
        //Employee Selection Textboxes
        searchFirstNameText = new JTextField(15);
        searchLastNameText = new JTextField(15);
        
        //Product Selection Textboxes
        searchProductNameText = new JTextField(15);
        searchProductNumberText = new JTextField(10);
        
        //Add searchPanelTop and innerSearchPanel to searchPanelNorth
        searchPanelNorth.add(innerSearchPanel, BorderLayout.NORTH);
        searchPanelNorth.add(searchPanelTop, BorderLayout.SOUTH);
        
        //Create searchPanelBottom and set layout
        searchPanelBottom = new JPanel();
        
        //Create the JScrollPane and set dimensions
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(tableSize);
        
        //Add scrollPane to the searchPanelBottom
        searchPanelBottom.add(scrollPane);
        
    } // end of buildSearchPanel()
    
    private void buildEmployeeButtonPanel()
    {
        //Create employee button panel, submit button, and exit button
        employeeButtonPanel = new JPanel();
        createButton = new JButton("Create");
        exitButton = new JButton("Exit");
        
        //CreateButtonListener
        CreateButtonListener createListener = new CreateButtonListener();
        
        //Add the specific action listener for each button
        createButton.addActionListener(createListener); 
        exitButton.addActionListener(new ExitButtonListener());
        
        //Add the JButtons to the buttonPanel
        employeeButtonPanel.add(createButton);
        employeeButtonPanel.add(exitButton);
        
    } // end of buildEmployeeButtonPanel()
    
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
        searchButton.addActionListener(new SearchButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        //Add the JButtons to the searchButtonPanel
        searchButtonPanel.add(searchButton);
        searchButtonPanel.add(exitButton);
    }
    
    private void buildSalesButtonPanel()
    {
        //Create salesButtonPanel
        salesButtonPanel = new JPanel();
        addTransactionButton = new JButton("Add Transaction");
        exitButton = new JButton("Exit");
        
        //Add the specific action listener for each button
        
        addTransactionButton.addActionListener(new TransactionButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        //Add the JButtons to the salesButtonPanel
        salesButtonPanel.add(addTransactionButton);
        salesButtonPanel.add(exitButton);
    }
    
    private class AddItemButtonListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if (saleProductNameText.getText().equals("") || employeeIDText.getText().equals("")) 
                {
                    JOptionPane.showMessageDialog(null, "Please select a product and employee to add", 
                      "Error", WIDTH);
                }
                
                else 
                {
                    itemsSoldCount = itemsSoldCount +1;
                    itemsSoldText.setText(Integer.toString(itemsSoldCount));
                    tempTotal = Double.parseDouble(productCostText.getText());
                    
                    totalCost = tempTotal + totalCost;
                    totalCostText.setText(Double.toString(tempTotal));
                    
                    Manufacturer tempManu = new Manufacturer();
                    
                    currentProd = new Product(productDescriptionText.getText(),
                            productNameText.getText(), Integer.parseInt(productNumberText.getText()), 
                            Double.parseDouble(productCostText.getText()), tempManu);
                    
                    soldItems.add(currentProd);
                }
            }
        }
    
    //Method for getting manufacturer info
    private void getManufacturerInfo()
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            
            appendToFile(error);
        }
        
        try
        {
            //If connection was successful
            if (conn != null)
            {
                statement = conn.createStatement();
                        
                String sql = "SELECT * FROM manufacturers";
                
                result = statement.executeQuery(sql);  
                
                while(result.next())
                {
                    //Create new Manufacturer Object to store data
                    temp = new Manufacturer();
                    
                    //Set attributes of manufacturer
                    temp.setIDNumber(Integer.parseInt(result.getString("idNumber")));
                    temp.setCompanyName(result.getString("companyName"));
                    temp.setAddress(result.getString("address"));
                    temp.setPhoneNumber(result.getString("phoneNumber"));
                    
                    //Add to array of manufacturers
                    manufacturerList.add(temp);
                }
            }  
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            appendToFile(error);
        }
        
        //ArrayList to store manufacturer names
        ArrayList<String> manufacturerNames = new ArrayList<>();
        
        //Add blank spot for array
        manufacturerNames.add("Select");
        
        //For loop to add manufacturerNames to String array
        for (Manufacturer manufacturer : manufacturerList) 
        {
            manufacturerNames.add(manufacturer.getCompanyName());
        }
        
        //String array for holding manufacturer names
        String[] names = manufacturerNames.toArray(new String[manufacturerNames.size()]);
        manufacturerNameArray = names;
    }
    
    //Method for getting product info
    private void getProductInfo()
    {
        //Connect to db
        try
        {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            
            appendToFile(error);
        }
        
        try
        {
            //If connection was successful
            if (conn != null)
            {
                statement = conn.createStatement();
                        
                //Selection query
                String sql = "SELECT * FROM products";

                //execute statement
                result = statement.executeQuery(sql);  
                
                while(result.next())
                {
                    //Create new Product Object to store data
                    searchProd = new Product();
                    
                    //Set attributes of product
                    searchProd.setProductName(result.getString("productName"));
                    searchProd.setProductDescription(result.getString("productDescription"));
                    searchProd.setProductCost(Double.parseDouble(result.getString("productCost")));
                    searchProd.setProductNumber(Integer.parseInt(result.getString("productNumber")));
                    
                    //Add to array of products
                    productList.add(searchProd);
                }
            }  
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            appendToFile(error);
        }
        
        //ArrayList to store product names
        ArrayList<String> productNames = new ArrayList<>();
        
        //For loop to add productNames to String array
        for (Product product : productList) 
        {
            productNames.add(product.getProductName());
        }
        
        //Temp String array for holding product names
        String[] names = productNames.toArray(new String[productNames.size()]);
        
        //Assign productNameArray to names
        productNameArray = names;
        
    }
    
    //Method for getting employee info
    private void getEmployeeInfo()
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            
            appendToFile(error);
        }
        
        try
        {
            //If connection was successful
            if (conn != null)
            {
                statement = conn.createStatement();
                        
                String sql = "SELECT * FROM employees";
                
                result = statement.executeQuery(sql);  
                
                while(result.next())
                {
                    //Create new Employee Object to store data
                    searchEmp = new HourlyEmployee();
                    
                    //Temp date and calendar object to get employee's DOB
                    String dateString = result.getString("dateOfBirth");
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
                    Date date = new Date();
                    
                    try
                    {
                        date = df.parse(dateString);
                    }
                    catch(Exception e)
                    {
                        
                    }
  
                    //Get instance of calendar and setTime for date object
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    
                    //Split into year, month and day and assign to employee
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
 
                    //Set attributes of employee
                    searchEmp.setFirstName(result.getString("firstName"));
                    searchEmp.setLastName(result.getString("lastName"));
                    searchEmp.setGender(result.getString("gender"));
                    searchEmp.setAddress(result.getString("address"));
                    searchEmp.setSIN(Integer.parseInt(result.getString("sinNum")));
                    searchEmp.setDateOfBirth(year, month, day);
                    
                    //Add to array of employees
                    employeeList.add(searchEmp);
                }
            }  
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            appendToFile(error);
        }
        
        //ArrayList to store employee names
        ArrayList<String> employeeNames = new ArrayList<>();
        
        //For loop to add employeeNames to String array
        for (Employee employee : employeeList) 
        {
            employeeNames.add(employee.getFullName());
        }
        
        //String array for holding employee names
        String[] names = employeeNames.toArray(new String[employeeNames.size()]);
        employeeNameArray = names;
    }
    
    //private inner class for event handling
    private class CreateUserListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(customerIncomplete() == false)
            {
                //addCustomerToDB
                try 
                {
                    //Create a temp customer object to store information
                    Customer temp = new Customer(customerFirstNameText.getText(), 
                        customerLastNameText.getText(), customerAddressText.getText(), 
                        customerPhoneNumberText.getText(), customerCityText.getText(), 
                    customerPostalCodeText.getText());
                    
                    //Add customer to db
                    addCustomerToDB(temp);
                    
                    //Clear textboxes
                    clearCustomerTab();
                }
                catch(Exception error)
                {
                    JOptionPane.showMessageDialog(null, "An error has occurred", 
                            "Error", WIDTH);

                    appendToFile(error);
                }
            }
            else
            {
                //Show message dialog
                JOptionPane.showMessageDialog(null, "All fields must be completed before being submitted", 
                        "Input Error", WIDTH);
            }
        }
    }   
    
    //Method to clear customer tab textboxes
    private void clearCustomerTab()
    {
        //Clear all textboxes
        customerFirstNameText.setText("");
        customerLastNameText.setText("");
        customerAddressText.setText("");
        customerPhoneNumberText.setText("");
        customerCityText.setText("");
        customerPostalCodeText.setText("");
        
    }
    
    //Method for checking Customer
    private boolean customerIncomplete()
    {
        return customerFirstNameText.getText().equals("") || 
                customerLastNameText.getText().equals("") ||
                customerAddressText.getText().equals("") ||
                customerPhoneNumberText.getText().equals("") ||
                customerCityText.getText().equals("") ||
                customerPostalCodeText.getText().equals("");
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
                searchPanelTop.setBorder(title);
                title.setTitleJustification(TitledBorder.CENTER);
                
                //Remove Product Labels and textboxes if there
                searchPanelTop.remove(searchProductNameLabel);
                searchPanelTop.remove(searchProductNameText);
                searchPanelTop.remove(searchProductNumberLabel);
                searchPanelTop.remove(searchProductNumberText);
                clearTable();
                
                //Reload Panel
                searchPanelTop.revalidate();

                //Add Employee Labels and textboxes
                searchPanelTop.add(searchFirstNameLabel);
                searchPanelTop.add(searchFirstNameText);
                searchPanelTop.add(searchLastNameLabel);
                searchPanelTop.add(searchLastNameText);
 
            }
            else if(productButton.isSelected() == true)
            {
                //Titled borders
                TitledBorder title;
                title = BorderFactory.createTitledBorder("Product Information");
                searchPanelTop.setBorder(title);
                title.setTitleJustification(TitledBorder.CENTER);
                
                //Remove Employee Labels and textboxes if there
                searchPanelTop.remove(searchFirstNameLabel);
                searchPanelTop.remove(searchFirstNameText);
                searchPanelTop.remove(searchLastNameLabel);
                searchPanelTop.remove(searchLastNameText);
                
                clearTable();
                
                //Reload Panel
                searchPanelTop.revalidate();
                
                //Add Product Labels and textboxes
                searchPanelTop.add(searchProductNameLabel);
                searchPanelTop.add(searchProductNameText);
                searchPanelTop.add(searchProductNumberLabel);
                searchPanelTop.add(searchProductNumberText);
            } 
        }
    } 
    
    private class AdminButtonHandler implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            if (adminEmployeeButton.isSelected() == true)
            {
                
            }
            
            else if (adminProductButton.isSelected() == true)
            {
                
            }
            
            else if (adminSalesButton.isSelected() == true)
            {
                
            }
            
            else if (adminManufacturerButton.isSelected() == true)
            {
                
            }
        }
    }
    
    //private inner class for event handling
    private class ButtonEnabledListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(loginUsernameText.getText().length() > 0)
                
            {
                createUserButton.setEnabled(true);
            }
            else
            {
                createUserButton.setEnabled(false);
            }
        }
    }
    
    //private inner class for event handling
    private class UserButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(loginUsernameText.getText().length() == 0 || loginPasswordText.getPassword().length == 0) 
            {
                JOptionPane.showMessageDialog(null, "Username and Password cannot be blank.\n"
                        + "Please press the Enter key after typing your username & password.",
                        "Error", WIDTH);
                
                createUserButton.setEnabled(false);
            }
            
            else if(loginUsernameText.getText().length() > 0 && loginPasswordText.getPassword().length > 0) 
            {
                JOptionPane.showMessageDialog(null, "Creating user", 
                    "Welcome",  WIDTH);

                //Create the new user
                user = new User(username, password);
                user.setAdmin(isAdmin);

                //Hide the login page
                loginFrame.setVisible(false);

                //Set the main form to visible
                setVisible(true);

                if (user.userIsAdmin() == true) 
                {
                    //Add the tab to edit/delete
                    tabPane.addTab("Edit/Delete", null, adminPanel, "Admin");

                    //build the adminPanel
                    buildAdminPanel();
                }
            }
        }
    }
    
    //private inner class for event handling
    private class SearchButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (employeeButton.isSelected() == true)
            {
                searchEmployeeDB(searchFirstNameText.getText(), searchLastNameText.getText());
                clearSearchBoxes();
            }
            
            else if(productButton.isSelected() == true)
            {
                searchProductDB(searchProductNameText.getText(), searchProductNumberText.getText());
                clearSearchBoxes();
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "Please select and object to search", 
                        "Input Error", WIDTH);
            }
        }
    }
    
    private class UserSelectButtonHandler implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            
            // code for changing labels and textboxes based on search selection
            if(regularUserButton.isSelected() == true)
            {
                isAdmin = false;
            }
            else if(adminUserButton.isSelected() == true)
            {
                isAdmin = true;
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
    private class TransactionButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            //Add transactions to db and clear array
            int reply = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to add the transaction to the database?", 
                        "Transaction", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) 
                {
                    //Add to db
                    try
                    {
                        conn = DriverManager.getConnection(dbURL, username, password);
                        statement = conn.createStatement();

                        String sql = "INSERT INTO sales VALUES('" + 
                                Integer.parseInt(itemsSoldText.getText()) + "', ' "
                                +  productNameText.getText() + "', '" + 
                        Double.parseDouble(totalCostText.getText()) + "')";

                        statement.executeUpdate(sql);
                        
                        //Clear ArrayList
                        soldItems.clear();
            
                    }
                    catch(SQLException error)
                    {
                        appendToFile(error);
                    }
                    
                    //show confirm  
                    JOptionPane.showMessageDialog(null, "Sales items added to the database",
                            "Transaction Added", WIDTH);
                }
        }
    }
    
    //private inner class for event handling
    private class SubmitButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
            keepGoing = true;
            if(inventoryIsIncomplete() == false)
            {
                checkProductInformation();//checks for input errors
                checkManufactureInformation();//checks for input errors
                if(keepGoing == true)
                {
                        //Check if user is ready to submit employee details
                    int response = JOptionPane.showConfirmDialog(null, 
                            "Are you sure you want to submit this form", 
                            "Create", JOptionPane.YES_NO_OPTION);

                       if (response == JOptionPane.YES_OPTION) 
                        {
                          try
                          {
                          JOptionPane.showMessageDialog(null, "Form Submitted", 
                                  "Confirmation", WIDTH);
                          //creates manufacture
                          Manufacturer test = new Manufacturer(manufacturerNameText.getText(),manufacturerAddressText.getText(),manufacturerPhoneText.getText(),Integer.parseInt(manufacturerIDText.getText())); 
                          //creates product
                          Product product = new Product(productDescriptionText.getText(),productNameText.getText(),Integer.parseInt(productNumberText.getText()),Double.parseDouble(productCostText.getText()),test);

                          //add to database
                          System.out.println(test);
                          System.out.println(product);
                          }
                          catch(HeadlessException | NumberFormatException error)
                          {
                            appendToFile(error);
                          }

                        }   
                  }
                }
            else
            {
                JOptionPane.showMessageDialog(null, "Please complete form before submitting",
                        "Input Error", WIDTH);
            }
            
        }
    }
    
    //Method to check if form is missing requirements
    private boolean inventoryIsIncomplete()
    {
        return productNameText.getText().equals("") || productNumberText.getText().equals("")
                || productDescriptionText.getText().equals("") || productCostText.getText().equals("")
                || manufacturerNameText.getText().equals("") || manufacturerAddressText.getText().equals("")
                || manufacturerPhoneText.getText().equals("") || manufacturerIDText.getText().equals("");
    }
    
    public void checkEmptyString(String example)
    {
        if(example.replaceAll("\\s","").isEmpty())
        {
            keepGoing = false;
            System.out.println("All fields required");
        }
    }
    
    public void checkProductInformation()
    {   
        checkEmptyString(productNameText.getText());
        checkEmptyString(productDescriptionText.getText());
        checkEmptyString(productCostText.getText());
        checkEmptyString(productNumberText.getText());
        
        try
            {
             checkDouble(Double.parseDouble(productCostText.getText()));               
            }
            catch(Exception e)
            {
               checkDouble("Product Cost",productCostText.getText());  
               appendToFile(e);
            }
        
        try
            {
             checkInt(Integer.parseInt(productNumberText.getText()));             
            }
            catch(Exception e)
            {
               checkInt("Product Number",productNumberText.getText());     
               appendToFile(e);
            }
    }
    
 
    public void checkManufactureInformation()
    {
        checkEmptyString(manufacturerNameText.getText());
        checkEmptyString(manufacturerAddressText.getText());
        checkEmptyString(manufacturerPhoneText.getText());
        checkEmptyString(manufacturerIDText.getText());
        
        try
            {
             checkInt(Integer.parseInt(manufacturerIDText.getText()));             
            }
            catch(Exception e)
            {
               checkInt("Manufacture ID",manufacturerIDText.getText());  
               appendToFile(e);
            }
    }
    
    //private inner class for event handling
    private class ProductComboBoxHandler implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            if (manufacturerBox.getSelectedIndex() == 0)
            {
                manufacturerIDText.setText("");
                manufacturerNameText.setText("");
                manufacturerAddressText.setText("");
                manufacturerPhoneText.setText("");
            }
            
            else if (manufacturerBox.getSelectedIndex() == 1)
            {
                //Set the textboxes 
                manufacturerIDText.setText(Integer.toString(manufacturerList.get(0).getIDNumber()));
                manufacturerNameText.setText(manufacturerList.get(0).getCompanyName());
                manufacturerAddressText.setText(manufacturerList.get(0).getAddress());
                manufacturerPhoneText.setText(manufacturerList.get(0).getPhoneNumber());
            }
            
            else if (manufacturerBox.getSelectedIndex() == 2)
            {
                //Set the textboxes 
                manufacturerIDText.setText(Integer.toString(manufacturerList.get(1).getIDNumber()));
                manufacturerNameText.setText(manufacturerList.get(1).getCompanyName());
                manufacturerAddressText.setText(manufacturerList.get(1).getAddress());
                manufacturerPhoneText.setText(manufacturerList.get(1).getPhoneNumber());
            }
        }
    }
    
    //private inner class for event handling
    private class CreateButtonListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            //reset boolean
            keepGoing = true; 
            //checks what type of employee, and the requirements for that type of employee            
            if(employeeIndex==0)
            {
            checkHourlyEmployeeInformation();
            }
            if(employeeIndex==1)
            {
            checkSalaryEmployeeInformation();   
            }
            if(employeeIndex == 2)
            {
            checkCommissionEmployeeInformation();
            }
            if(employeeIndex == 3)
            {
            checkSalaryPlusCommissionEmployeeInformation();  
            }
            //if all required fields are there, continue    
            if(keepGoing == true)
            {
                if(employeeIndex == 0)//hourly employee
                {
                    createOptionPane();
                    
                    if(response == JOptionPane.YES_OPTION)
                    {
                        employeeAfterCreatedMessage();
                        
                        //creates a HourlyEmployee
                        HourlyEmployee test = new HourlyEmployee(firstNameText.getText(),lastNameText.getText(),
                                genderText.getText(),addressText.getText(),phoneNumberText.getText(),
                                Integer.parseInt(sinText.getText()),Integer.parseInt(yearText.getText()),Integer.parseInt(monthText.getText()),
                                Integer.parseInt(dayText.getText()),positionText.getText(),statusText.getText(),departmentText.getText(),
                                Integer.parseInt(idNumberText.getText()),Double.parseDouble(hourlyRateText.getText()));
                        
                        JOptionPane.showMessageDialog(null, test.toString(), "Employee Created", WIDTH);
                        
                        //put in db
                        addEmployeeToDB(test);
                        
                        //clears textfields
                        clearTextBoxes();
                    }
                    else
                    {
                        clearOptionPane();
                        if(response2 == JOptionPane.YES_OPTION)
                        {
                            clearTextBoxes();
                        }
                        else
                        {
                            //nothing
                        }
                    }
                }
                
                if(employeeIndex == 1)//salary employee
                {
                    createOptionPane();
                    if(response == JOptionPane.YES_OPTION)
                    {
                        employeeAfterCreatedMessage();
                    
                        SalaryEmployee test = new SalaryEmployee(firstNameText.getText(),lastNameText.getText(),
                                genderText.getText(),addressText.getText(),phoneNumberText.getText(),
                                Integer.parseInt(sinText.getText()),Integer.parseInt(yearText.getText()),Integer.parseInt(monthText.getText()),
                                Integer.parseInt(dayText.getText()),positionText.getText(),statusText.getText(),departmentText.getText(),
                                Integer.parseInt(idNumberText.getText()),Double.parseDouble(baseSalaryText.getText()));
                        
                        JOptionPane.showMessageDialog(null, test.toString(), "Employee Created", WIDTH);
                  
                        addEmployeeToDB(test);
                        
                        clearTextBoxes();
                    }
                    else
                    {
                        clearOptionPane();
                        if(response2 == JOptionPane.YES_OPTION)
                        {
                            clearTextBoxes();
                        }
                        
                        
                    }
                }
                
                 if(employeeIndex == 2)//commission employee
                {
                    createOptionPane();
                    if(response == JOptionPane.YES_OPTION)
                    {
                        employeeAfterCreatedMessage();
                    
                        CommissionSalesEmployee test = new CommissionSalesEmployee(firstNameText.getText(),lastNameText.getText(),
                                genderText.getText(),addressText.getText(),phoneNumberText.getText(),
                                Integer.parseInt(sinText.getText()),Integer.parseInt(yearText.getText()),Integer.parseInt(monthText.getText()),
                                Integer.parseInt(dayText.getText()),positionText.getText(),statusText.getText(),departmentText.getText(),
                                Integer.parseInt(idNumberText.getText()),Double.parseDouble(commissionRateText.getText()));
                        
                        JOptionPane.showMessageDialog(null, test.toString(), "Employee Created", WIDTH);
                        
                        addEmployeeToDB(test);
                                    
                        clearTextBoxes();
                    }
                     else
                    {
                       clearOptionPane();
                        if(response2 == JOptionPane.YES_OPTION)
                        {
                            clearTextBoxes();
                        }
                        else
                        {
                            //nothing
                        }
                    }
                }
                 
                if(employeeIndex == 3)//commission plus salary employee
                {
                    createOptionPane();
                    if(response == JOptionPane.YES_OPTION)
                    {
                        employeeAfterCreatedMessage();
                    
                        BasePlusCommissionEmployee test = new BasePlusCommissionEmployee(firstNameText.getText(),lastNameText.getText(),
                                genderText.getText(),addressText.getText(),phoneNumberText.getText(),
                                Integer.parseInt(sinText.getText()),Integer.parseInt(yearText.getText()),Integer.parseInt(monthText.getText()),
                                Integer.parseInt(dayText.getText()),positionText.getText(),statusText.getText(),departmentText.getText(),
                                Integer.parseInt(idNumberText.getText()),Double.parseDouble(commissionRateText.getText()),Double.parseDouble(baseSalaryText.getText()));
                        
                        addEmployeeToDB(test);
                  
                        clearTextBoxes();
                    }
                     else
                    {
                        clearOptionPane();
                        if(response2 == JOptionPane.YES_OPTION)
                        {
                            clearTextBoxes();
                        }
                        else
                        {
                            //nothing
                        }
                    }
                }  
            }           
        }
    }
    
    // asks user if they want to clear 
    public void clearOptionPane()
    {
        int response = JOptionPane.showConfirmDialog(null, 
                    "Do you want to clear the textfields?", 
                    "Clear", JOptionPane.YES_NO_OPTION);
    }
    
    // ask user if they want to create employee
    public void createOptionPane()
    {
        String employee="";
        
        if(employeeIndex == 0)
        {
            employee="hourly";
        }
        if(employeeIndex == 1)
        {
            employee="salary";
        }
        if(employeeIndex == 2)
        {
            employee="commission";
        }
        if(employeeIndex == 3)
        {
            employee="salary and commission";
        }
        response = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to create a "+employee+" employee?", 
                    "Create", JOptionPane.YES_NO_OPTION);
    }
    
    // tells user that employee was created
    public void employeeAfterCreatedMessage()
    {   
              JOptionPane.showMessageDialog(null, "Employee Created", 
                      "Confirmation", WIDTH);     
    }
    
    private void clearTextBoxes()
    {
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
    }
    
    private void checkCommissionEmployeeInformation()
    {
        checkEmployeeInformation();
        checkEmployeePositionInformation();
        if(commissionRateText.getText().replaceAll("\\s","").isEmpty())
        {
            keepGoing = false;
        }
          try
            {
                checkDouble(Double.parseDouble(commissionRateText.getText()));               
            }
            catch(Exception e)
            {
               checkDouble("Commission Rate",commissionRateText.getText()); 
               appendToFile(e);
            }
    }
    
    private void checkSalaryEmployeeInformation()
    {
        checkEmployeeInformation();
        checkEmployeePositionInformation();
        if(baseSalaryText.getText().replaceAll("\\s","").isEmpty())
        {
            keepGoing = false;
        }
          try
            {
                checkDouble(Double.parseDouble(baseSalaryText.getText()));               
            }
            catch(Exception e)
            {
               checkDouble("Salary",baseSalaryText.getText());    
               appendToFile(e);
            }
    }
    
    private void checkSalaryPlusCommissionEmployeeInformation()
    {
        checkEmployeeInformation();
        checkEmployeePositionInformation();
        if(baseSalaryText.getText().replaceAll("\\s","").isEmpty())
        {
            keepGoing = false;
        }
        if(commissionRateText.getText().replaceAll("\\s","").isEmpty())
        {
            keepGoing = false;
        }
        
        try
            {
                checkDouble(Double.parseDouble(baseSalaryText.getText()));               
            }
            catch(Exception e)
            {
               checkDouble("Salary",baseSalaryText.getText()); 
               appendToFile(e);
            }
        
        try
            {
                checkDouble(Double.parseDouble(commissionRateText.getText()));               
            }
            catch(Exception e)
            {
               checkDouble("Commission Rate",commissionRateText.getText()); 
               appendToFile(e);
            }
    }
    
    
    
    private void checkHourlyEmployeeInformation()
    {
        checkEmployeeInformation();
        checkEmployeePositionInformation();
        if(hourlyRateText.getText().replaceAll("\\s","").isEmpty())
        {
            keepGoing = false;
        }
            try
            {
                checkDouble(Double.parseDouble(hourlyRateText.getText()));               
            }
            catch(Exception e)
            {
               checkDouble("Hourly Rate",hourlyRateText.getText());   
               appendToFile(e);
            }
    }
    
    
    
    // checks to see if employee position information isn't empty when white space removed
    private void checkEmployeePositionInformation()
    {
      
        checkEmptyString(positionText.getText());
        checkEmptyString(statusText.getText());
        checkEmptyString(departmentText.getText());
        checkEmptyString(idNumberText.getText());
        
            try
            {
                checkInt(Integer.parseInt(idNumberText.getText()));               
            }
            catch(Exception e)
            {
               checkInt("ID Number",idNumberText.getText());     
               appendToFile(e);
            }
        
    }
    
    /*
    * Removes whitespace from textfield, then checks if they are empty
    * if empty, keepgoing is false and employee won't be created
    */
    private void checkEmployeeInformation()
    {
            
        checkEmptyString(firstNameText.getText());  
        checkEmptyString(lastNameText.getText());
        checkEmptyString(genderText.getText());
        checkEmptyString(addressText.getText());
        checkEmptyString(phoneNumberText.getText());
        checkEmptyString(sinText.getText());
        checkEmptyString(yearText.getText());
        checkEmptyString(monthText.getText());
        checkEmptyString(dayText.getText());
        
            //try to parse text into int, catch exception
            try
            {
                checkInt(Integer.parseInt(sinText.getText()));               
            }
            catch(Exception e)
            {
               checkInt("sin",sinText.getText()); 
               appendToFile(e);
            }
            
            try
            {
                checkInt(Integer.parseInt(dayText.getText()));               
            }
            catch(Exception e)
            {
               checkInt("day",dayText.getText()); 
               appendToFile(e);
            }
             
            try
            {
                checkInt(Integer.parseInt(monthText.getText()));               
            }
            catch(Exception e)
            {
               checkInt("month",monthText.getText()); 
               appendToFile(e);
            }
              
            try
            {
               checkInt(Integer.parseInt(yearText.getText()));               
            }
            catch(Exception e)
            {
               checkInt("year",yearText.getText()); 
               appendToFile(e);
            }        
    }
    
    //empty method
    public void checkInt(int example)
    {
        //Success
    }
    //if user puts a String, they will be told to enter a int
    public void checkInt(String textbox,String example)
    {
        keepGoing = false;
        System.out.println("Please only enter a numberic value for "+textbox+" You entered " + example);
    }
    //empty method
    public void checkDouble(double example)
    {
        //Success
    }
    //if user enters a string, they will be told to enter a double
    public void checkDouble(String textbox,String example)
    {
        keepGoing = false;
        System.out.println("Please only enter a numberic value for "+textbox+" You entered "+example);
    }
    
     public void checkDirectory()
    {
        try
        {

            String directoryName = "D:/FinalProject/Error";
            File directory = new File("D:/FinalProject/Error");

            if (!directory.exists())
            {
                System.out.println("Creating directory: " + directoryName);
                boolean exists = false;

                try
                {
                    directory.mkdirs();
                    exists = true;
                    System.out.println("Directory created.");
                }
                catch(SecurityException error)
                {
                    //handle error
                    appendToFile(error);
                }
            }
            else
            {
                System.out.println("Exists.");
            }
        }
        catch(SecurityException error)
        {
            appendToFile(error);
        }
        catch(Exception error)
        {
            appendToFile(error);
        }
        
    }
    
    //Create a method that appends data to a file
    public void appendToFile(Exception exceptionError)
    {
        checkDirectory();
        
        try
        {
            File file = new File("D:/FinalProject/Error/errorLog.txt");
            String message = exceptionError.toString();
            String ioDate = "Date: " + today.getTime();
            String ioLabel = "Error Description:";
            String ioError = message;

            if (!file.exists())
            {
                System.out.println("No such file.");
                file.createNewFile();
            }

            FileWriter fileWriter = 
                    new FileWriter("D:/FinalProject/Error/errorLog.txt", true);
            

            bufferWriter = new BufferedWriter(fileWriter);
            
            bufferWriter.newLine();
            bufferWriter.write(ioDate);
            bufferWriter.newLine();
            bufferWriter.write(ioLabel);
            bufferWriter.newLine();
            bufferWriter.write(ioError);
            bufferWriter.newLine();

            bufferWriter.close();
            
            
        }
        catch(IOException | SecurityException error)
        {
            //Handle exception
            
        }

    }
    
    //method to add employee to db
    private void addCustomerToDB(Customer customer)
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = conn.createStatement();
            
                        
            String sql = "INSERT INTO customers VALUES ('" + 
                    customer.getFirstName() + "', '" +
                    customer.getLastName() + "', '" + 
                    customer.getAddress() + "', '" +
                    customer.getPhoneNumber() + "', '" +
                    customer.getCity() + "', '" +
                    customer.getPostalCode() + "')";

            statement.executeUpdate(sql);   
            
            //show confirm  
            JOptionPane.showMessageDialog(null, "Customer: " + customer.getFirstName() 
                    + " " + customer.getLastName() + " has been added to the database.",
                "Customer Added", WIDTH);
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            
            appendToFile(error);
        }
    }
    
    //method to add employee to db
    private void addEmployeeToDB(Employee employee)
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = conn.createStatement();
            
                        
            String sql = "INSERT INTO employees VALUES ('" + 
                    employee.getFirstName() + "', '" + 
                    employee.getLastName() + "', '" +
                    employee.getGender() + "', '" +
                    employee.getAddress() + "', '" +
                    employee.getPhoneNumber() + "', '" +
                    employee.getSIN() + "', '" +
                    java.sql.Date.valueOf(employee.getDateOfBirth()) + "', '" +
                    employee.getPosition() + "', '" +
                    employee.getEmployeeStatus() + "', '" +
                    employee.getDepartment() + "', '" +
                    employee.getIDNumber() + "')";

            statement.executeUpdate(sql);   
            
            //show confirm  
            JOptionPane.showMessageDialog(null, "Employee: " + employee.getFullName() 
                    + " has been added to the database.",
                "Employee Added", WIDTH);
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            
            appendToFile(error);
        }
    }
    
    //method to search db for employee
    private void searchEmployeeDB(String firstName, String lastName)
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            
            appendToFile(error);
        }
        
        try
        {
            //If connection was successful
            if (conn != null)
            {
                statement = conn.createStatement();
                        
                String sql;
                if ("".equals(firstName) && "".equals(lastName))
                {
                    sql = "SELECT * FROM employees";
                }
                else
                {
                    sql = "SELECT * FROM employees WHERE firstName = '" + 
                        firstName + "' AND lastName = '" + lastName + "'";
                }

                result = statement.executeQuery(sql);  

                //show confirm  
                JOptionPane.showMessageDialog(null, "Searching the database... ",
                    "Search", WIDTH);
                
                //Add data to the table
                ResultSetMetaData metaData = result.getMetaData();
        
                //get the column names and store into vector
                Vector<String> columnNames = new Vector<String>();
                //column count
                int columnCount = metaData.getColumnCount();
                //loop to build the column names
                for(int i=1; i<=columnCount;i++)
                {
                    columnNames.add(metaData.getColumnName(i));
                }
                //Create the vector to hold the data(Vectors of Vectors)
                //this vector all rows
                Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
                
                if("".equals(firstName) && "".equals(lastName))
                {
                    while(result.next())
                    {
                        //this will store each row
                        Vector<Object> rowVector =  new Vector<Object>();
                        //loop through the resultset and get each object/row
                        for(int colIndex = 1; colIndex<=columnCount;colIndex++)
                        {
                            rowVector.add(result.getObject(colIndex));
                        }
                        tableData.add(rowVector);
                    }
                }
                
                else 
                {
                    //go through the resultset
                    if(result.next())
                    {
                        //this will store each row
                        Vector<Object> rowVector =  new Vector<Object>();
                        //loop through the resultset and get each object/row
                        for(int colIndex = 1; colIndex<=columnCount;colIndex++)
                        {
                            rowVector.add(result.getObject(colIndex));
                        }
                        tableData.add(rowVector);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No results were found",
                                "Not Found", WIDTH);
                    }
                }
          
                //display the data into a JTabel
                JTable tempTable = new JTable(tableData, columnNames);
                
                //Remove empty scrollPane and revalidate
                searchPanelBottom.remove(scrollPane);
                searchPanelBottom.revalidate();
                searchPanelBottom.repaint();
                
                //Set scrollPane = new JScrollPane(tempTable) and add
                scrollPane = new JScrollPane(tempTable);
                scrollPane.setPreferredSize(tableSize);
                searchPanelBottom.add(scrollPane);            
            }  
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            appendToFile(error);
        }
    }
    
    //Method to search for product
    private void searchProductDB(String productName, String productNumber)
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            
            appendToFile(error);
        }
        
        try
        {
            //If connection was successful
            if (conn != null)
            {
                statement = conn.createStatement();
                        
                String sql;
                if ("".equals(productName) && "".equals(productNumber))
                {
                    sql = "SELECT * FROM products";
                }
                else
                {
                    sql = "SELECT * FROM products WHERE productName = '" + 
                        productName + "' AND productNumber = '" + Integer.parseInt(productNumber) + "'";
                }

                result = statement.executeQuery(sql);  

                //show confirm  
                JOptionPane.showMessageDialog(null, "Searching the database... ",
                    "Search", WIDTH);
                
                //Add data to the table
                ResultSetMetaData metaData = result.getMetaData();
        
                //get the column names and store into vector
                Vector<String> columnNames = new Vector<String>();
                //column count
                int columnCount = metaData.getColumnCount();
                //loop to build the column names
                for(int i=1; i<=columnCount;i++)
                {
                    columnNames.add(metaData.getColumnName(i));
                }
                //Create the vector to hold the data(Vectors of Vectors)
                //this vector all rows
                Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();

                if ("".equals(productName) && "".equals(productNumber))
                {
                    while(result.next())
                    {
                        //this will store each row
                        Vector<Object> rowVector =  new Vector<Object>();
                        //loop through the resultset and get each object/row
                        for(int colIndex = 1; colIndex<=columnCount;colIndex++)
                        {
                            rowVector.add(result.getObject(colIndex));
                        }
                        tableData.add(rowVector);
                    }
                }
                else
                {
                    //go through the resultset
                    if(result.next())
                    {
                        //this will store each row
                        Vector<Object> rowVector =  new Vector<Object>();
                        //loop through the resultset and get each object/row
                        for(int colIndex = 1; colIndex<=columnCount;colIndex++)
                        {
                            rowVector.add(result.getObject(colIndex));
                        }
                        tableData.add(rowVector);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No results were found",
                                "Not Found", WIDTH);
                    }
                }
                
                //display the data into a JTabel
                JTable tempTable = new JTable(tableData, columnNames);
                
                //Remove empty scrollPane and revalidate
                searchPanelBottom.remove(scrollPane);
                searchPanelBottom.revalidate();
                searchPanelBottom.repaint();
                
                //Set scrollPane = new JScrollPane(tempTable) and add
                scrollPane = new JScrollPane(tempTable);
                scrollPane.setPreferredSize(tableSize);
                searchPanelBottom.add(scrollPane);            
            }  
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred", 
                    "Error", WIDTH);
            appendToFile(error);
        }
    }
  
    //Method to clear search boxes
    private void clearSearchBoxes()
    {
        //Clear all search Boxes
        searchFirstNameText.setText("");
        searchLastNameText.setText("");
        searchProductNameText.setText("");
        searchProductNumberText.setText("");

    }
    
    //Method to clear JTable results
    private void clearTable()
    {
        //Clear table
        //Remove empty scrollPane and revalidate
        searchPanelBottom.remove(scrollPane);
        searchPanelBottom.revalidate();
        searchPanelBottom.repaint();
                
        //Set scrollPane = new JScrollPane(tempTable) and add
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(tableSize);
        searchPanelBottom.add(scrollPane);
    }
    
    //main
    public static void main(String[] args)
    {   
        MainGUI gui = new MainGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(false);
        
        //Set Location of JFrame to center
        gui.setLocationRelativeTo(null);
        
    }            
}
