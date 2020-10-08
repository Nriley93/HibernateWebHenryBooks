
package business;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="bookinv")
public class Inventory implements Serializable {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final long id;
    @Column(name="storeID")
    private int storeID;
    @Column(name="onHand")
    private int OnHand;
    @Column(name="bookID")
    private String bookID;
    @OneToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="bookID",insertable=false,updatable=false)
    private final Book book;
    
    public Inventory() {
        this.id = 0;
        this.storeID = 0;
        this.OnHand = 0;
        this.bookID = "";
        this.book = null;
    }

    public int getStoreID() {return this.storeID;}
    public String getBookID() {return this.bookID;}
    public int getOnHand() {return this.OnHand;}
    public Book getBook() {return this.book;}

    public void setStoreID(int StoreID) {this.storeID = StoreID;}
    public void setOnHand(int OnHand) {this.OnHand = OnHand;}
    public void setBookID(String BookID) {this.bookID = BookID;}
}
