package com.ex.tests;

import com.ex.data.ProductSQLDatabase;
import com.ex.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAOTest extends DAOTest<Product, Integer> {
    @Before
    public void init() {
        dao = new ProductSQLDatabase(dc);
    }

    @Test
    public void shouldFindProduct() {
        Product expected = new Product(2, "Fish Nibbles", "Fish Food", 399, 5);

        super.shouldFindObject(expected, 2);
    }

    @Test
    public void shouldFindNoProduct() {
        super.shouldFindNoObject(123);
    }

    @Test
    public void shouldAddAndRemoveProduct() {
        int id = 3;
        Product newProduct = new Product(id, "Generic Cat Bites", "Cat Food", 1234, 56);

        super.shouldAddAndRemoveObject(newProduct, id);
    }

    @Test
    public void shouldAddAndRemoveProductNonExistentType() {
        int id = 4;
        Product prod = new Product(id, "asdf", "ghjkl", 100, 10);

        super.shouldAddAndRemoveObject(prod, id);
    }

    @Test
    public void shouldUpdateProduct() {
        int id = 1;
        Product newProd = new Product(id, "Super Tasty Dog Dessert!", "Cat Food", 4900, 1);
        super.shouldUpdateObject(newProd, id, id);
    }

    @Test
    public void shouldUpdateProductNonExistentType() {
        int id = 1;
        Product newProd = new Product(id, "Kind of Tasty Food", "Human Food", 12345, 10);
        super.shouldUpdateObject(newProd, id, id);
    }

    @Test
    @Override
    public void shouldFindAllObjects() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product(0, "Tasty Dog Food", "Dog Food", 599, 20));
        expected.add(new Product(1, "Less Tasty Dog...Food?", "Dog Food", 49, 300));
        expected.add(new Product(2, "Fish Nibbles", "Fish Food", 399, 5));

        List<Product> actual = dao.findAll();
        Collections.sort(actual, (a, b) -> Integer.compare(a.getProductID(), b.getProductID()));

        Assert.assertArrayEquals("Didn't return correct list of products!", expected.toArray(), actual.toArray());
    }
}
