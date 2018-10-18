package seedu.address.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.BookInventoryParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.request.CommandSecondary;
import seedu.address.model.request.Request;
import seedu.address.model.request.RequestListParser;
import seedu.address.model.request.RequestModel;
import seedu.address.model.statistic.CommandStatistic;
import seedu.address.model.statistic.StatisticModel;
import seedu.address.model.statistic.StatisticParser;



/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final RequestModel requestModel;
    private final StatisticModel statisticModel;
    private final CommandHistory history;
    private final BookInventoryParser bookInventoryParser;
    private final RequestListParser requestListParser;
    private final StatisticParser statisticParser;

    public LogicManager(Model model, RequestModel requestModel, StatisticModel statisticModel) {
        this.model = model;
        this.requestModel = requestModel;
        this.statisticModel = statisticModel;
        history = new CommandHistory();
        bookInventoryParser = new BookInventoryParser();
        requestListParser = new RequestListParser();
        statisticParser = new StatisticParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        if (commandText.equals("viewstatistics")) {
            CommandStatistic command = statisticParser.parseCommandStatistic(commandText);
            history.add(commandText);
            return command.execute(statisticModel, history);
        } else if (commandText.equals("viewrequests") || commandText.toLowerCase().contains("request")) {
            CommandSecondary command = requestListParser.parseCommandRequest(commandText);
            history.add(commandText);
            return command.execute(requestModel, history);
        } else {
            try {
                Command command = bookInventoryParser.parseCommand(commandText);
                return command.execute(model, history);
            } finally {
                history.add(commandText);
            }
        }
    }

    @Override
    public ObservableList<Book> getFilteredBookList() {
        return model.getFilteredBookList();
    }

    @Override
    public ObservableList<Request> getFilteredRequestList() {
        return requestModel.getFilteredRequestList();
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
