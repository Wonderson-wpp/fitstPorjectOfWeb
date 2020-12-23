package com.peng.bookstore.book.domain;

public class Book {
    private String bid;
    private String bname;
    private double price;
    private String author;
    private String image;
    private String cid;

    @Override
    public String toString() {
        return "book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", img='" + image + '\'' +
                ", cid=" + cid +
                '}';
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
