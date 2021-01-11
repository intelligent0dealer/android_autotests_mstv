package tests;


import DbUtils.DbUtils;
import org.junit.Test;
import org.springframework.context.annotation.Description;
import pages.AndroidProfile;
import pages.TestAPI;


public class SampleTest {

    @Test
        public void simpleTest(){
        // воткнул Test API, проверить работу в совокупности
     //   DbUtils.deleteSubscribe("intelligent.dealer1605+59@gmail.com");
            AndroidProfile.openApplication();
            AndroidProfile.logIn();
            AndroidProfile.atPageProfile("Subscription"); // проверка что мы на странице профайла
            TestAPI.sampleAPI();
            AndroidProfile.scrollPage();
            AndroidProfile.signOut(); //
            AndroidProfile.atPageSignIn("SIGN IN");
    }
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


