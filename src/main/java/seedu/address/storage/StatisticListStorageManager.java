package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.StatisticListChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.UserPrefs;
import seedu.address.model.statistic.ReadOnlyStatistics;

/**
 * Manages storage of StatisticList data in local storage.
 */
public class StatisticListStorageManager extends ComponentManager implements StatisticStorage {

    private static final Logger logger = LogsCenter.getLogger(StatisticListStorageManager.class);
    private StatisticListStorage statisticListStorage;
    private UserPrefsStorage userPrefsStorage;


    public StatisticListStorageManager(StatisticListStorage statisticListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.statisticListStorage = statisticListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ StatisticList methods ==============================

    @Override
    public Path getStatisticListFilePath() {
        return statisticListStorage.getStatisticListFilePath();
    }

    @Override
    public Optional<ReadOnlyStatistics> readStatisticList() throws DataConversionException, IOException {
        return readStatisticList(statisticListStorage.getStatisticListFilePath());
    }

    @Override
    public Optional<ReadOnlyStatistics> readStatisticList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return statisticListStorage.readStatisticList(filePath);
    }

    @Override
    public void saveStatisticList(ReadOnlyStatistics addressBook) throws IOException {
        saveStatisticList(addressBook, statisticListStorage.getStatisticListFilePath());
    }

    @Override
    public void saveStatisticList(ReadOnlyStatistics statisticList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        statisticListStorage.saveStatisticList(statisticList, filePath);
    }


    @Override
    @Subscribe
    public void handleStatisticListChangedEvent(StatisticListChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveStatisticList(event.dataStatistic);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }
}
