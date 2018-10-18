package seedu.address.model.statistic;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class CommandStatistic {
    /**
     * Executes the command and returns the result message.
     *
     * @param statisticModel {@code Model} which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(StatisticModel statisticModel, CommandHistory history) throws CommandException;
}
