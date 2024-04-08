package Railway;
import Constant.Constants;

public class HomePage extends GeneralPage {
    // Locators
    // Elements
    // Methods

    public HomePage open() {
        Constants.WEBDRIVER.navigate().to(Constants.RAILWAY_URL);
        return this;
    }
}
