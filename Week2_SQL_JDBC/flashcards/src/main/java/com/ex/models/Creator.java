package com.ex.models;

public class Creator {
  private int id;
  private String creatorName;

  public Creator() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creatorName) {
    this.creatorName = creatorName;
  }

  @Override
  public String toString() {
    return "Creator{" +
      "id=" + id +
      ", creatorName='" + creatorName + '\'' +
      '}';
  }
}
