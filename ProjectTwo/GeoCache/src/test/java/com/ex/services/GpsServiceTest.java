package com.ex.services;

import com.ex.dao.GpsDAOImpl_PGR;
import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
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
        Item item = new Item("name","description","image");
        GeoCashe cashe = new GeoCashe(item,"image","gps",new DifficultyLevel(3));
        Mockito.doNothing().when(mockGpsDAO).addCashe(cashe);
        Assert.assertSame(true, service.createNewGeoCashe(cashe));
    }

    @Test
    public void shouldMakeNewCacheFailsToCreate(){
        Item item = new Item("name","description","image");
        GeoCashe cashe = new GeoCashe(item,"image","gps",new DifficultyLevel(3));
        Mockito.doThrow(new RuntimeException()).when(mockGpsDAO).addCashe(cashe);
        Assert.assertSame(false, service.createNewGeoCashe(cashe));
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

    @Test
    public void shouldFindOneCache(){
        GeoCashe tmp = new GeoCashe();
        Mockito.when(mockGpsDAO.findCasheByID(1)).thenReturn(tmp);
        GeoCashe actual = service.findCasheByID(1);
        Assert.assertSame(tmp,actual);
    }

    @Test
    public void shouldFindOneItem(){
        Item tmp = new Item();
        Mockito.when(mockGpsDAO.findItemByID(1)).thenReturn(tmp);
        Item actual = service.findItemByID(1);
        Assert.assertSame(tmp,actual);
    }

    @Test
    public void shouldPlaceItem(){
        GeoCasheHistorys tmp = new GeoCasheHistorys();
        Item tmpItem = new Item();
        boolean success = service.placeItem(tmpItem, tmp);
        Assert.assertTrue(success);
    }

    @Test
    public void shouldRemoveItem(){
        GeoCasheHistorys tmp = new GeoCasheHistorys();
        boolean success = service.removeItem(tmp);
        Assert.assertTrue(success);
    }



}