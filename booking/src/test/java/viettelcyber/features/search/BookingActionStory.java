package viettelcyber.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import viettelcyber.abilities.Booking;
import viettelcyber.actions.ChooseDestination;
import viettelcyber.actions.Period;
import viettelcyber.actions.VisitedCustomer;
import viettelcyber.questions.SuggestionBox;
import viettelcyber.tasks.OpenTheApplication;
import viettelcyber.tasks.Search;
import viettelcyber.ui.BookingPage;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SerenityRunner.class)
public class BookingActionStory {
    Actor mrdat = Actor.named("MrDat");

    @Managed(uniqueSession = true)
    public WebDriver herBrowser;

    @Steps
    OpenTheApplication openTheApplication;

    @Before
    public void annaCanBrowseTheWeb() {
        herBrowser.manage().window().maximize();
        mrdat.can(BrowseTheWeb.with(herBrowser))
        .can(Booking.loadTestData(mrdat.getName()));
    }

    @Test
    public void search_destination() {
        givenThat(mrdat).wasAbleTo(Open.url("https://www.booking.com/index.en-gb.html"));
        when(mrdat).attemptsTo(
//                Enter.theValue(Booking.as(mrdat).getDestination()).into(BookingPage.SEARCH_FIELD),
//                WaitUntil.the(BookingPage.SUGGEST_DESTINATION, WebElementStateMatchers.isVisible()).forNoMoreThan(5).seconds()
                ChooseDestination.with(Booking.as(mrdat).getDestination())
        );
        and(mrdat).should(eventually(seeThat(SuggestionBox.TextOfSuggestionBox(), containsString(Booking.as(mrdat).getDestination()))));
        and(mrdat).attemptsTo(
                Click.on(BookingPage.SUGGEST_DESTINATION),
                Period.WithStartDate(Booking.as(mrdat).getStartDate()).andWithEndDate(Booking.as(mrdat).getEndDate()),
                Click.on(BookingPage.ACCOMODATION_CONTAINER),
                VisitedCustomer.adults(Booking.as(mrdat).getAdult()).childs(Booking.as(mrdat).getChildren()).rooms(Booking.as(mrdat).getRooms()),
                Click.on(BookingPage.BTN_SEARCH)
        );
        then(mrdat).should(eventually(seeThat(TheWebPage.title(), containsString("Booking.com: Hotels in"))));
    }
}
