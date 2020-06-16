package Google;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class Google {

    @Test
    public void userCanSearch() {
        open("https://google.com");

        new GooglePage().searchFor("Selenide");
        SearchResultsPage results = new SearchResultsPage();
        results.getResult(1).shouldHave(text("Selenide: concise UI tests in Java"));
    }

}
