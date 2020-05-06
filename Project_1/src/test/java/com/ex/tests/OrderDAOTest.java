package com.ex.tests;

import com.ex.data.OrderSQLDatabase;
import com.ex.models.Account;
import com.ex.models.Order;
import com.ex.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderDAOTest extends DAOTest<Order, Integer> {
    @Before
    public void init() {
        dao = new OrderSQLDatabase(dc);
    }

    @Test
    public void shouldFindOrder() {
        List<Product> orderProducts = new ArrayList<>();
        orderProducts.add(new Product(0, "Tasty Dog Food", "Dog Food", 499, 10));
        orderProducts.add(new Product(2, "Fish Nibbles", "Fish Food", 300, 2));

        Account customer = new Account("Perry", "abc@123.com", "correct horse battery staple", false, false);

        Order order = new Order(0, customer, orderProducts, 0);
        super.shouldFindObject(order, 0);
    }

    @Test
    public void shouldFindNoOrder() {
        super.shouldFindNoObject(10);
    }

    @Test
    public void shouldAddAndRemoveOrder() {
        Account acc = new Account("Bob Doe", "bob@doe", "admin", false, false);
        List<Product> prods = new ArrayList<>();
        prods.add(new Product(0, "Tasty Dog Food", "Dog Food", 123,45));
        prods.add(new Product(1, "Less Tasty Dog...Food?", "Dog Food", 456, 78));

        Order o = new Order(123, acc, prods, Order.Status.PROCESSING);
        super.shouldAddAndRemoveObject(o, 123);
    }

    @Test
    public void shouldUpdateOrderStatus() {
        int orderId = 1;
        Order o = dao.findByID(orderId);
        Assert.assertNotNull("Couldn't find order!", o);

        o.setStatusCode(Order.Status.DELIVERED);

        super.shouldUpdateObject(o, orderId, orderId);
    }

    @Test
    public void shouldUpdateOrderProducts() {
        int orderId = 1;
        Order o = dao.findByID(orderId);
        Assert.assertNotNull("Couldn't find order!", o);

        List<Product> prods = o.getOrderProducts();
        prods.add(new Product(2, "Fish Nibbles", "Fish Food", 398, 1));
        o.setOrderProducts(prods);

        super.shouldUpdateObject(o, orderId, orderId);
    }

    @Test
    @Override
    public void shouldFindAllObjects() {
        // Oh man, constructing these Orders is such a huge pain
        Account acc1 = new Account("Perry", "abc@123.com", "correct horse battery staple", false, false);
        Account acc3 = new Account("Spider-Man", "(redacted)@(top secret)", "password", false, false);

        List<Product> orders0 = new ArrayList<>();
        orders0.add(new Product(0, "Tasty Dog Food", "Dog Food", 499, 10));
        orders0.add(new Product(2, "Fish Nibbles", "Fish Food", 300, 2));
        List<Product> orders1 = new ArrayList<>();
        orders1.add(new Product(0, "Tasty Dog Food", "Dog Food", 1599, 1));
        List<Product> orders2 = new ArrayList<>();
        orders2.add(new Product(1, "Less Tasty Dog...Food?", "Dog Food", 10000, 500));
        orders2.add(new Product(2, "Fish Nibbles", "Fish Food", 99, 50));

        Order order0 = new Order(0, acc1, orders0, 0);
        Order order1 = new Order(1, acc1, orders1, 1);
        Order order2 = new Order(2, acc3, orders2, 2);

        List<Order> expected = new ArrayList<>();
        expected.add(order0);
        expected.add(order1);
        expected.add(order2);

        List<Order> actual = dao.findAll();
        actual.sort(Comparator.comparingInt(Order::getOrderConfirmation));
        for (Order o: actual)
            o.getOrderProducts().sort(Comparator.comparingInt(Product::getProductID));

        Assert.assertArrayEquals("Didn't return all orders!", expected.toArray(), actual.toArray());
    }
}
