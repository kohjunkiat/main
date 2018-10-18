package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.statistic.ReadOnlyStatistics;

/** Indicates the BookInventory in the model has changed*/
public class StatisticListChangedEvent extends BaseEvent {

    public final ReadOnlyStatistics dataStatistic;

    public StatisticListChangedEvent(ReadOnlyStatistics dataStatistic) {
        this.dataStatistic = dataStatistic;
    }

    @Override
    public String toString() {
        return "number of persons ";
    }
}
