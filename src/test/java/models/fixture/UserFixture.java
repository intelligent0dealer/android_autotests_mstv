package models.fixture;

public enum UserFixture {
    EMAIL_FOR_API_TEST("intelligent.dealer1605+595@gmail.com"), PASSWORD_FOR_API_TEST("1234"),
    EMAIL_FOR_CHANGE_PASS_TEST("intelligent.dealer1605+59@gmail.com"),PASSWORD_FOR_CHANGE_PASS_TEST("12345678");

    String value;

    UserFixture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
