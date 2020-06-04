package viettelcyber.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import viettelcyber.ui.BookingPage;

public class SuggestionBox implements Question<String> {
    public static Question<String> TextOfSuggestionBox() {
        return new SuggestionBox();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(BookingPage.SUGGEST_DESTINATION).viewedBy(actor).asString();
    }
}
