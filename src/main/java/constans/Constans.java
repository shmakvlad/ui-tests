package constans;

public class Constans {

    public static class Drivers{
        public static String FIREFOX = System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        public static String GOOGLE = System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    }

}
