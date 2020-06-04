package viettelcyber.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import viettelcyber.ui.BookingPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Period implements Task  {
    private Date startDate;
    private Date endDate;

    public Period(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    @Override
    @Step("{0} books a room from #startDate to #endDate")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BookingPage.GET_DATE(getDateString(this.startDate))),
                Click.on(BookingPage.GET_DATE(getDateString(this.endDate)))
        );
    }

    public static PeriodBuilder WithStartDate(Date startDate) {
        return new PeriodBuilder(startDate);
    }

    public static class PeriodBuilder {
        private Date startDate;
        public PeriodBuilder(Date startDate) {
            this.startDate = startDate;
        }
        public Period andWithEndDate(Date endDate) {
            return instrumented(Period.class, this.startDate, endDate);
        }
    }
}
