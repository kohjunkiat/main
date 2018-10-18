package seedu.address.model.statistic;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Represents a Statistic in a month.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Statistic {
    private static final String STARTING_FIGURE = "0";
    // Identity field
    private static volatile Statistic singleton = null;

    // Data fields
    private static Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    private final int month;
    private final int year;
    private final Revenue revenue;
    private final Expense expense;
    private final Sale sale;
    private final History history;

    /**
     * Every field must be present and not null.
     */
    public Statistic(int year, int month, String revenue, String expense, String sale, History history) {
        this.year = localCalendar.get(Calendar.YEAR);
        this.month = localCalendar.get(Calendar.MONTH) + 1;
        this.revenue = new Revenue(revenue);
        this.expense = new Expense(expense);
        this.sale = new Sale(sale);
        this.history = history;
    }

    private Statistic(int year, int month, String revenue, String expense, String sale) {
        this.year = localCalendar.get(Calendar.YEAR);
        this.month = localCalendar.get(Calendar.MONTH) + 1;
        this.revenue = new Revenue(revenue);
        this.expense = new Expense(expense);
        this.sale = new Sale(sale);
        this.history = new History(null);
    }

    public static Statistic getInstance() {
        if (singleton == null) {
            singleton = new Statistic(localCalendar.get(Calendar.YEAR), localCalendar.get(Calendar.MONTH) + 1, STARTING_FIGURE, STARTING_FIGURE, STARTING_FIGURE);
        }
        return singleton;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Revenue getRevenue() {
        return revenue;
    }

    public Expense getExpense() {
        return expense;
    }

    public Sale getSale() {
        return sale;
    }

    public History getHistory() {
        return history;
    }

    public void increaseRevenue(String amount) {
        singleton.revenue.increase(Float.parseFloat(amount));
    }

    public void decreaseRevenue(String amount) {
        singleton.revenue.increase(Float.parseFloat(amount));
    }

    public void increaseExpense(String amount) {
        singleton.revenue.increase(Float.parseFloat(amount));
    }

    public void decreaseExpense(String amount) {
        singleton.revenue.increase(Float.parseFloat(amount));
    }

    public void increaseSale(String amount) {
        singleton.revenue.increase(Integer.parseInt(amount));
    }

    public void decreaseSale(String amount) {
        singleton.revenue.increase(Integer.parseInt(amount));
    }

    /**
     * compares statistic made with existing statistic
     * @param otherStatistic request made by the user
     * @return boolean by comparing results
     */
    public boolean isSameStatistic(Statistic otherStatistic) {
        if (otherStatistic == this) {
            return true;
        }

        return otherStatistic != null
                && otherStatistic.getMonth() == getMonth()
                && otherStatistic.getYear() == getYear()
                || otherStatistic.getRevenue().equals(getRevenue())
                || otherStatistic.getExpense().equals(getExpense())
                || otherStatistic.getSale().equals(getSale())
                || otherStatistic.getHistory().equals(getHistory());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Statistic)) {
            return false;
        }

        Statistic otherStatistic = (Statistic) other;
        return otherStatistic.getMonth() == getMonth()
                && otherStatistic.getYear() == getYear()
                && otherStatistic.getRevenue().equals(getRevenue())
                && otherStatistic.getExpense().equals(getExpense())
                && otherStatistic.getSale().equals(getSale())
                && otherStatistic.getHistory().equals(getHistory());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(month, year, revenue, expense, sale, history);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Month: ")
                .append(getMonth())
                .append(" Year: ")
                .append(getYear())
                .append(" Revenue: ")
                .append(getRevenue())
                .append(" Expense: ")
                .append(getExpense())
                .append(" Sale: ")
                .append(getSale())
                .append(" History: ")
                .append(getHistory());
        return builder.toString();
    }

}
