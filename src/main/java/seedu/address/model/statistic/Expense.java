package seedu.address.model.statistic;

/**
 * Represents Expense in the month's Statistic
 * Guarantees: immutable
 */
public class Expense {
    private static final String QUANTITY_VALIDATION_REGEX = "[-+]?[0-9]*\\.?[0-9]+";
    public String value;

    /**
     * Constructs an {@code Expense}.
     *
     * @param expense in String format
     */
    public Expense(String expense) {
        value = expense;
    }

    public static boolean isValid(String test) {
        return test.matches(QUANTITY_VALIDATION_REGEX);
    }

    public void increase(float amount) {
        value = Float.toString(Float.parseFloat(value) + amount);
    }

    public void decrease(float amount) {
        value = Float.toString(Float.parseFloat(value) - amount);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Expense // instanceof handles nulls
                && value.equals(((Expense) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
