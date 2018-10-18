package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStatistic comparison)
 */
public class StatisticList implements ReadOnlyStatistics {

    private final UniqueStatisticList statisticList;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        statisticList = new UniqueStatisticList();
    }

    public StatisticList() {}

    /**
     * Creates an StatisticList using the Persons in the {@code toBeCopied}
     */
    public StatisticList(ReadOnlyStatistics toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the statistic list with {@code statisticList}.
     * {@code statisticList} must not contain duplicate statisticList.
     */
    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList.setStatistics(statisticList);
    }

    /**
     * Resets the existing data of this {@code StatisticList} with {@code newData}.
     */
    public void resetData(ReadOnlyStatistics newData) {
        requireNonNull(newData);

        setStatisticList(newData.getStatisticList());
    }

    //// statistic-level operations

    /**
     * Returns true if a statistic with the same identity as {@code statistic} exists in the address book.
     */
    public boolean hasStatistic(Statistic statistic) {
        requireNonNull(statistic);
        return statisticList.contains(statistic);
    }

    /**
     * Adds a statistic to the address book.
     * The statistic must not already exist in the address book.
     */
    public void addStatistic(Statistic p) {
        statisticList.add(p);
    }

    /**
     * Replaces the given statistic {@code target} in the list with {@code editedStatistic}.
     * {@code target} must exist in the address book.
     * The statistic identity of {@code editedStatistic} must not be the same as
     * another existing statistic in the address book.
     */
    public void updateStatistic(Statistic target, Statistic editedStatistic) {
        requireNonNull(editedStatistic);

        statisticList.setStatistic(target, editedStatistic);
    }

    /**
     * Removes {@code key} from this {@code StatisticList}.
     * {@code key} must exist in the address book.
     */
    public void removeStatistic(Statistic key) {
        statisticList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return statisticList.asUnmodifiableObservableList().size() + " statisticList";
        // TODO: refine later
    }

    @Override
    public ObservableList<Statistic> getStatisticList() {
        return statisticList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatisticList // instanceof handles nulls
                && statisticList.equals(((StatisticList) other).statisticList));
    }

    @Override
    public int hashCode() {
        return statisticList.hashCode();
    }
}
