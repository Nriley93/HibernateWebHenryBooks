
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="booklist")
public class Book { 
    @Id
    @Column(name="bookID")
    private String bookID;
    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="publisher_Code")
    private String pubCode;
    @Column(name="booktype")
    private String booktype;
    @Column(name="price")
    private double price;
    
    public Book() {
        this.bookID = "";
        this.title = "";
        this.author = "";
        this.pubCode = "";
        this.booktype = "";
        this.price = 0;
    }
    
    public String getBookID() {return bookID;}
    public double getPrice() {return price;}
    public String getBooktype() {return booktype;}
    public String getPubCode() {return pubCode;}
    public String getAuthor() {return author;}
    public String getTitle() {return title;}
    
    public void setBookID(String bookID) {this.bookID = bookID;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setPubCode(String pubCode) {this.pubCode = pubCode;}
    public void setBooktype(String booktype) {this.booktype = booktype;}
    public void setPrice(double price) {this.price = price;}
    
    public String isValid() {
        String msg = "";
        if(bookID.isEmpty()){
            msg += "BookID is empty.<br>";
        } else if(title.isEmpty()){
            msg += "Title is empty.<br>";
        } else if(author.isEmpty()) {
            msg += "Author is empty.<br>";
        } else if(pubCode.isEmpty()) {
            msg += "Pulisher is empty.<br>";
        } else if(booktype.isEmpty()) {
            msg += "BookType is empty.<br>";
        } else if(price <= 0) {
            msg += "Price is less than or equal to zero.<br>";
        }
        return msg;
    }
}
