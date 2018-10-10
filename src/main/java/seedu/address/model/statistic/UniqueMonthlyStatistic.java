package seedu.address.model.statistic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.statistic.exceptions.StatisticNotFoundException;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class UniqueMonthlyStatistic implements Iterable<Statistic> {
    private final ObservableList<Statistic> internalList = FXCollections.observableArrayList();

    public void add(Statistic toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    public void remove(Statistic toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StatisticNotFoundException();
        }
    }

    public ObservableList<Statistic> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Statistic> iterator() { return internalList.iterator(); }

    @Override
    public int hashCode() { return internalList.hashCode(); }
}
