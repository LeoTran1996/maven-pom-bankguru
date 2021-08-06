package pageUI;

public class CustomerPageUI {

	public static final String CREATED_NEW_CUS_SUCCESS_MESSAGE = "//p[text()='Customer Registered Successfully!!!']";
	public static final String CUSTOMER_ID = "//td[text()='Customer ID']/following-sibling::td";
	public static final String ACCOUNT_ID = "//td[text()='Account ID']/following-sibling::td";
	public static final String CUSTOMER_ID_AT_EDIT_ACCOUNT_FORM = "//input[@name='txtcid']";
	public static final String BALANCE_AT_EDIT_ACCOUNT_FORM = "//input[@name='txtinitdep']";
	public static final String MINI_STATE_MESSAGE = "//p[contains(text(),'Last Five Transaction Details')]";
	public static final String CUSTOMISED_STATEMENT_MESSAGE = "//p[contains(text(),'Transaction Details for Account No')]";
	public static final String DYNAMIC_INFORMATION_BY_TEXT = "//td[text()='%s']/following-sibling::td";
	public static final String EDITED_CUS_SUCCESS_MESSAGE = "//p[text()='Customer details updated Successfully!!!']";
	public static final String TRANSFER_SUCCESS_MESSAGE = "//p[@class='heading3']";
	public static final String TYPE_OF_ACCOUNT_DROPDOWN = "//select[@name='a_type']";
	public static final String BALANCE_CHECK_SUCCESS = "//p[@class='heading3']";

}
