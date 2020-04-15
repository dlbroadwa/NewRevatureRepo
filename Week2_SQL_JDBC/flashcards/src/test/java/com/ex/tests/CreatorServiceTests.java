package com.ex.tests;

import com.ex.clients.CreatorService;
import com.ex.data.Repository;
import com.ex.models.Creator;
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

public class CreatorServiceTests {

  CreatorService service;
  List<Creator> creators = new ArrayList();

  @Mock
  Repository<Creator, Integer> repo;

  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    service = new CreatorService(repo);
    Creator tmp = new Creator();
    tmp.setId(1);
    tmp.setCreatorName("august");
    creators.add(tmp);
  }

  @Test
  public void shouldReturnAllCreators() {
    // ask the service for all creators
    // assert that all creators are returned
    Mockito.when(repo.findAll()).thenReturn(creators);
    List<Creator> actual = service.getAllCreators();
    Assert.assertArrayEquals("Didn't return expected creators", creators.toArray(), actual.toArray());
  }
}
