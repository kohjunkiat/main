package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.events.model.StatisticListChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.UserPrefs;
import seedu.address.model.statistic.ReadOnlyStatistics;

/**
 * API of the StatisticStorage component
 */
public interface StatisticStorage extends StatisticListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    Path getStatisticListFilePath();

    @Override
    Optional<ReadOnlyStatistics> readStatisticList() throws DataConversionException, IOException;

    @Override
    void saveStatisticList(ReadOnlyStatistics statisticList) throws IOException;

    /**
     * Saves the current version of the Address Book to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleStatisticListChangedEvent(StatisticListChangedEvent rlce);
}
