package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PromoPlug {

    public SignUpPage clickJoinAndWatch() {
        $(By.id("tv.motorsport.mobile:id/promoJoinAndWatch")).click();
        return new SignUpPage();
    }
}
