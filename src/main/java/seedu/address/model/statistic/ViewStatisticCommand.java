package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.statistic.StatisticModel.PREDICATE_SHOW_ALL_STATISTICS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewStatisticCommand extends CommandStatistic {
    public static final Statistic statistic = Statistic.getInstance();


    public static final String COMMAND_WORD = "viewstatistics";

    public static final String MESSAGE_SUCCESS = statistic.toString();

    @Override
    public CommandResult execute(StatisticModel statisticModel, CommandHistory history) {
        requireNonNull(statisticModel);
        statisticModel.updateFilteredStatisticList(PREDICATE_SHOW_ALL_STATISTICS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
