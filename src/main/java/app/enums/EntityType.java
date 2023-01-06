package app.enums;

public enum EntityType {
    CLIENT('C'),
    EMPRESA('E'),
    PRODUCER('P');

    private final char type;

    EntityType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public static EntityType valueOf(char type) {
        for (EntityType ettyType : EntityType.values()) {
            if (ettyType.getType() == type) {
                return ettyType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }
}
