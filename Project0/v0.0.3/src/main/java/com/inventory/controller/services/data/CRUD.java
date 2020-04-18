package com.inventory.controller.services.data;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public abstract class CRUD<T> {
    public abstract void create(int connIndex, @NotNull T t) throws SQLException;
    public abstract List<T> read(int connIndex) throws SQLException;
    public abstract  void update(int connIndex, @NotNull T t, @NotNull T newT) throws SQLException;
    public abstract  void delete(int connIndex, @NotNull T t) throws SQLException;
}
