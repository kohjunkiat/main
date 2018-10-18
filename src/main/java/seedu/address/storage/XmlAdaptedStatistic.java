package seedu.address.storage;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Quantity;
import seedu.address.model.request.Email;
import seedu.address.model.statistic.Expense;
import seedu.address.model.statistic.History;
import seedu.address.model.statistic.Revenue;
import seedu.address.model.statistic.Sale;
import seedu.address.model.statistic.Statistic;

/**
 * JAXB-friendly version of the Statistic.
 */
public class XmlAdaptedStatistic {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Statistic's %s field is missing!";

    @XmlElement(required = true)
    private int year;
    @XmlElement(required = true)
    private int month;
    @XmlElement(required = true)
    private String revenue;
    @XmlElement(required = true)
    private String sale;
    @XmlElement(required = true)
    private String expense;
    @XmlElement(required = true)
    private String history;


    /**
     * Constructs an XmlAdaptedStatistic.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedStatistic() {}

    /**
     * Constructs an {@code XmlAdaptedStatistic} with the given statistic details.
     */
    public XmlAdaptedStatistic(int year, int month, String revenue, String sale, String expense, String history) {
        this.year = year;
        this.month = month;
        this.revenue = revenue;
        this.sale = sale;
        this.expense = expense;
        this.history = history;

    }

    /**
     * Converts a given Statistic into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedStatistic
     */
    public XmlAdaptedStatistic(Statistic source) {
        year = source.getYear();
        month = source.getMonth();
        revenue = source.getRevenue().value;
        sale = source.getSale().value;
        expense = source.getExpense().value;
        history = source.getHistory().value;
    }

    /**
     * Converts this jaxb-friendly adapted statistic object into the statisticModel's Statistic object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted statistic
     */
    public Statistic toModelType() throws IllegalValueException {
        if (revenue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Isbn.class.getSimpleName()));
        }
        if (!Revenue.isValid(revenue)) {
            throw new IllegalValueException(revenue.trim());
        }
        final Revenue modelRevenue = new Revenue(revenue);

        if (sale == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName()));
        }
        if (!Sale.isValid(sale)) {
            throw new IllegalValueException(sale.trim());
        }
        final Sale modelSale = new Sale(sale);

        if (expense == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Expense.isValid(expense)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Expense modelExpense = new Expense(expense);

        if (history == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        final History modelHistory = new History(history);

        return Statistic.getInstance();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedStatistic)) {
            return false;
        }

        XmlAdaptedStatistic otherPerson = (XmlAdaptedStatistic) other;
        return Objects.equals(revenue, otherPerson.revenue)
                && Objects.equals(sale, otherPerson.sale)
                && Objects.equals(expense, otherPerson.expense)
                && Objects.equals(history, otherPerson.history);
    }
}
