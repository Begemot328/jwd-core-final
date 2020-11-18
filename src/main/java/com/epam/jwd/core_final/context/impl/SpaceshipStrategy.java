package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.Strategy;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.domain.SpaceshipFactory;

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
    private static final String REGEXP = "([0-9]+)\\:([0-9]+)";
    private static final String SLASH = "/";
    private static final String SEMICOLON = ";";

    @Override
    public void populate(Collection<Spaceship> result) {
        SpaceshipFactory factory = new SpaceshipFactory();
        Pattern pattern = Pattern.compile(REGEXP);
        Matcher matcher;
        String line;
        String[] array;

        File file = new File(ApplicationProperties.getInstance().getResorcesDir()
                + SLASH + ApplicationProperties.getInstance().getInputRootDir()
                + SLASH + ApplicationProperties.getInstance().getSpaceshipsFileName());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(HASH)) {
                    continue;
                }
                array = line.split(SEMICOLON);

                matcher = pattern.matcher(array[2]);
                Map<Role, Short> crew = new HashMap<>();

                while (matcher.find()) {
                    crew.put(Role.resolveRoleById(
                            Integer.parseInt(matcher.group(1))),
                            Short.valueOf(matcher.group(2)));
                }

                result.add(factory.create(array[0], Long.valueOf(array[1]), crew));
            }
        } catch (FileNotFoundException e) {
            throw new InvalidStateException(e.getMessage());
        }
    }
}
