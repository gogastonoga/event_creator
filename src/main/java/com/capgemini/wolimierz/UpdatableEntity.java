package com.capgemini.wolimierz;

public interface UpdatableEntity<T> extends GlobalEntity {
    void updateFrom(T dto);
}
