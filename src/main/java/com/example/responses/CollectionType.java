package com.example.responses;

import java.util.List;

public class CollectionType<T> {

    List<T> records;

    public List<T> getRecords() {
        return records;
    }

    public CollectionType<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public CollectionType(List<T> records) {
        this.records = records;
    }
}
