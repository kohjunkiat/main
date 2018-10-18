package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.statistic.ReadOnlyStatistics;

/**
 * A class to access StatisticList data stored as an xml file on the hard disk.
 */
public class XmlStatisticListStorage implements StatisticListStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlStatisticListStorage.class);

    private Path filePath;

    public XmlStatisticListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStatisticListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStatistics> readStatisticList() throws DataConversionException, IOException {
        return readStatisticList(filePath);
    }

    /**
     * Similar to {@link #readStatisticList()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStatistics> readStatisticList(Path filePath) throws DataConversionException,
            FileNotFoundException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("StatisticList file " + filePath + " not found");
            return Optional.empty();
        }

        XmlSerializableStatisticList xmlAddressBook = XmlFileStorage.loadStatisticsFromSaveFile(filePath);
        try {
            return Optional.of(xmlAddressBook.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStatisticList(ReadOnlyStatistics statisticList) throws IOException {
        saveStatisticList(statisticList, filePath);
    }

    /**
     * Similar to {@link #saveStatisticList(ReadOnlyStatistics)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveStatisticList(ReadOnlyStatistics statisticList, Path filePath) throws IOException {
        requireNonNull(statisticList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        XmlFileStorage.saveStatisticToFile(filePath, new XmlSerializableStatisticList(statisticList));
    }

}
