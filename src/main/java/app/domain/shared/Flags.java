package app.domain.shared;

public enum Flags {

    OFFICIAL("A", "Official figure"), ESTIMATED("E", "Estimated value"), IMPUTED("I", "Imputed value"),
    MISSING("M", "Missing value (data cannot exist; not applicable)"), UNOFFICIAL("T", "Unofficial figure"),
    NOTDEFINED("ND", "ND");

    private final String flag;
    private final String description;

    Flags(String flag, String description) {
        this.flag = flag;
        this.description = description;
    }

    public String getFlag() {
        return this.flag;
    }

    public String getDescription() {
        return this.description;
    }

}
