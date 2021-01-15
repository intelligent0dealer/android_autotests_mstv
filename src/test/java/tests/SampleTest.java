package tests;


import models.fixture.UserFixture;
import org.aspectj.weaver.ast.And;
import org.junit.AfterClass;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import pages.DbUtils.DbUtils;
import org.junit.Test;
import org.springframework.context.annotation.Description;
import pages.AndroidProfile;
import pages.TestAPI;


public class SampleTest {


    @Test
    @Description("Регистрация пользователя в приложении")
        public void fullRegistration() throws Exception {

        AndroidProfile.registrationInApp();
        AndroidProfile.pressExitButton();

//        Final User user = this.getDAO('user').findByEmail(UserFixture.EMAIL_FOR_API_TEST.getValue());

        TestAPI.confirmationRegisterNewUser();
        TestAPI.verificationEmailConfirmed();
        AndroidProfile.openSignIn();

        AndroidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        AndroidProfile.openSignIn();
        AndroidProfile.checkSubscribeAtProfile();
    }
    @Test
        public void buySubscription() throws Exception {
        AndroidProfile.openApplication();
        AndroidProfile.logIn();
        AndroidProfile.checkSubscribeAtProfile();
        AndroidProfile.clickSubscribe();
        AndroidProfile.buyMonthlySub();
        AndroidProfile.checkSuccessBuy();
        AndroidProfile.clickContinueSubButton();
        TestAPI.getSubscriptionInfo();
        DbUtils.deleteSubscribe(UserFixture.EMAIL_FOR_API_TEST.getValue());
        DbUtils.deleteUser(UserFixture.EMAIL_FOR_API_TEST.getValue());
    }
    @Test
        public void buyPPV() throws Exception {
        AndroidProfile.openApp();
        AndroidProfile.logIn();
        // search
        // ppv input
        // open
        // buy
        // check
        // clear
    }

    @Test
    @Description("Проверка смены имени и никнейма")
        public void changeName() throws Exception{

            AndroidProfile.openApplication();
            AndroidProfile.logIn();
            AndroidProfile.checkSubscribeAtProfile();

            AndroidProfile.goToName();
            AndroidProfile.inputNameLastDone("Artem","Morozov");
            AndroidProfile.checkNameLast("Artem Morozov");
            AndroidProfile.checkSubscribeAtProfile();
            TestAPI.postClearNameSurname();

            AndroidProfile.clickUsername();
            AndroidProfile.inputUserName("intdealer");
            AndroidProfile.checkUserName("intdealer");
            TestAPI.postClearNickname();
            AndroidProfile.checkSubscribeAtProfile();

        }

    @Test
        public void changeGender() throws Exception {
            AndroidProfile.openApplication();
            AndroidProfile.logIn();
            AndroidProfile.checkSubscribeAtProfile();

            AndroidProfile.clickGender();
            AndroidProfile.chooseGender();

            AndroidProfile.checkSubscribeAtProfile();
            AndroidProfile.checkGender("Мужской");
            TestAPI.postClearToOtherGender();
    }

    @Test
        public void changePassword() throws Exception {
        AndroidProfile.openApplication();
        AndroidProfile.logIn();

        AndroidProfile.changePassword(UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());

        AndroidProfile.checkSubscribeAtProfile();
        AndroidProfile.scrollPage();


        AndroidProfile.signOut();
        AndroidProfile.openSignIn();
        AndroidProfile.inputLogPass(UserFixture.EMAIL_FOR_CHANGE_PASS_TEST.getValue(),UserFixture.PASSWORD_FOR_API_TEST.getValue());
        AndroidProfile.openSignIn();
        AndroidProfile.changePassword(UserFixture.PASSWORD_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue());  // чтоб как было:)))
    }
/*
    @AfterClass public static void tearDown() {
        DbUtils.deleteUser(UserFixture.EMAIL_FOR_API_TEST.getValue());
    } */


}


