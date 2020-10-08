
package business;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author n.riley
 */
@Entity
@Table(name="stores")
public class Store implements Serializable {
    @Id
    @Column(name="storeID")
    private int StoreID;
    @Column(name="storeEmp")
    private int StoreEmp;
    @Column(name="storeName")
    private String StoreName;
    @Column(name="storeAddr")
    private String StoreAddr;
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="storeID")
    @OrderBy("bookID")
    @Cascade(CascadeType.ALL)
    private List<Inventory> bookinv;
    
    public Store() {
        StoreID = 0;
        StoreEmp = 0;
        StoreName = "";
        StoreAddr = "";
    }

    public int getStoreID() {return StoreID;}
    public String getStoreAddr() {return StoreAddr;}
    public String getStoreName() {return StoreName;}
    public int getStoreEmp() {return StoreEmp;}
    public List<Inventory> getBookinv() {return this.bookinv;}

    public void setStoreID(int StoreID) {this.StoreID = StoreID;}
    public void setStoreEmp(int StoreEmp) {this.StoreEmp = StoreEmp;}
    public void setStoreName(String StoreName) {this.StoreName = StoreName;}
    public void setStoreAddr(String StoreAddr) {this.StoreAddr = StoreAddr;}
}
