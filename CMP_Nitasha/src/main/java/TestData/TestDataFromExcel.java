package TestData;


import utilities.ExcelRead;
import Base.TestBase;
import java.util.ArrayList;

public class TestDataFromExcel
{
    private String userName;
    private String password;
    private ArrayList<ArrayList<String>> credDetails;
    private ArrayList<ArrayList<String>> userDetails;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String region;
    private String companyName;
    private String primaryContact;
    private String salesManager;
    private String salesEngineer;
    private String outOfSurvey;
    private String dataExtractMsg;
    private String enableMsgTest;
    
    public TestDataFromExcel() {
        this.userName = null;
        this.password = null;
        this.credDetails = null;
        this.userDetails = null;
        this.credDetails = new ArrayList<ArrayList<String>>();
    }
    
    public void setCredentials(final int rowNo) {
        this.credDetails = (ArrayList<ArrayList<String>>)ExcelRead.excelRead(TestBase.prop.getProperty("excelPath"), "Cred");
        this.userName = this.credDetails.get(rowNo).get(0);
        this.password = this.credDetails.get(rowNo).get(1);
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public ArrayList<ArrayList<String>> readUserDetailsFromExcel() {
        this.userDetails = new ArrayList<ArrayList<String>>();
        return this.userDetails = (ArrayList<ArrayList<String>>)ExcelRead.excelRead(TestBase.prop.getProperty("excelPath"), "UserDetails");
    }
    
    public void setNewUserDetails(final ArrayList<String> userData) {
        this.role = userData.get(0);
        this.firstName = userData.get(1);
        this.lastName = userData.get(2);
        this.email = userData.get(3);
        this.phoneNo = userData.get(4);
        this.region = userData.get(5);
        if (userData.size() > 6) {
            if (!userData.get(6).isEmpty()) {
                this.companyName = userData.get(6);
            }
            if (!userData.get(7).isEmpty()) {
                this.primaryContact = userData.get(7);
            }
            if (!userData.get(8).isEmpty()) {
                this.salesManager = userData.get(8);
            }
            if (!userData.get(9).isEmpty()) {
                this.salesEngineer = userData.get(9);
            }
            if (!userData.get(10).isEmpty()) {
                this.outOfSurvey = userData.get(10);
            }
            if (!userData.get(11).isEmpty()) {
                this.dataExtractMsg = userData.get(11);
            }
            if (!userData.get(12).isEmpty()) {
                this.enableMsgTest = userData.get(12);
            }
        }
    }
    
    public String getRole() {
        return this.role;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPhoneNo() {
        return this.phoneNo;
    }
    
    public String getRegion() {
        return this.region;
    }
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public String getPrimaryContact() {
        return this.primaryContact;
    }
    
    public String getSurveyOption() {
        return this.outOfSurvey;
    }
    
    public String getDataExtractOption() {
        return this.dataExtractMsg;
    }
    
    public String getEnableMessageTestOption() {
        return this.enableMsgTest;
    }
    
    public String getSalesManager() {
        return this.salesManager;
    }
    
    public String getSalesEngineer() {
        return this.salesEngineer;
    }
    
    public int getUserDetailsListSize() {
        return this.userDetails.get(1).size();
    }
}
