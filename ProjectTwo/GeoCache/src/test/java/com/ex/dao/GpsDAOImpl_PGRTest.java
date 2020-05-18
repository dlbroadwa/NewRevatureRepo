package com.ex.dao;

import com.ex.model.GeoCashe;
import com.ex.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GpsDAOImpl_PGRTest {
    private GpsDAO dao;

    @Before
    public void init(){
        dao = new GpsDAOImpl_PGR();
    }
    @Test
    public void shouldGetAllCashes() {
        List<GeoCashe> tmp = dao.getAllCashes();
        Assert.assertNotNull(tmp);
    }

    @Test
    public void shouldFindCasheByID() {
        GeoCashe tmp = dao.findCasheByID(18);
        Assert.assertEquals("{lat: -25.363, lng: 131.044}",tmp.getGPSLocation());
    }

    @Test
    public void shouldFindItemByID() {
        Item tmp = dao.findItemByID(2);
        Assert.assertEquals("lighter",tmp.getName());

    }
}