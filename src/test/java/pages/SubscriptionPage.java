package pages;

import org.openqa.selenium.By;
import pages.ProfilePage.ProfilePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SubscriptionPage {
    public ProfilePage clickContinueSubButton() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
        return new ProfilePage();
    }

    public void buyMonthlySub() {
        $(By.id("tv.motorsport.mobile:id/subscription_tv_name")).shouldHave(text("MONTHLY")).click();
    }

    public SubscriptionPage checkSuccessBuy(String message) {
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text(message));
        System.out.println("Успех");
        return new SubscriptionPage();
    }
}
