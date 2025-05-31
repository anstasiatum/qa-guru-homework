package eighthHomeTask.data;

public enum Language {
    Chinese("普通话", "從官方管道購買《漢密爾頓》門票"),
    Spanish("Español","Compre las entradas para Hamilton");

    private final String description;
    private final String languageName;

    Language(String languageName, String description) {
        this.languageName = languageName;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguageName() {
        return languageName;
    }
}