package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.book.exceptions.BookNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A statistic is considered unique by comparing using {@code Statistic#isSameStatistic(Statistic)}.
 * As such, adding and updating of persons uses Statistic#isSameStatistic(Statistic) for equality
 * so as to ensure that the statistic being added or updated is unique in terms of identity in
 * the UniqueStatisticList. However, the removal of a statistic uses Statistic#equals(Object) so
 * as to ensure that the statistic with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Statistic#isSameStatistic(Statistic)
 */
public class UniqueStatisticList implements Iterable<Statistic> {

    private final ObservableList<Statistic> internalStatisticList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent statistic as the given argument.
     */
    public boolean contains(Statistic toCheck) {
        requireNonNull(toCheck);
        return internalStatisticList.stream().anyMatch(toCheck::isSameStatistic);
    }

    /**
     * Adds a statistic to the list.
     * The statistic must not already exist in the list.
     */
    public void add(Statistic toAdd) {
        requireNonNull(toAdd);
        internalStatisticList.add(toAdd);
    }

    /**
     * Replaces the statistic {@code target} in the list with {@code editedStatistic}.
     * {@code target} must exist in the list.
     * The statistic identity of {@code editedStatistic} must not be the same as another existing statistic in the list.
     */
    public void setStatistic(Statistic target, Statistic editedStatistic) {
        requireAllNonNull(target, editedStatistic);

        int index = internalStatisticList.indexOf(target);
        if (index == -1) {
            throw new BookNotFoundException();
        }

        if (!target.isSameStatistic(editedStatistic) && contains(editedStatistic)) {
            throw new BookNotFoundException();
        }

        internalStatisticList.set(index, editedStatistic);
    }

    public void setStatistic(UniqueStatisticList replacement) {
        requireNonNull(replacement);
        internalStatisticList.setAll(replacement.internalStatisticList);
    }

    /**
     * Replaces the contents of this list with {@code statistics}.
     * {@code statistics} must not contain duplicate statistics.
     */
    public void setStatistics(List<Statistic> statistics) {
        requireAllNonNull(statistics);
        if (!statisticsAreUnique(statistics)) {
            //    throw new BookNotFoundException();
        }

        internalStatisticList.setAll(statistics);
    }
    /**
     * Removes the equivalent statistic from the list.
     * The statistic must exist in the list.
     */
    public void remove(Statistic toRemove) {
        requireNonNull(toRemove);
        if (!internalStatisticList.remove(toRemove)) {
            throw new BookNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Statistic> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalStatisticList);
    }

    @Override
    public Iterator<Statistic> iterator() {
        return internalStatisticList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStatisticList // instanceof handles nulls
                && internalStatisticList.equals(((UniqueStatisticList) other).internalStatisticList));
    }

    @Override
    public int hashCode() {
        return internalStatisticList.hashCode();
    }

    /**
     * Returns true if {@code statistics} contains only unique statistics.
     */
    private boolean statisticsAreUnique(List<Statistic> statistics) {
        for (int i = 0; i < statistics.size() - 1; i++) {
            for (int j = i + 1; j < statistics.size(); j++) {
                if (statistics.get(i).isSameStatistic(statistics.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
