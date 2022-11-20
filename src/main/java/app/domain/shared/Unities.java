package app.domain.shared;

/**
 * @authors Daniel Aires, Fernando Ribeiro, José Silva, Manuel Marques, Mariana Rocha
 */
public enum Unities {

    TONNES("tonnes"), HECTOGRAMHECTARE("hg/ha"), HECTOGRAMANIMAL("hg/An"), HECTARE("ha"), HEAD("Head"),
    MHEAD("1000 Head"),MILIGRAMAN("100mg/An"),GRAMAN("0.1g/An"),NUMBER("No"),NUMBERANIMAL("No/An"),HECTOGRAM("hg"),
    MANIMAL("1000 No"),HECTOGRAMMETRICTONNE("hg/mt");

    private final String description;

    Unities(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
