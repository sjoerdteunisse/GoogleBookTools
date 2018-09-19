package com.AXR.DomainLayer;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("id")
    private String id;

    @SerializedName("volumeInfo")
    private BookDetails bookDetail;

    public BookDetails getBookDetail() {
        return bookDetail;
    }
}
