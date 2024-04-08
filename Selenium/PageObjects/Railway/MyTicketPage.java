package Railway;
import Constant.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List;
import Common.Utilities;


public class MyTicketPage extends GeneralPage{
//    Locators
    private String btnCancelWithRow = "//td[.='%s']/..//input[@value='Delete' or @value='Cancel']";
    private String rowSelected = "//input[@onclick='%s']/../..";

//    Element
    public String getCancelId(String rowNumber){
        return getBtnCancelWithRow(rowNumber).getAttribute("onclick");
    }
    private WebElement getBtnCancelWithRow(String rowNumber){
        return Constants.WEBDRIVER.findElement(By.xpath(String.format(btnCancelWithRow,rowNumber)));
    }


//    Methods
    public void cancelTicket(String rowNumber){
        Utilities.scrollToFindElement(getLink());
        getBtnCancelWithRow(rowNumber).click();
    }

    private WebElement getRowSelected(String cancelId) {
        return Constants.WEBDRIVER.findElement(By.xpath(String.format(rowSelected,cancelId)));
    }

    private WebElement selectRowWillBeCanceled(String cancelId){
        return getRowSelected(cancelId);
    }

    public Boolean checkRowBeCanceled (String cancelId){
        try{
            selectRowWillBeCanceled(cancelId);
            return false;
        }
        catch(Exception e){
            return true;
        }
    }

    public void clickOkAlert(){
        Constants.WEBDRIVER.switchTo().alert().accept();
    }

    public boolean findById(String id, String expectedDepartFrom, String expectedArriveAt, String expectedSeatType, String expectedDatepart, String expectedTicketAmount) {
        final By departFromLocator = By.xpath("//tr[contains(td/input/@onclick, 'DeleteTicket(" + id + ")')]/td[2]");
        final By arriveAtLocator = By.xpath("//tr[contains(td/input/@onclick, 'DeleteTicket(" + id + ")')]/td[3]");
        final By seatTypeLocator = By.xpath("//tr[contains(td/input/@onclick, 'DeleteTicket(" + id + ")')]/td[4]");
        final By ticketAmountLocator = By.xpath("//tr[contains(td/input/@onclick, 'DeleteTicket(" + id + ")')]/td[9]");
        final By datepartLocator = By.xpath("//tr[contains(td/input/@onclick, 'DeleteTicket(" + id + ")')]/td[5]");

        String actualDepartFromText = Constants.WEBDRIVER.findElement(departFromLocator).getText();
        String actualArriveAtText = Constants.WEBDRIVER.findElement(arriveAtLocator).getText();
        String actualSeatTypeText = Constants.WEBDRIVER.findElement(seatTypeLocator).getText();
        String actualTicketAmountText = Constants.WEBDRIVER.findElement(ticketAmountLocator).getText();
        String actualDatepartText = Constants.WEBDRIVER.findElement(datepartLocator).getText();

        return expectedDepartFrom.equals(actualDepartFromText) &&
                expectedArriveAt.equals(actualArriveAtText) &&
                expectedSeatType.equals(actualSeatTypeText) &&
                expectedTicketAmount.equals(actualTicketAmountText) &&
                expectedDatepart.equals(actualDatepartText);

    }
}

