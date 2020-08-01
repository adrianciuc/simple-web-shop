package com.homework.sws.repository.impl;

import com.homework.sws.model.Category;
import com.homework.sws.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class CategoryRepositoryImpl implements CategoryRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
    private static final String CATEGORY_TABLE = "categories.table";

    private final CSVLineReader csvLineReader;
    private final CSVLineMapper csvLineMapper;

    public CategoryRepositoryImpl(CSVLineReader csvLineReader, CSVLineMapper csvLineMapper) {
        this.csvLineReader = csvLineReader;
        this.csvLineMapper = csvLineMapper;
    }

    public List<Category> getAll() {
        try {
            Path tablePath = Paths.get(System.getProperty("db.path"), CATEGORY_TABLE);
            LOG.debug("Reading category table from file: {}", tablePath);
            Stream<List<String>> csvDataLines = this.csvLineReader.getCSVDataLinesFrom(tablePath);
            List<String> csvHeaderLine = this.csvLineReader.getCSVHeaderLine(tablePath);
            return this.csvLineMapper.mapFrom(csvDataLines, csvHeaderLine, Category.class);
        } catch (Exception e) {
            LOG.error("There was a problem at fetching the categories from DB.", e);
            throw new DataAccessException(e);
        }
    }
}
