package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Collections;

public class CollectionJSONWriter {
    private ApplicationProperties properties = ApplicationProperties.getInstance();
    private ApplicationContext context;


    context.retrieveBaseEntityList();

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void write(Collection<? super BaseEntity> collection) {
        String filename = properties.getOutputRootDir() + "/" + properties.getCrewFileName();
        File file = new File(filename);


        try {
            FileOutputStream stream = new FileOutputStream(file);

            collection.stream().map(o -> o.toString()).forEach(s -> stream.write(s));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
