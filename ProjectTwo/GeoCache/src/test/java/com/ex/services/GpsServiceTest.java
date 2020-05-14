package com.ex.services;

import com.ex.dao.GpsDAOImpl_PGR;
import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class GpsServiceTest {
    List<GeoCashe> tmpAll = new ArrayList<>();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    GpsDAOImpl_PGR mockGpsDAO;

    @InjectMocks
    GpsService service;

    @Before
    public void setUp() throws Exception {
        service = new GpsService(mockGpsDAO);
        List<GeoCashe> tmpAll= new ArrayList<>();
        GeoCashe tmp1 = new GeoCashe(new Item(), "image1", "loc1", new DifficultyLevel(1));
        GeoCashe tmp2 = new GeoCashe(new Item(), "image2", "loc2", new DifficultyLevel(2));
        GeoCashe tmp3 = new GeoCashe(new Item(), "image3", "loc3", new DifficultyLevel(3));
        tmpAll.add(tmp1);
        tmpAll.add(tmp2);
        tmpAll.add(tmp3);

    }

    @Test
    public void shouldMakeNewCache(){
//        GeoCashe cashe = new GeoCashe(3,"image","gps",4);
//        Mockito.when(mockGpsDAO.addCashe(cashe)).then();
    }

    @Test
    public void shouldMakeNewItem(){
        Item item = new Item("name","description","image");
        boolean success = service.createNewItem(item);
        Assert.assertTrue(success);
    }

    @Test
    public void shouldGetAllCaches(){
        Mockito.when(mockGpsDAO.getAllCashes()).thenReturn(tmpAll);
        List<GeoCashe> cashes = service.getAllCashes();
        Assert.assertArrayEquals(tmpAll.toArray(),cashes.toArray());
    }




}