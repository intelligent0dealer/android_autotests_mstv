package models.fixture;

public enum TimeFixture {
    TIME_FOR_PLUG_START("120000");
    String value;

    TimeFixture(String value) {
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
