package LoginSettings;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {
	private static Properties propertiesSetting = null;



	

	private static String settingURL;
	
	

	private static String settingENV;
	
	private static String sURL;
	private static String sEmail;
	private static String sPwd;
	private static String ScreenshotPath;
	
	


	
	public static  void read() throws Exception {
		if (propertiesSetting == null) {
			propertiesSetting = new Properties();
			propertiesSetting.load(new FileInputStream("File.properties"));

			
			
			
			settingURL = propertiesSetting.getProperty("URL");
			settingENV = propertiesSetting.getProperty("ENV");
			
			sURL= propertiesSetting.getProperty("sURL");
			
			ScreenshotPath= propertiesSetting.getProperty("ScreenshotPath");
			
		}
	}
	
	
	
	public static String getPassword() {
		return sPwd;
	}

	public static void setPassword(String password) {
		sPwd = password;
	}
	
	public static String getUserName() {
		return sEmail;
	}

	public static void setUserName(String userName) {
		sEmail = userName;
	}
	
	


	public static String getsURL() {
		return sURL;
	}

	public static void setsURL(String sURL) {
		Settings.sURL = sURL;
	}

	

	

	

	public static Properties getPropertiesSetting() {
		return propertiesSetting;
	}

	public static void setPropertiesSetting(Properties propertiesSetting) {
		Settings.propertiesSetting = propertiesSetting;
	}

	

	

	

	

	public static String getSettingENV() {
		return settingENV;
	}

	public static void setSettingENV(String settingENV) {
		Settings.settingENV = settingENV;
	}

	public static String getScreenshotPath() {
		return ScreenshotPath;
	}

	public static void setScreenshotPath(String screenshotPath) {
		ScreenshotPath = screenshotPath;
	}



	

	

	

	

	

	

	


}