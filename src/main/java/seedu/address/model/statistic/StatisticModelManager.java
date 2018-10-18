package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.StatisticListChangedEvent;
import seedu.address.model.UserPrefs;

/**
 * Represents the in-memory statisticModel of the address book data.
 */
public class StatisticModelManager extends ComponentManager implements StatisticModel {
    private static final Logger logger = LogsCenter.getLogger(StatisticModelManager.class);

    private final VersionedStatisticList versionedStatisticList;
    private final FilteredList<Statistic> filteredStatistics;

    /**
     * Initializes a StatisticModelManager with the given statisticList and userPrefs.
     */
    public StatisticModelManager(ReadOnlyStatistics statisticList, UserPrefs userPrefs) {
        super();
        requireAllNonNull(statisticList, userPrefs);

        logger.fine("Initializing with address book: " + statisticList + " and user prefs " + userPrefs);

        versionedStatisticList = new VersionedStatisticList(statisticList);
        filteredStatistics = new FilteredList<>(versionedStatisticList.getStatisticList());
    }

    public StatisticModelManager() {
        this(new StatisticList(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyStatistics newData) {
        versionedStatisticList.resetData(newData);
        indicateStatisticListChanged();
    }

    @Override
    public ReadOnlyStatistics getStatisticList() {
        return versionedStatisticList;
    }

    /** Raises an event to indicate the statisticModel has changed */
    private void indicateStatisticListChanged() {
        raise(new StatisticListChangedEvent(versionedStatisticList));
    }

    @Override
    public boolean hasStatistic(Statistic statistic) {
        requireNonNull(statistic);
        return versionedStatisticList.hasStatistic(statistic);
    }

    @Override
    public void deleteStatistic(Statistic target) {
        versionedStatisticList.removeStatistic(target);
        indicateStatisticListChanged();
    }

    @Override
    public void addStatistic(Statistic statistic) {
        versionedStatisticList.addStatistic(statistic);
        updateFilteredStatisticList(PREDICATE_SHOW_ALL_STATISTICS);
        indicateStatisticListChanged();
    }

    @Override
    public void updateStatistic(Statistic target, Statistic editedStatistic) {
        requireAllNonNull(target, editedStatistic);

        versionedStatisticList.updateStatistic(target, editedStatistic);
        indicateStatisticListChanged();
    }

    //=========== Filtered Statistic List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Statistic} backed by the internal list of
     * {@code versionedStatisticList}
     */
    @Override
    public ObservableList<Statistic> getFilteredStatisticList() {
        return FXCollections.unmodifiableObservableList(filteredStatistics);
    }

    @Override
    public void updateFilteredStatisticList(Predicate<Statistic> predicate) {
        requireNonNull(predicate);
        filteredStatistics.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoStatistics() {
        return versionedStatisticList.canUndo();
    }

    @Override
    public boolean canRedoStatistics() {
        return versionedStatisticList.canRedo();
    }

    @Override
    public void undoStatistics() {
        versionedStatisticList.undo();
        indicateStatisticListChanged();
    }

    @Override
    public void redoStatistics() {
        versionedStatisticList.redo();
        indicateStatisticListChanged();
    }

    @Override
    public void commitStatistics() {
        versionedStatisticList.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof StatisticModelManager)) {
            return false;
        }

        // state check
        StatisticModelManager other = (StatisticModelManager) obj;
        return versionedStatisticList.equals(other.versionedStatisticList)
                && filteredStatistics.equals(other.filteredStatistics);
    }

}
