package commons;

public class GlobalConstants {
	public static final String PROJECT_LOCATION = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String DEV_EVR_URL = "dev";
	public static final String TEST_EVR_URL = "test";
	public static final String STAGING_EVR_URL = "staging"; 
	public static final String UPLOAD_FOLDER_LOCATION = PROJECT_LOCATION + "\\uploadFile";
	public static final String DOWNLOAD_FOLDER_LOCATION = PROJECT_LOCATION + "\\downloadFile";
	public static final long LONG_TIMEOUT= 30;
	public static final long SHORT_TIMEOUT = 5;
	public static final String DB_URL = "";
	public static final String DB_NAME = "automationfc";
	public static final String DB_USERNAME = "admin";
	public static final String DB_PASSWORD = "admin";
}

