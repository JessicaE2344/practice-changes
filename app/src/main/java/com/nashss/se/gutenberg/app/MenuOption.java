package com.nashss.se.gutenberg.app;

/**
 * Represents the main Gutenberg application menu
 */
public enum MenuOption {
    QUIT(0),
    ALL_BOOKS(1),
    BOOK_BY_ID(2),
    SEARCH_BOOKS_BY_AUTHOR(3),
    SEARCH_BOOKS_BY_TITLE(4),
    SEARCH_BOOKS_BY_AUTHOR_OR_TITLE(5),
    RANDOM_BOOK(6),
    ADD_TO_FAVORITES(7);

    /**
     * Get the minimum optionNum value for the entire menu
     * @return The minimum optionNum
     */
    public static int min() {
        int result = Integer.MAX_VALUE;
        for (MenuOption option : values()) {
            if (option.optionNum < result) {
                result = option.optionNum;
            }
        }
        return result;
    }

    /**
     * Get the maximum optionNum value for the entire menu
     * @return The maximum optionNum
     */
    public static int max() {
        int result = Integer.MIN_VALUE;
        for (MenuOption option : values()) {
            if (option.optionNum > result) {
                result = option.optionNum;
            }
        }
        return result;
    }

    /**
     * Create a string representation of the menu
     * that's suitable for displaying to the user
     * @return A friendly representation of the menu
     */
    public static String renderMenu() {
        StringBuilder sb = new StringBuilder("Available Options\n-----------------\n");
        for (MenuOption option : values()) {
            sb.append(String.format("%d) %s%n", option.optionNum, option.toFriendlyString()));
        }
        return sb.toString();
    }

    /**
     * Convert an option number into a MenuOption
     * @param optionNum
     * @return The MenuOption that corresponds to the given option number
     * @throws IllegalArgumentException if the given optionNum is not valid
     */
    public static MenuOption fromOptionNum(int optionNum) {
        if (optionNum < min() || optionNum > max()) {
            throw new IllegalArgumentException(
                    String.format("optionNum out of range. Must be between %d and %d", min(), max()));
        }

        for (MenuOption option : values()) {
            if (option.optionNum == optionNum) {
                return option;
            }
        }

        throw new RuntimeException("Unable to find MenuOption for given optionNum");
    }

    private final int optionNum;

    private MenuOption(int optionNum) {
        this.optionNum = optionNum;
    }

    private String toFriendlyString() {
        return name()
                .replaceAll("_", " ")
                .toLowerCase();
    }

}
