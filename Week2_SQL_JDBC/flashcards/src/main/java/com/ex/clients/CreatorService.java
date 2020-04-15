package com.ex.clients;

import com.ex.data.Repository;
import com.ex.models.Creator;

import java.util.List;

public class CreatorService {
  private Repository<Creator, Integer> repo;

  public CreatorService(Repository<Creator, Integer> repo) {
    this.repo = repo;
  }

  public List<Creator> getAllCreators() {
    // this method may do some validation and authorization before handling
    // the request directly
    return this.repo.findAll();
  }
}
