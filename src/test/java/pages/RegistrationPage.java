package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    public void pressExitButton() {
        $(By.id("tv.motorsport.mobile:id/close")).click();
    }

    public SubscriptionPage chooseFreePlan() {
        $(By.id("tv.motorsport.mobile:id/subscription_scv_plan_free")).click();
        return new SubscriptionPage();
    }
}
