package com.ex.controller.storage;

import java.io.Serializable;

public interface CRUD {
    void create(Serializable object, String filePath);
    Object read(String filePath);
    //TODO: update and delete methods
}
