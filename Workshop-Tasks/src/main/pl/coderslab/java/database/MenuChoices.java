package main.pl.coderslab.java.database;

public enum MenuChoices {
    ADD("add"),
    REMOVE("remove"),
    LIST("list"),
    EXIT("exit");

    private final String choice;

    private MenuChoices(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public static boolean isValidChoice(String choice) {
        for (MenuChoices menuChoice : MenuChoices.values()) {
            if (menuChoice.getChoice().equals(choice)) {
                return true;
            }
        }
        return false;
    }

}
