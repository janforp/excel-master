package com.janita.excel.common.entity;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Janita on 2017/6/12 0012- 上午 10:38
 * 该类是：
 */
public class Book {

    private String bookId;

    private String bookName;

    private double price;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book(String bookId, String bookName, double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
    }

    public Book() {
    }


    public static List<Book> getBookList() {
        List<Book> bookList = Arrays.asList(
                new Book("bookId1","bookName1",12.2),
                new Book("bookId2","bookName2",12.3));
        return bookList;
    }
}
