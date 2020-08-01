package com.homework.sws.repository.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVLineReader {

    List<String> getCSVHeaderLine(Path csvFilePath) throws IOException {
        return Arrays.stream(
                Files.newBufferedReader(csvFilePath)
                        .readLine().
                        split(","))
                .collect(Collectors.toList());
    }

    Stream<List<String>> getCSVDataLinesFrom(Path csvFilePath) throws IOException {
        return Files.newBufferedReader(csvFilePath)
                .lines()
                .skip(1)
                .map(line -> line.split(","))
                .map(Arrays::asList);
    }
}
