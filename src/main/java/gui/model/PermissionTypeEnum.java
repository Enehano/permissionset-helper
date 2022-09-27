package gui.model;

public enum PermissionTypeEnum {

    OBJECT("object"),
    FIELD("field"),
    CUSTOM("custom"),
    CLASS("class"),
    RECORD_TYPE("record type"),
    PAGE("page"),
    APP("app"),
    USER("user");

    PermissionTypeEnum(String label) {
        this.label = label;
    }
    private final String label;

    public String getLabel() {
        return label;
    }

}
