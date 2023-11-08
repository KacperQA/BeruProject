package org.example;

import abstractComponents.AbstractPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeruTestPO extends AbstractPO {

    public static final String YEAR_FILTER_DROPDOWN_ID = "year-filter-selection";

    public BeruTestPO(WebDriver driver) {
        super(driver);
    }

    public void filterResultRemoveButton() {
        driver.findElement(By.xpath("//a[@class='remove']")).click();
    }

    public List<WebElement> listOfArticlesOnPage() {
        return driver.findElements(By.xpath("//div[@id='gb_ContentPlaceHolderDefault_bottomGrid_ctl06'] //div[@class='items'] //div[@class='span-3'] //span[@class='date']"));
    }

    public List<String> getArticleDates() {
        List<String> articleDates = new ArrayList<>();
        List<WebElement> articleList = listOfArticlesOnPage();

        for (WebElement article : articleList) {
            WebElement dateElement = article.findElement(By.xpath("//span[@class='date']"));


//Split and trim performed in getYearFromDate
            articleDates.add(getYearFromDate(dateElement));
        }
        return articleDates;
    }


    public List<Integer> convertedGetArticleDatesToInteger(List<String> articleDates) {
        List<Integer> convertedStringDatesToIntDates = articleDates.stream().map(str -> Integer.parseInt(str)).collect(Collectors.toList());

        return convertedStringDatesToIntDates;
    }

    public int convertFilterTagToInteger(String optionString) {
        int filterConvertedValuesToInt = 0;
        try {
            filterConvertedValuesToInt = Integer.parseInt(optionString);
            return filterConvertedValuesToInt;
        } catch (NumberFormatException e) {
            System.out.println("Invalid Integer Input");
        }
        return filterConvertedValuesToInt;
    }

    /**
     * @param element - element containing date string
     * @return year from date string
     */
    private String getYearFromDate(WebElement element) {
        String wholeText = element.getText();
        if (!wholeText.contains("Select year") || !wholeText.contains("and older")) {
            return element.getText().split("-")[2].trim();
        } else if (wholeText.contains("and older")) {
            return element.getText().split(" ")[0].trim();
        } else {
            return wholeText;
        }

    }

    /**
     * @param id of select filter element (eg. "year-filter-selection")
     * @return list of options texts
     */
    public List<String> getSelectOptions(String id) {
        WebElement filterDropdown = driver.findElement(By.id(id));
        List<WebElement> dropdownOptions = (new Select(filterDropdown)).getOptions();
        return dropdownOptions.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectYearFilter(String option) {
        WebElement staticDropdown = driver.findElement(By.id(YEAR_FILTER_DROPDOWN_ID));
        Select yearDropdown = new Select(staticDropdown);
        yearDropdown.selectByVisibleText(option);
    }
}