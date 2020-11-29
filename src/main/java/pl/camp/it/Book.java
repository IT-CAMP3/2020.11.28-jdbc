package pl.camp.it;

public class Book {
    private int id;
    private int author_id;
    private int pieces;
    private String isbn;
    private double price;
    private String title;
    private int category_id;

    public Book(int id, int author_id, int pieces, String isbn, double price, String title, int category_id) {
        this.id = id;
        this.author_id = author_id;
        this.pieces = pieces;
        this.isbn = isbn;
        this.price = price;
        this.title = title;
        this.category_id = category_id;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author_id=" + author_id +
                ", pieces=" + pieces +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
