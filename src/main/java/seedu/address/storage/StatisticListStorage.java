package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.statistic.ReadOnlyStatistics;

/**
 * Represents a storage for {@link StatisticListStorage}.
 */
public interface StatisticListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStatisticListFilePath();

    /**
     * Returns StatisticList data as a {@link ReadOnlyStatistics}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStatistics> readStatisticList() throws DataConversionException, IOException;

    /**
     * @see #readStatisticList(Path) ()
     */
    Optional<ReadOnlyStatistics> readStatisticList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStatistics} to the storage.
     * @param statisticList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStatisticList(ReadOnlyStatistics statisticList) throws IOException;

    /**
     * @see #saveStatisticList(ReadOnlyStatistics)
     */
    void saveStatisticList(ReadOnlyStatistics statisticList, Path filePath) throws IOException;

}
