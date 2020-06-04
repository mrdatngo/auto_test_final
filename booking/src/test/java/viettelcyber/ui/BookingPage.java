package viettelcyber.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BookingPage {
    public static Target SEARCH_FIELD = Target.the("Where you are going?").located(By.name("ss"));
    public static Target SUGGEST_DESTINATION = Target.the("Suggest destination").located(By.cssSelector("ul[aria-label=\"List of suggested destinations \"] li[data-i=\"0\"]"));
    public static Target GET_DATE(String dataDate) {
        return Target.the("Data date").located(By.cssSelector("td[data-date=\"" + dataDate + "\"]"));
    }
    public static Target ACCOMODATION_CONTAINER = Target.the("accomodation container").located(By.cssSelector("div[data-visible=\"accommodation,flights\"]"));
    public static Target ACCOMODATION_ADULT_PLUS = Target.the("adults plus").located(By.cssSelector("button[aria-label=\"Increase number of Adults\"]"));
    public static Target ACCOMODATION_CHILD_PLUS = Target.the("childs plus").located(By.cssSelector("button[aria-label=\"Increase number of Children\"]"));
    public static Target ACCOMODATION_ROOMS_PLUS = Target.the("rooms plus").located(By.cssSelector("button[aria-label=\"Increase number of Rooms\"]"));

    public static Target ACCOMODATION_ADULT_VALUE = Target.the("adults text value").locatedBy("(//span [@data-bui-ref=\"input-stepper-value\"])[1]");
    public static Target ACCOMODATION_CHILD_VALUE = Target.the("childs text value").locatedBy("(//span [@data-bui-ref=\"input-stepper-value\"])[2]");
    public static Target ACCOMODATION_ROOMS_VALUE = Target.the("rooms text value").locatedBy("(//span [@data-bui-ref=\"input-stepper-value\"])[3]");

    public static Target BTN_SEARCH = Target.the("search button").located(By.cssSelector("button[class=\"sb-searchbox__button \"]"));

}
