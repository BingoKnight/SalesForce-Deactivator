import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.Doc;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = new ChromeDriver();

//        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://aaalife.onelogin.com/client/apps/select/288055773");

        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        emailInput.sendKeys("nhessling@aaalife.com");
        //TODO: Enter password
        passwordInput.sendKeys("");
        passwordInput.submit();
        System.out.println("Submitted credentials");

        WebDriverWait wait = new WebDriverWait(driver, 10);

//        wait.until(ExpectedConditions.urlContains("aaalife.onelogin.com/portal/"));

//        driver.navigate().to("https://aaalife.onelogin.com/client/apps/select/288055773");
//        driver.navigate().to("https://aaalife.my.salesforce.com/console");

//        wait.until(ExpectedConditions.urlContains("aaalife.onelogin.com/trust/saml2/launch/288055773"));
//        System.out.println("Current URL: " + driver.getCurrentUrl());
//        driver.navigate().to("https://aaalife--c.na105.content.force.com/secur/contentDoor?startURL=https%3A%2F%2Faaalife.my.salesforce.com%2Fconsole&sid=00D80000000braG%21ARUAQP2vRYoaXLtFUTz4YyRhuq1CZZ.COWUYepE_EbIg6uA2E9XvADhWLjDT5lT0dpZ.9SSqTSXlfRV0IGR2kMLYX5_fyVf2&skipRedirect=1&lm=eyJlbmMiOiJBMjU2R0NNIiwiYXVkIjoiMDBEODAwMDAwMDBicmFHIiwia2lkIjoie1widFwiOlwiMDBEODAwMDAwMDBicmFHXCIsXCJ2XCI6XCIwMkdDMDAwMDAwMFh0WGNcIixcImFcIjpcImNvbnRlbnRkb29ydXNlcnRyYW5zaWVudGtleWVuY3J5cHRcIixcInVcIjpcIjAwNTNjMDAwMDBCWHhJOVwifSIsImNyaXQiOlsiaWF0Il0sImlhdCI6MTU2MzQ3NzcwMjE5OCwiZXhwIjowfQ%3D%3D..9VK1iIQ0PIYvUAAt.VqbgoTwmFA6sW4NeTq8xag%3D%3D.gW0raZ1oHQZ6GcHgX8XoYQ%3D%3D");
//        driver.navigate().to("https://aaalife.my.salesforce.com/console");

//        wait.until(ExpectedConditions.urlContains("aaalife.my.salesforce.com/console"));
        DocumentSettleCondition settleCondition = new DocumentSettleCondition(
                ExpectedConditions.visibilityOfElementLocated(By.id("phSearchInput")), 6000);

        new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(settleCondition.getSettleTime(), TimeUnit.MILLISECONDS)
                .ignoring(WebDriverException.class)
                .until(settleCondition);

        System.out.println("Current URL: " + driver.getCurrentUrl());

        WebElement search = driver.findElement(By.id("phSearchInput"));
        search.sendKeys("sharonvelishek@ipnagent.com");
        search.submit();
        System.out.println("Submitted search");

        List<WebElement> iframe_element=driver.findElements(By.tagName("iframe"));
        System.out.println("number of iframes: " + iframe_element.size());

        driver.switchTo().frame(1);

//        try {
//            DocumentSettleCondition condition = new DocumentSettleCondition(
//                    ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1021")), 6000);
//
//            new FluentWait<WebDriver>(driver)
//                    .withTimeout(30, TimeUnit.SECONDS)
//                    .pollingEvery(settleCondition.getSettleTime(), TimeUnit.MILLISECONDS)
//                    .ignoring(WebDriverException.class)
//                    .until(condition);
//
//            System.out.println("Found class iframe");
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        driver.switchTo().frame("ext-comp-1021");
////        // failing in headed and headless chrome, unable to locate element
        WebElement userSearchResult = driver.findElement(By.xpath("//div[contains(@class,'displayName')]/a"));
        userSearchResult.click();

        System.out.println("Selected user in search results");

        // maybe not getting right iframe
        iframe_element = driver.findElements(By.tagName("iframe"));
        System.out.println("number of iframes: " + iframe_element.size());
        driver.switchTo().frame(1);

        WebElement zenTrigger = driver.findElement(By.xpath("//li[contains(@class,'zen-select zen-open')]/a"));
        zenTrigger.click();
        System.out.println("Clicked zen trigger");

        WebElement userDetail = driver.findElement(By.xpath("//li[contains(@class,'zen-firstItem')]/a"));
        userDetail.click();
        System.out.println("Selected user details");

        System.out.println("Page title is: " + driver.getTitle());

//        WebElement elem = driver.findElement(By.xpath("//*"));
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
//            writer.write(elem.getAttribute("outerHTML"));
//            writer.close();
//        } catch(IOException e){
//            System.out.println("Failed");
//            e.printStackTrace();
//        }
//        driver.close();
    }
}
