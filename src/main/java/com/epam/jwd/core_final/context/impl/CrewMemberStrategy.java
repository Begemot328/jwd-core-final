package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.Strategy;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CrewMemberStrategy implements Strategy<CrewMember> {
    private static final CharSequence HASH = "#";
    // 4,Davey Bentley,2;
    private static final String REGEXP = "([0-9]+)\\:([0-9]+)";

    @Override
    public void populate(Collection<CrewMember> result) {
        CrewMemberFactory factory = new CrewMemberFactory();

        File file = new File("src/main/resources" + "/" + ApplicationProperties.getInstance().getInputRootDir()
                + "/" + ApplicationProperties.getInstance().getCrewFileName());

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

                Stream<String> stream = Arrays.stream(array);
                stream.map(s -> {
                    String[] array1  = s.split(",");
                    CrewMemberFactory factory1 = new CrewMemberFactory();
                    return factory.create(Role.resolveRoleById(Integer.parseInt(array1[0])),
                            array1[1], Rank.resolveRankById(Integer.parseInt(array1[2])));
                })
                        .forEach(result::add);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
