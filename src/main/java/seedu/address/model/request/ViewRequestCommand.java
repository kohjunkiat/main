package seedu.address.model.request;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.request.RequestModel.PREDICATE_SHOW_ALL_REQUESTS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewRequestCommand extends CommandSecondary {

    public static final String COMMAND_WORD = "viewrequests";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(RequestModel requestModel, CommandHistory history) {
        requireNonNull(requestModel);
        requestModel.updateFilteredRequestList(PREDICATE_SHOW_ALL_REQUESTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
