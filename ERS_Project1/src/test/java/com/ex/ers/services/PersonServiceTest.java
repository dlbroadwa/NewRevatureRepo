package com.ex.ers.services;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.models.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonServiceTest {
    PersonService service;
    List<Person> people = new ArrayList();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    DAOs<Person> mockPersonDAO;

    @Before
    public void setUp() throws Exception {
        service = new PersonService();
        Person tmp1 = new Person();
        Person tmp2 = new Person();
        Person tmp3 = new Person();
        tmp1.setUsername("username");
        tmp1.setPw("pass");
        tmp2.setUsername("next user");
        tmp2.setPw("anotherpass");
        tmp3.setUsername("bloke");
        tmp3.setPw("9010");


        people.add(tmp1);
        people.add(tmp2);
        people.add(tmp3);

    }

    @Test
    public void shouldCheckForExistingUsername() {
        Person tmp1 = new Person();
        tmp1.setUsername("username");
        tmp1.setPw("pass");

        Mockito.when(mockPersonDAO.findByName("username")).thenReturn(tmp1);
        int actual = service.legitName("username");
        Assert.assertEquals(1,actual);
    }

    @Test
    public void shouldLoginPerson() {
        Person tmp1 = new Person();
        tmp1.setUsername("username");
        tmp1.setPw("pass");

        Mockito.when(mockPersonDAO.findByName("username")).thenReturn(tmp1);
        int actual = service.loginPerson("username","pass");
        Assert.assertEquals(1,actual);
    }

    @Test
    public void shouldGetAll(){
        Mockito.when(mockPersonDAO.findAll()).thenReturn(people);
        List<Person> actual =service.getAllEmployees();
        Assert.assertArrayEquals(people.toArray(),actual.toArray());
    }
}