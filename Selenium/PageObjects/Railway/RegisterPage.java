package Railway;
import Constant.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class RegisterPage extends GeneralPage{
//    Locators
    private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtPID = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
    private final By _lblPasswordErrorMsg = By.xpath("//label[@for='password']");
    private final By _lblPIPErrorMsg = By.xpath("//label[@for='pid']");
    private final By _lblThanksRegister = By.xpath("//div[@id='content']/p");



    //    Elements
    public WebElement getLblThanksRegister() {
        return Constants.WEBDRIVER.findElement(_lblThanksRegister);
    }
    public WebElement getLblRegisterErrorMsg() {
        return Constants.WEBDRIVER.findElement(_lblRegisterErrorMsg);
    }
    public WebElement getlblPasswordErrorMsg() {
        return Constants.WEBDRIVER.findElement(_lblPasswordErrorMsg);
    }
    public WebElement getlblPIPErrorMsg() {
        return Constants.WEBDRIVER.findElement(_lblPIPErrorMsg);
    }
    public WebElement getTxtEmail() {
        return Constants.WEBDRIVER.findElement(_txtEmail);
    }
    public WebElement getTxtPassword() {
        return Constants.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getTxtConfirmPassword() {
        return Constants.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getTxtPID() {
        return Constants.WEBDRIVER.findElement(_txtPID);
    }
    public WebElement getBtnRegister() {
        return Constants.WEBDRIVER.findElement(_btnRegister);
    }


    //    Methods
    public HomePage register(String email, String password, String confirmpassword, String PID){
        this.getTxtEmail().sendKeys(email);
        this.getTxtPassword().sendKeys(password);
        this.getTxtConfirmPassword().sendKeys(confirmpassword);
        this.getTxtPID().sendKeys(PID);
        this.getBtnRegister().submit();

        // Land on Home page
        return new HomePage();
    }

//    Methods


}
