package Railway;

import Common.Utilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Constant.Constants;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;



public class LoginTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constants.WEBDRIVER = new ChromeDriver();
        Constants.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constants.WEBDRIVER.quit();
    }


    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username add password");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();
        String actualMsg = loginPage.login(Constants.USERNAME, Constants.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constants.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank 'Username' textbox");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();
        loginPage.login("", Constants.PASSWORD);
        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();

        loginPage.login(Constants.USERNAME, " ");

        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage bookTicket = new LoginPage();
        bookTicket.loginclickBookticket();
        bookTicket.gotoLoginPage();
        Assert.assertTrue(bookTicket.getTabBookticket().isDisplayed(), "The login page is not displayed");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 0; i < 4; i++) {
            loginPage.login(Constants.USERNAME, "invalidPassword");
        }

        loginPage.login(Constants.USERNAME, "invalidPassword");
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC06() {
        System.out.println("TC06-Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constants.USERNAME, Constants.PASSWORD).getWelcomeMessage();

        loginPage.gotoMyticket();
        loginPage.clickChangePassword();
        Assert.assertTrue(loginPage.getTabMyticket().isDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(loginPage.getTabChangePassWord().isDisplayed(), "Change password tab is not displayed");
    }

    @Test
    public void TC07() {
        System.out.println("TC07 - User can create new account");
        HomePage homepage = new HomePage();
        homepage.open();
        RegisterPage registerpage = homepage.gotoRegisterPage();
        String REGISTER_USERNAME = "thanhnhan" + (int) (Math.random() * 1000) + "@gmail.com";
        registerpage.register(REGISTER_USERNAME,"thanhnhan","thanhnhan","123456789");
        String actualMsg = registerpage.getLblThanksRegister().getText();
        String expectedErrorMsg = "Thank you for registering your account";
        Assert.assertEquals(actualMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - User can't login with an account hasn't been activated");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();

        loginPage.login("htthanhan@gmail.com", "123456789");
        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09 - User can change password");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();
        loginPage.login(Constants.USERNAME, Constants.PASSWORD);

        loginPage.clickChangePassword();

        loginPage.changepassword(Constants.PASSWORD, "123456789","123456789");
        String actualMsg = loginPage.getLblChangePasswordErrorMsg().getText();
        String expectedErrorMsg = "Your password has been updated";
        Assert.assertEquals(actualMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' is not the same with 'Password'");
        HomePage homepage = new HomePage();
        homepage.open();
        RegisterPage registerpage = homepage.gotoRegisterPage();

        registerpage.register("thnhan@gmail.com", "123456789", "12345678", "123456789");
        String actualMsg = registerpage.getLblRegisterErrorMsg().getText();
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC11() {
        System.out.println("TC11 - User can't create account while password and PID fields are empty");
        HomePage homepage = new HomePage();
        homepage.open();
        RegisterPage registerpage = homepage.gotoRegisterPage();

        registerpage.register("thnhann@gmail.com","","","");

        String actualErrorMsg = registerpage.getLblRegisterErrorMsg().getText();
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");

        Assert.assertTrue(registerpage.getlblPasswordErrorMsg().isDisplayed(), "Invalid password length");
        Assert.assertTrue(registerpage.getlblPIPErrorMsg().isDisplayed(),"Invalid ID length");
    }

    @Test
    public void TC12() {
        System.out.println("TC12 - Errors display when password reset token is blank");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();
        loginPage.PasswordResetInstructions(Constants.USERNAME2);
    }

    @Test
    public void TC13() {
        System.out.println("TC13 - Errors display if password and confirm password don't match when resetting password");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();
        loginPage.PasswordResetInstructions(Constants.USERNAME);
    }

    @Test
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constants.USERNAME, Constants.PASSWORD);
        BookTicketPage bookTicket = homePage.gotoBookTicketPage();

        String id = bookTicket.bookTicket();

        MyTicketPage myTicketPage = homePage.gotoMyticket();
        String date = Utilities.getDate();
        boolean check = myTicketPage.findById(id, "Huế", "Nha Trang", "Soft bed with air conditioner", date, "1");
        Assert.assertEquals(check, true, "");
    }

    @Test
    public void TC15() {
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");
        HomePage homepage = new HomePage();
        homepage.open();
        LoginPage loginPage = homepage.gotoLoginPage();
        loginPage.login(Constants.USERNAME, Constants.PASSWORD);

        BookTicketPage bookticket = homepage.gotoTimeTablePage();
        bookticket.ClickBookTicket("Huế","Sài Gòn");
    }

    @Test
    public void TC16() {
        System.out.println("User can cancel a ticket");
        HomePage homepage = new HomePage();
        homepage.open();

        LoginPage loginPage = homepage.gotoLoginPage();
        loginPage.login(Constants.USERNAME, Constants.PASSWORD);

        BookTicketPage bookTicketPage = homepage.gotoBookTicketPage();

        String id = bookTicketPage.bookTicket();

        MyTicketPage myTicketPage = homepage.gotoMyticket();
        String date = Utilities.getDate();
        myTicketPage.findById(id, "Huế", "Nha Trang", "Soft bed with air conditioner", date, "1");

        String cancelID = myTicketPage.getCancelId("5");

        myTicketPage.cancelTicket("5");
        myTicketPage.clickOkAlert();

        Boolean actualResult = myTicketPage.checkRowBeCanceled(cancelID);
        Assert.assertTrue(actualResult, "User can not cancel a ticket");
    }
}

