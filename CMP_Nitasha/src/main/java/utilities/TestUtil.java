package utilities;


import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

public class TestUtil
{
    public static long PAGE_LOAD_TIMEOUT;
    public static long IMPLICIT_WAIT;
    public static String LOGIN_PAGE_TITLE;
    public static String SAP_COMMUNITY_BOARD_LABEL;
    public static int LONG_WAIT;
    public static String CMP_ADMIN_EMAIL;
    public static String SALESFORCE_ADMIN_EMAIL;
    public static String STANDARD_PSSWRD;
    public static String EMTT_ADMIN_EMAIL;
    public static String SALES_USER_EMAIL;
    public static String EMTT_SALES_USER_EMAIL;
    public static String EMTT_CUST_ADMIN_EMAIL;
    public static String SERVICE_USER_EMAIL;
    public static String SALES_SPRT_USER_EMAIL;
    public static String CUST_ADMIN_USER_EMAIL;
    public static String CUST_SUPPORT_USER_EMAIL;
    public static String CUST_ADMIN;
    public static String EMTT_CUST_ADMIN;
    public static String CUST_SUPP;
    public static String EMTT_CUST_SUPP;
    public static String NO_CUST_SUPP_MSG;
    public static String NO_EMTT_CUST_SUPP_MSG;
    public static ArrayList<String> EMTT_CUST_ADMIN_WAYS;
    public static ArrayList<String> CUST_ADMIN_WAYS;
    public static String SEARCH_BY_FIRST_NAME;
    public static String SEARCH_BY_LAST_NAME;
    public static String SEARCH_BY_EMAIL;
    public static String SEARCH_BY_PHONE_NO;
    public static String SEARCH_BY_COMPANY_NAME;
    public static String SEARCH_BY_ROLE;
    public static String EMTT_OPT_SURVEY_OPTION;
    public static String EMTT_DATA_EXTRACTION_OPTION;
    public static String EMTT_ENABLE_TEST_OPTION;
    public static String EMTT_SALES_USER;
    public static String EMTT_SERVICE_DESK_USER;
    public static String LABEL_ATTRIBUTE;
    public static ArrayList<String> ALL_USERS_FOR_CMP_ADMIN;
    public static String PUBLIC_EMAIL_ID_ERROR;
    public static String LOAD_BALANCER_URL;
    
    static {
        TestUtil.PAGE_LOAD_TIMEOUT = 20L;
        TestUtil.IMPLICIT_WAIT = 20L;
        TestUtil.LOGIN_PAGE_TITLE = "CMP Login";
        TestUtil.SAP_COMMUNITY_BOARD_LABEL = "SAP Digital Interconnect Community Board";
        TestUtil.LONG_WAIT = 200;
        TestUtil.CMP_ADMIN_EMAIL = "cmp.admin@sap.com";
        TestUtil.SALESFORCE_ADMIN_EMAIL = "salesforce.admin@sap.com";
        TestUtil.STANDARD_PSSWRD = "welcome@1";
        TestUtil.EMTT_ADMIN_EMAIL = "emt.admin@sap.com";
        TestUtil.SALES_USER_EMAIL = "anmolgrover357@sap.com";
        TestUtil.EMTT_SALES_USER_EMAIL = "swathisales@sap.com";
        TestUtil.EMTT_CUST_ADMIN_EMAIL = "EMTTCustAdminForFrenchByEMTTAdmin@mkoiki.com";
        TestUtil.SERVICE_USER_EMAIL = "saleuser1@sap.com";
        TestUtil.SALES_SPRT_USER_EMAIL = "himanip@sap.com";
        TestUtil.CUST_ADMIN_USER_EMAIL = "newcustomeradmin@bosh.com";
        TestUtil.CUST_SUPPORT_USER_EMAIL = "anmolgrover@bosh.com";
        TestUtil.CUST_ADMIN = "Customer Administrator";
        TestUtil.EMTT_CUST_ADMIN = "EMTT Customer Administrator";
        TestUtil.CUST_SUPP = "Customer Support";
        TestUtil.EMTT_CUST_SUPP = "EMTT Customer Support";
        TestUtil.NO_CUST_SUPP_MSG = "There are no active CMP Customer Support Users that could be changed to Customer Administrator.";
        TestUtil.NO_EMTT_CUST_SUPP_MSG = "There are no active EMTT Customer Support Users that could be changed to EMTT Customer Administrator.";
        TestUtil.EMTT_CUST_ADMIN_WAYS = new ArrayList<String>(Arrays.asList("emtt cust admin", "emtt customer admin", "emtt customer administrator"));
        TestUtil.CUST_ADMIN_WAYS = new ArrayList<String>(Arrays.asList("cust admin", "customer admin", "customer administrator"));
        TestUtil.SEARCH_BY_FIRST_NAME = "first name";
        TestUtil.SEARCH_BY_LAST_NAME = "last name";
        TestUtil.SEARCH_BY_EMAIL = "email";
        TestUtil.SEARCH_BY_PHONE_NO = "phone no";
        TestUtil.SEARCH_BY_COMPANY_NAME = "company name";
        TestUtil.SEARCH_BY_ROLE = "role";
        TestUtil.EMTT_OPT_SURVEY_OPTION = "Opt out of Survey";
        TestUtil.EMTT_DATA_EXTRACTION_OPTION = "Data extract message text option";
        TestUtil.EMTT_ENABLE_TEST_OPTION = "Enable Message Test Option";
        TestUtil.EMTT_SALES_USER = "emtt sales";
        TestUtil.EMTT_SERVICE_DESK_USER = "emtt service";
        TestUtil.LABEL_ATTRIBUTE = "label";
        TestUtil.ALL_USERS_FOR_CMP_ADMIN = new ArrayList<String>(Arrays.asList("Sales", "Sales Support", "Service", "Customer Administrator", "EMTT Sales", "EMTT Service Desk", "EMTT Customer Administrator", "SDTool User"));
        TestUtil.PUBLIC_EMAIL_ID_ERROR = "No public email allowed.";
        TestUtil.LOAD_BALANCER_URL = "https://cmptest-cp.sapdigitalinterconnect.com/SAPCMP/login";
    }
}
