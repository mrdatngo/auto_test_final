package viettelcyber.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import viettelcyber.abilities.Booking;
import viettelcyber.ui.BookingPage;

public class ChooseDestination implements Task {
    private String destination;

    public ChooseDestination(String destination) {
        this.destination = destination;
    }

    @Override
    @Step("{0} choose the #destination")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(destination).into(BookingPage.SEARCH_FIELD),
                WaitUntil.the(BookingPage.SUGGEST_DESTINATION, WebElementStateMatchers.isVisible()).forNoMoreThan(5).seconds()
        );
    }

    public static ChooseDestination with(String destination) {
        return Tasks.instrumented(ChooseDestination.class, destination);
    }

}
