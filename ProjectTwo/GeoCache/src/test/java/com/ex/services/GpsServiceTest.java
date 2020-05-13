package com.ex.services;

import com.ex.dao.GpsDAOImpl_PGR;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GpsServiceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    GpsDAOImpl_PGR mockGpsDAO;

    @InjectMocks
    GpsService service;

    @Before
    public void setUp() throws Exception {
        service = new GpsService(mockGpsDAO);
    }





}