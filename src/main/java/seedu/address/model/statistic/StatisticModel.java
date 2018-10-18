package seedu.address.model.statistic;

import java.util.function.Predicate;

import javafx.collections.ObservableList;

/**
 * The API of the StatisticModel component.
 */
public interface StatisticModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Statistic> PREDICATE_SHOW_ALL_STATISTICS = unused -> true;

    /** Clears existing backing statisticModel and replaces with the provided new data. */
    void resetData(ReadOnlyStatistics newData);

    /** Returns the StatisticList */
    ReadOnlyStatistics getStatisticList();

    /**
     * Returns true if a statistic with the same identity as {@code statistic} exists in the address book.
     */
    boolean hasStatistic(Statistic statistic);

    /**
     * Deletes the given statistic.
     * The statistic must exist in the address book.
     */
    void deleteStatistic(Statistic target);

    /**
     * Adds the given statistic.
     * {@code statistic} must not already exist in the address book.
     */
    void addStatistic(Statistic statistic);

    /**
     * Replaces the given statistic {@code target} with {@code editedStatistic}.
     * {@code target} must exist in the address book.
     * The statistic identity of {@code editedStatistic} must not be
     * the same as another existing statistic in the address book.
     */
    void updateStatistic(Statistic target, Statistic editedStatistic);

    /** Returns an unmodifiable view of the filtered statistic list */
    ObservableList<Statistic> getFilteredStatisticList();

    /**
     * Updates the filter of the filtered statistic list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStatisticList(Predicate<Statistic> predicate);

    /**
     * Returns true if the statisticModel has previous address book states to restore.
     */
    boolean canUndoStatistics();

    /**
     * Returns true if the statisticModel has undone address book states to restore.
     */
    boolean canRedoStatistics();

    /**
     * Restores the statisticModel's address book to its previous state.
     */
    void undoStatistics();

    /**
     * Restores the statisticModel's address book to its previously undone state.
     */
    void redoStatistics();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitStatistics();
}
