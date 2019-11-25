package com.example.ObservablesWithJust.model;

public class model {

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    private String bookName;

    public model(String bookName) {
        this.bookName = bookName;
    }

}
