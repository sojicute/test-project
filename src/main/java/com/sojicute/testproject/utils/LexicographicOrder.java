package com.sojicute.testproject.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LexicographicOrder {

    private String[] convertToStringArray(String str) {
        String delimiters = "\\s+|,\\s*|\\.\\s*";
        return str.trim().split(delimiters);
    }
    public List<String> inArray(String str1, String str2) {
        String[] array1 = convertToStringArray(str1);
        String[] array2 = convertToStringArray(str2);

        return Stream.of(array1)
                .filter(word -> Stream.of(array2).anyMatch(e -> e.contains(word)))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
