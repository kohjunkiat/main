package seedu.address.model.statistic;

/**
 * Represents History in the month's Statistic
 * Guarantees: immutable
 */
public class History {

    public final String value;

    /**
     * Constructs an {@code History}.
     *
     * @param history
     */
    public History(String history) {
        value = history;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof History // instanceof handles nulls
                && value.equals(((History) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
