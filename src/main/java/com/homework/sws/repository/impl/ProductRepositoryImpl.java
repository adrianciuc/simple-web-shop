package com.homework.sws.repository.impl;

import com.homework.sws.model.Product;
import com.homework.sws.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private static final String PRODUCTS_TABLE = "products.table";

    private final CSVLineReader csvLineReader;
    private final CSVLineMapper csvLineMapper;

    public ProductRepositoryImpl(CSVLineReader csvLineReader, CSVLineMapper csvLineMapper) {
        this.csvLineReader = csvLineReader;
        this.csvLineMapper = csvLineMapper;
    }

    @Override
    public List<Product> getForCategory(Long categoryId) {
        try {
            Path tablePath = Paths.get(System.getProperty("db.path"), PRODUCTS_TABLE);
            LOG.debug("Reading products table from file: {}", tablePath);
            Stream<List<String>> csvDataLines = this.csvLineReader.getCSVDataLinesFrom(tablePath);
            Stream<List<String>> productsOfCategory =
                    csvDataLines.filter(productAsListOfStrings -> isProductOfCategory(categoryId, productAsListOfStrings));
            List<String> csvHeaderLine = this.csvLineReader.getCSVHeaderLine(tablePath);
            return this.csvLineMapper.mapFrom(productsOfCategory, csvHeaderLine, Product.class);
        } catch (Exception e) {
            LOG.error("There was a problem at fetching the products of category with id {} from DB.", categoryId, e);
            throw new DataAccessException(e);
        }
    }

    private boolean isProductOfCategory(Long categoryId, List<String> productAsListOfStrings) {
        return productAsListOfStrings.get(productAsListOfStrings.size() - 1).endsWith(categoryId.toString());
    }
}
