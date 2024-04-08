package Railway;

import Constant.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.Select;

public class BookTicketPage extends GeneralPage {
//    Locators
    private final By _listTimeTable = By.xpath("//table[@class='MyTable WideTable']/tbody/tr");
    private final By _selectdepartdate = By.xpath("//select[@name='Date']");
    private final By _btnbookticketpage = By.xpath("//input[@value='Book ticket']");

//

    private final By _lblBookTicketErrorMsg = By.xpath("//h1[@align='center']");
    private final By _btnBooking = By.xpath("//input[@value='Book ticket']");
    private final By _departFrom = By.xpath("//select[@name='DepartStation']");
    private final By _ArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By _SeatType = By.xpath("//select[@name='SeatType']");
    private final By _TicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By _Date = By.xpath("//select[@name='Date']");


//    Elements
    public WebElement getBtnBookTicketpage() {return Constants.WEBDRIVER.findElement(_btnbookticketpage);}
    public WebElement getSelectDepartdate() { return Constants.WEBDRIVER.findElement(_selectdepartdate); }

//
    protected WebElement getBtnBooking(){return Constants.WEBDRIVER.findElement(_btnBooking);}
    protected Select getDate(){return new Select(Constants.WEBDRIVER.findElement(_Date)) ;}
    protected Select getTicketAmount(){return  new Select(Constants.WEBDRIVER.findElement(_TicketAmount));}
    protected Select getSeatType(){return new  Select(Constants.WEBDRIVER.findElement(_SeatType));}
    protected Select getDepartFrom(){return new Select(Constants.WEBDRIVER.findElement(_departFrom));}
    protected Select getArriveAt(){return new Select (Constants.WEBDRIVER.findElement(_ArriveAt));}
    protected WebElement getBookTicketErrorMessage(){return  Constants.WEBDRIVER.findElement(_lblBookTicketErrorMsg);}
    public String getErrorBookTicketMessage(){return this.getBookTicketErrorMessage().getText();}


//    Methods
    public void ClickBookTicket(String departStation, String arriveStation) {
        List<WebElement> rows = Constants.WEBDRIVER.findElements(_listTimeTable);
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            String departStationInRow = columns.get(1).getText();
            String arriveStationInRow = columns.get(2).getText();
            if (departStationInRow.equals(departStation) && arriveStationInRow.equals(arriveStation)) {
                ((JavascriptExecutor) Constants.WEBDRIVER).executeScript(
                        "arguments[0].scrollIntoView(true);", row);
                WebElement bookTicketLink = row.findElement(By.linkText("book ticket"));
                bookTicketLink.click();
                break;
            }
        }
    }

    public String bookTicket(){
        WebElement footElement =Constants.WEBDRIVER.findElement(By.xpath("//div[@id='footer']"));
        ((JavascriptExecutor)  Constants.WEBDRIVER).executeScript("arguments[0].scrollIntoView();", footElement);

        this.getDate().selectByIndex(0);
        this.getDepartFrom().selectByVisibleText("Huế");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.getArriveAt().selectByVisibleText("Nha Trang");
        this.getSeatType().selectByVisibleText("Soft bed with air conditioner");
        this.getTicketAmount().selectByValue("1");
        this.getBtnBooking().click();

        return Constants.WEBDRIVER.getCurrentUrl().split("id=")[1];

    }


}
