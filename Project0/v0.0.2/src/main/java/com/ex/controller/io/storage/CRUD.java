package com.ex.controller.io.storage;

import java.io.Serializable;

public interface CRUD {
    public void create(Serializable object, String filePath);
    public Object read(String filePath);
    //TODO: update and delete methods
}
