package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {
    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final String fileRefreshRate;
    private final String dateTimeFormat;
    private final static ApplicationProperties INSTANCE = new ApplicationProperties();

    {
        PropertyReaderUtil.loadProperties();
        inputRootDir = PropertyReaderUtil.getPropery("inputRootDir");
        outputRootDir = PropertyReaderUtil.getPropery("outputRootDir");
        crewFileName = PropertyReaderUtil.getPropery("crewFileName");
        missionsFileName = PropertyReaderUtil.getPropery("missionsFileName");
        spaceshipsFileName = PropertyReaderUtil.getPropery("spaceshipsFileName");
        fileRefreshRate = PropertyReaderUtil.getPropery("fileRefreshRate");
        dateTimeFormat = PropertyReaderUtil.getPropery("dateTimeFormat");
    }

    private ApplicationProperties() {}

    public static ApplicationProperties getInstance() {
        return INSTANCE;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public String getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
