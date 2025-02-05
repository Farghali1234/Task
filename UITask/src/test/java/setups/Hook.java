package setups;

import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hook{
    @Before
    public void before() throws Exception {
        System.setProperty("browser", "chrome"); //Could be Firefox also as requested for the task
        BrowserSetup.startWebDriver();
    }

    @After
    public void after() throws Exception {
        BrowserSetup.getWebDriver().quit();
    }
}
