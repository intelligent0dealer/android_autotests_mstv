package models.fixture;

public enum ElementsRU {
    TYPE_OF_SUBSCRIPTION("МЕСЯЧНЫЙ");
    String value;

    ElementsRU(String value) {
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
