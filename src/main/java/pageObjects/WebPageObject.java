package pageObjects;

import org.openqa.selenium.By;

public class WebPageObject {
    By searchFld = By.xpath("// form/div[1] // input");

    By form = By.cssSelector("form");

    By answer = By.xpath("// *[@data-sokoban-grid]");

    By answerLink = By.xpath(". // *[@role='text']");

    By answerHeader = By.xpath(". // *[@role='heading'] / div");

    By answerDescription = By.xpath(". / div[2] / div");
}
