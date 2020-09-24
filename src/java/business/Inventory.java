
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
    private int StoreID;
    @Column(name="OnHand")
    private int OnHand;
    @Column(name="BookID")
    private String BookID;
    @OneToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="bookID",insertable=false,updatable=false)
    private final Book book;
    @Transient
    NumberFormat c = NumberFormat.getCurrencyInstance();
    
    public Inventory() {
        this.id = 0;
        this.StoreID = 0;
        this.OnHand = 0;
        this.BookID = "";
        this.book = null;
    }

    public int getStoreID() {return StoreID;}
    public String getBookID() {return BookID;}
    public int getOnHand() {return OnHand;}
    public Book getBook() {return this.book;}

    public void setStoreID(int StoreID) {this.StoreID = StoreID;}
    public void setOnHand(int OnHand) {this.OnHand = OnHand;}
    public void setBookID(String BookID) {this.BookID = BookID;}
}
