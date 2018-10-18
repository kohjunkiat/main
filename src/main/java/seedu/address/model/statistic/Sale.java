package seedu.address.model.statistic;

/**
 * Represents Sale in the month's Statistic
 * Guarantees: immutable
 */
public class Sale {
    private static final String QUANTITY_VALIDATION_REGEX = "\\d{1,4}";
    public String value;

    /**
     * Constructs an {@code Revenue}.
     *
     * @param revenue
     */
    public Sale(String revenue) {
        value = revenue;
    }

    public static boolean isValid(String test) {
        return test.matches(QUANTITY_VALIDATION_REGEX);
    }

    public void increase(int amount) {
        value = Integer.toString(Integer.parseInt(value) + amount);
    }

    public void decrease(int amount) {
        value = Integer.toString(Integer.parseInt(value) - amount);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sale // instanceof handles nulls
                && value.equals(((Sale) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

