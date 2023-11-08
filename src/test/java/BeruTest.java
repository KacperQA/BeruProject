import org.example.BeruTestPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.BeruTestPO.YEAR_FILTER_DROPDOWN_ID;

public class BeruTest extends BaseTest {

    @Test
    public void checkIfArticlesFilteredByYearWereCreatedInSelectedYear() {
        BeruTestPO beruTestPO = new BeruTestPO(driver);

        beruTestPO.navigateToHomePage();
        beruTestPO.acceptCookiesButton();

        for (String optionString : beruTestPO.getSelectOptions(YEAR_FILTER_DROPDOWN_ID)) {
            beruTestPO.selectYearFilter(optionString);

            if (!optionString.equals("Select year")) {
                boolean allDatesEqualFilter = beruTestPO.getArticleDates().stream().allMatch(el -> el.equals(optionString));
                Assertions.assertTrue(allDatesEqualFilter);
            } else if (optionString.contains("and older")) {

                boolean specificDateEqualFilter = beruTestPO.convertedGetArticleDatesToInteger(beruTestPO.getArticleDates()).stream().
                        allMatch(i -> i <= beruTestPO.convertFilterTagToInteger(optionString));

                Assertions.assertTrue(specificDateEqualFilter);
            }
        }
    }
}
