package viettelcyber.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import viettelcyber.ui.BookingPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class VisitedCustomer implements Task {
    private Integer adult;
    private Integer children;
    private Integer rooms;

    public VisitedCustomer(Integer adult, Integer children, Integer rooms) {
        this.adult = adult;
        this.children = children;
        this.rooms = rooms;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String adultDefaultStr = Text.of(BookingPage.ACCOMODATION_ADULT_VALUE).viewedBy(actor).asString();
        String childrenDefaultStr = Text.of(BookingPage.ACCOMODATION_CHILD_VALUE).viewedBy(actor).asString();
        String roomsDefaultStr = Text.of(BookingPage.ACCOMODATION_ROOMS_VALUE).viewedBy(actor).asString();
        System.out.println(this.adult + " " + this.children + " " + this.rooms);
        int adultPlus = adult - Integer.parseInt(adultDefaultStr);
        int childPlus = children - Integer.parseInt(childrenDefaultStr);
        int roomsPlus = rooms - Integer.parseInt(roomsDefaultStr);
        System.out.println(adultPlus + " " + childPlus + " " + roomsPlus);
        for (int i = 0; i < adultPlus; i++) {
            actor.attemptsTo(
                    Click.on(BookingPage.ACCOMODATION_ADULT_PLUS)
            );
        }
        for (int i = 0; i < childPlus; i++) {
            actor.attemptsTo(
                    Click.on(BookingPage.ACCOMODATION_CHILD_PLUS)
            );
        }
        for (int i = 0; i < roomsPlus; i++) {
            actor.attemptsTo(
                    Click.on(BookingPage.ACCOMODATION_ROOMS_PLUS)
            );
        }
    }

    public static VisitedBuilder adults(Integer adults) {
        return new VisitedBuilder(adults);
    }

    public static class VisitedBuilder {
        private static Integer adults;
        private static Integer childs;

        public VisitedBuilder(Integer adults) {
            VisitedBuilder.adults = adults;
        }

        public VisitedBuilder(Integer adults, Integer childs) {
            VisitedBuilder.adults = adults;
            VisitedBuilder.childs = childs;
        }

        public VisitedBuilder childs(Integer childs) {
            return new VisitedBuilder(VisitedBuilder.adults, childs);
        }

        public VisitedCustomer rooms(Integer rooms) {
            return instrumented(VisitedCustomer.class, adults, childs, rooms);
        }
    }
}
