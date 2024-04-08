package Railway;
import Constant.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;


public class LoginPage extends GeneralPage {
//    Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='Login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _txtCurrentPassword = By.xpath("//input[@id='currentPassword']");
    private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    private final By _lblChangePasswordErrorMsg = By.xpath("//p[@class='message success']");
    private final By _linkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
    private final By _txtEmailAddress = By.xpath("//input[@title='Email address']");
    private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
    public final By _tabForgotPW = By.xpath("//*[@id='content']/ul/li[3]/a");



    //    Elements
    public WebElement getBtnSendInstructions() { return Constants.WEBDRIVER.findElement(_btnSendInstructions); }
    public WebElement getTxtEmailAddress() { return Constants.WEBDRIVER.findElement(_txtEmailAddress); }
    public WebElement getLinkForgotPassword() { return Constants.WEBDRIVER.findElement(_linkForgotPassword); }
    public WebElement getLblChangePasswordErrorMsg() { return Constants.WEBDRIVER.findElement(_lblChangePasswordErrorMsg); }
    public WebElement getBtnChangePassword() {
        return Constants.WEBDRIVER.findElement(_btnChangePassword);
    }
    public WebElement getTxtCurrentPassword() {
        return Constants.WEBDRIVER.findElement(_txtCurrentPassword);
    }
    public WebElement getTxtNewPassword() {
        return Constants.WEBDRIVER.findElement(_txtNewPassword);
    }
    public WebElement getTxtConfirmPassword() {
        return Constants.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getTxtUsername() {
        return Constants.WEBDRIVER.findElement(_txtUsername);
    }
    public WebElement getTxtPassword() {
        return Constants.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getBtnLogin() {
        return Constants.WEBDRIVER.findElement(_btnLogin);
    }
    public WebElement getLblLoginErrorMsg() { return Constants.WEBDRIVER.findElement(_lblLoginErrorMsg); }
    public WebElement getforgotpw(){
        return Constants.WEBDRIVER.findElement(_tabForgotPW);
    }

//    Methods
    public HomePage login (String username, String password){
        // Submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();

        // Land on Home page
        return new HomePage();
    }

    public HomePage gotoForgotPasswordPage(){
        JavascriptExecutor js = (JavascriptExecutor) Constants.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.getforgotpw().click();
        return new HomePage();
    }

    public void clickForgotPassword (){
        this.getLinkForgotPassword().click();
        new HomePage();
    }

    public HomePage changepassword (String currentpassword, String newpassword, String confirmpassword){
        // Submit login credentials
        this.getTxtCurrentPassword().sendKeys(currentpassword);
        this.getTxtNewPassword().sendKeys(newpassword);
        this.getTxtConfirmPassword().sendKeys(confirmpassword);
        this.getBtnChangePassword().click();

        // Land on Home page
        return new HomePage();
    }

    public HomePage PasswordResetInstructions (String EmailAddress) {
        this.getTxtEmailAddress().sendKeys(EmailAddress);
        this.getBtnSendInstructions().submit();
        return new HomePage();
    }
}
