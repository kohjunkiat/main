package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.statistic.ReadOnlyStatistics;
import seedu.address.model.statistic.Statistic;
import seedu.address.model.statistic.StatisticList;

/**
 * An Immutable StatisticList that is serializable to XML format
 */
@XmlRootElement(name = "statisticlist")
public class XmlSerializableStatisticList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate statistic(s).";

    @XmlElement
    private List<XmlAdaptedStatistic> statistics;

    /**
     * Creates an empty XmlSerializableStatisticList.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableStatisticList() {
        statistics = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableStatisticList(ReadOnlyStatistics src) {
        this();
        statistics.addAll(src.getStatisticList().stream().map(XmlAdaptedStatistic::new).collect(Collectors.toList()));
    }

    /**
     * Converts this addressbook into the statisticModel's {@code StatisticList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedStatistic}.
     */
    public StatisticList toModelType() throws IllegalValueException {
        StatisticList statisticList = new StatisticList();
        for (XmlAdaptedStatistic p : statistics) {
            Statistic statistic = p.toModelType();
            if (statisticList.hasStatistic(statistic)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            statisticList.addStatistic(statistic);
        }
        return statisticList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableStatisticList)) {
            return false;
        }
        return statistics.equals(((XmlSerializableStatisticList) other).statistics);
    }
}
