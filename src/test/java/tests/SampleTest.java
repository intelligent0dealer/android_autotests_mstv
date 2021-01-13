package tests;


import models.fixture.UserFixture;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import pages.DbUtils.DbUtils;
import org.junit.Test;
import org.springframework.context.annotation.Description;
import pages.AndroidProfile;
import pages.TestAPI;


public class SampleTest {

    @Test
        public void simpleTest(){
        // воткнул Test API, проверить работу в совокупности
            DbUtils.deleteSubscribe("intelligent.dealer1605+59@gmail.com");
        /*    AndroidProfile.openApplication();
            AndroidProfile.logIn();
            AndroidProfile.atPageProfile("Subscription"); // проверка что мы на странице профайла
            TestAPI.getSubscriptionInfo();
            AndroidProfile.scrollPage();
            AndroidProfile.signOut(); //
            AndroidProfile.atPageSignIn("SIGN IN"); */
    }
    @Test
    @Description("Регистрация пользователя в приложении")
        public void fullRegistration() {

        AndroidProfile.registrationInApp();
        AndroidProfile.pressExitButton();

//        Final User user = this.getDAO('user').findByEmail(UserFixture.EMAIL_FOR_API_TEST.getValue());

        TestAPI.confirmationRegisterNewUser();
        TestAPI.verificationEmailConfirmed();
        AndroidProfile.openSignIn();

        AndroidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        AndroidProfile.openSignIn();
        AndroidProfile.atPageProfile("Подписка");
        DbUtils.deleteUser(UserFixture.EMAIL_FOR_API_TEST.getValue());
    }
 /*    @AfterTest
        public static void tearDown() throws Exception {
         DbUtils.deleteUser(UserFixture.EMAIL_FOR_API_TEST.getValue());
     } */
    @Test
    @Description("Проверка смены имени и никнейма")

        public void name(){

            AndroidProfile.openApplication();
            AndroidProfile.logIn();
            AndroidProfile.atPageProfile("Subscription");
            AndroidProfile.goToName();
            AndroidProfile.inputNameLastDone("Artem","Morozov");
            AndroidProfile.checkNameLast("Artem Morozov");
            AndroidProfile.clickUsername();
            AndroidProfile.inputUserName("intdealh");
            AndroidProfile.checkUserName("intdealh");
        }
    @Test
    @Description("Проверка смены пола")
        public void gender() throws InterruptedException {
            AndroidProfile.openApplication();
            AndroidProfile.logIn();
            AndroidProfile.atPageProfile("Subscription");
            AndroidProfile.clickGender();
            AndroidProfile.chooseGender();
            Thread.sleep(5000);
            AndroidProfile.checkGender("Male");
    }
    @Test // сделать константу для кредов
    @Description("Смена пароля") // fixture (изучить)
        public void passwordChange() throws InterruptedException {
        AndroidProfile.openApplication();
        AndroidProfile.logIn();

        AndroidProfile.changePassword( "12345678", "1234");

        AndroidProfile.scrollPage();
        Thread.sleep(5000);

        AndroidProfile.signOut();
        AndroidProfile.openSignIn();
        AndroidProfile.inputLogPass("intelligent.dealer1605+59@gmail.com","1234");
        AndroidProfile.openSignIn();
        AndroidProfile.changePassword("1234", "12345678");
    }



}


