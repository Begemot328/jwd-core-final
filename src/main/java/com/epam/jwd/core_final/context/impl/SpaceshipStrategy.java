package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.Strategy;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpaceshipStrategy implements Strategy<Spaceship> {
    private static final CharSequence HASH = "#";
    // {1:10,2:9,3:3,4:2}
    private static final String REGEXP = "([0-9]+)\\:([0-9]+)";

    @Override
    public void populate(Collection<Spaceship> result) {
        SpaceshipFactory factory = new SpaceshipFactory();

        File file = new File("src/main/resources" + "/" + ApplicationProperties.getInstance().getInputRootDir()
                + "/" + ApplicationProperties.getInstance().getSpaceshipsFileName());

        Pattern pattern = Pattern.compile(REGEXP);
        Matcher matcher;
        String line;
        String[] array;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(HASH)) {
                    continue;
                }
                array = line.split(";");

                matcher = pattern.matcher(array[2]);
                Map<Role, Short> crew = new HashMap<>();

                while (matcher.find()) {
                    crew.put(Role.resolveRoleById(
                            Integer.valueOf(matcher.group(1))),
                            Short.valueOf(matcher.group(2)));
                }

                result.add(factory.create(array[0], Long.valueOf(array[1]), crew));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
