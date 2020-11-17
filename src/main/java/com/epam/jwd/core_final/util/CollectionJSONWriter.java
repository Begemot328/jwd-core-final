package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.logging.Logger;

public class CollectionJSONWriter {

    private static final ApplicationProperties properties = ApplicationProperties.getInstance();
    private static final String FILENAME = "src/main/resources" + "/" + properties.getOutputRootDir() + "/" + "OBJECT" + ".json";
    private static final String OBJECT = "OBJECT";
    private static String filename = FILENAME;

    public static void write(Collection<? super BaseEntity> collection) throws InvalidStateException {

        Object o =  collection.stream().findAny().get();
        if (o == null) {
            return;
        } else {
            if (o instanceof CrewMember) {
                filename = FILENAME.replace(OBJECT, properties.getCrewFileName());
            } else if (o instanceof Spaceship) {
                filename = FILENAME.replace(OBJECT, properties.getSpaceshipsFileName());
            } else if (o instanceof FlightMission) {
                filename = FILENAME.replace(OBJECT, properties.getMissionsFileName());
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filename), collection);
        } catch (IOException e) {
            ApplicationContext.getLoggerInstance().error(e);
            throw new InvalidStateException(e.getMessage());
        }
    }
}
