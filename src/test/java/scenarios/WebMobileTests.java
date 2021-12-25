package scenarios;

import dataprovider.TestData;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"},
            description = "Go to Google search and make a search using keyword 'EPAM'",
            dataProviderClass = TestData.class, dataProvider = "googleWebTestData")
    public void googleSearchWebTest(String googleUrl, String googleTitle, String wordToSearch)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getDriver().get(googleUrl);
        getWait().until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        Assert.assertEquals(((WebDriver) getDriver()).getTitle(), googleTitle);
        getWelement("searchFld").sendKeys(wordToSearch);

        getDriver().findElements(getPo().getLocator("suggestions")).get(0).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(getPo().getLocator("answer")));
        for (WebElement answer : getDriver().findElements(getPo().getLocator("answer"))) {
            String link = answer.findElement(getPo().getLocator("answerLink")).getText();
            String header = answer.findElement(getPo().getLocator("answerHeader")).getText();
            String description = answer.findElement(getPo().getLocator("answerDescription")).getText();

            if (!StringUtils.containsIgnoreCase(link, wordToSearch)
                    && !StringUtils.containsIgnoreCase(header, wordToSearch)
                    && !StringUtils.containsIgnoreCase(description, wordToSearch)) {
                Assert.fail("Irrelevant result\n" + link + "\n" + header + "\n" + description + "\n");
            }
        }
    }
}