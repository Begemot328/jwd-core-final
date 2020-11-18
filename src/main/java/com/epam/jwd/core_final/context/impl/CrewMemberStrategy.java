package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.Strategy;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.domain.CrewMemberFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Stream;

public class CrewMemberStrategy implements Strategy<CrewMember> {
    private static final CharSequence HASH = "#";
    private static final String COMMA = ",";
    private static final String SEMICOLON = ";";
    private static final String SLASH = "/";

    @Override
    public void populate(Collection<CrewMember> result) {
        CrewMemberFactory factory = new CrewMemberFactory();

        File file = new File(ApplicationProperties.getInstance().getResorcesDir()
                + SLASH + ApplicationProperties.getInstance().getInputRootDir()
                + SLASH + ApplicationProperties.getInstance().getCrewFileName());

        String line;
        String[] array;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(HASH)) {
                    continue;
                }
                array = line.split(SEMICOLON);

                Stream<String> stream = Arrays.stream(array);
                stream.map(s -> {
                    String[] arrayInner = s.split(COMMA);

                    return factory.create(Role.resolveRoleById(Integer.parseInt(arrayInner[0])),
                            arrayInner[1], Rank.resolveRankById(Integer.parseInt(arrayInner[2])));
                })
                        .forEach(result::add);
            }
        } catch (FileNotFoundException e) {
            throw new InvalidStateException(e.getMessage());
        }
    }
}
