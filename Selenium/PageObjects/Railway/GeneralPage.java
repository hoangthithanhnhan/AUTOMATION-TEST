package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constants;
import org.openqa.selenium.bidi.log.Log;

public class GeneralPage {
//    Locators

    private final By _tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private final By _tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By tablogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tablogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabregister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By lblwelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By _tabbookticket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    private final By _tabtimetable = By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']");

    private static final By link = By.xpath("//a[.='Web hosting by Somee.com']");


    //    Element
    protected static WebElement getLink() {
        return Constants.WEBDRIVER.findElement(link);
    }
    protected WebElement getTabChangePassWord() {
        return Constants.WEBDRIVER.findElement(_tabChangePassword);
    }
    public WebElement getTabMyticket() {
    return Constants.WEBDRIVER.findElement(_tabMyTicket);
}
    public WebElement getTabTimeTable() { return Constants.WEBDRIVER.findElement(_tabtimetable); }
    protected WebElement getTabLogin() {
        return Constants.WEBDRIVER.findElement(tablogin);
    }
    protected WebElement getTabLogout() {
        return Constants.WEBDRIVER.findElement(tablogout);
    }
    protected WebElement getTabRegister() {
        return Constants.WEBDRIVER.findElement(tabregister);
    }
    protected WebElement getLblWelcomeMessage() {
        return Constants.WEBDRIVER.findElement(lblwelcomeMessage);
    }
    public WebElement getTabBookticket() {
        return Constants.WEBDRIVER.findElement(_tabbookticket);
    }


//    Methods

    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public HomePage clickChangePassword(){
        this.getTabChangePassWord().click();
        return new HomePage();
    }

    public RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }

    public BookTicketPage gotoBookTicketPage() {
        this.getTabBookticket().click();
        return new BookTicketPage();
    }

    public BookTicketPage gotoTimeTablePage() {
        this.getTabTimeTable().click();
        return new BookTicketPage();
    }

    public HomePage loginclickBookticket(){
        this.getTabBookticket().click();
        return new HomePage();
    }

    public MyTicketPage gotoMyticket(){
        this.getTabMyticket().click();
        return new MyTicketPage();
    }


}

