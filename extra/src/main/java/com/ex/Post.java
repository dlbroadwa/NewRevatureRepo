package com.ex;

public class Post {
  public int id;
  public int postId;
  public String name;
  public String email;
  public String body;

  @Override
  public String toString() {
    return "Post{" +
      "id=" + id +
      ", postId=" + postId +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", body='" + body + '\'' +
      '}';
  }
}
