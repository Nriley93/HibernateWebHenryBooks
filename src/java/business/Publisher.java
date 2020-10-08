
package business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="publisher")
public class Publisher implements Serializable {
    @Id
    @Column(name="publisher_Code")
    private String pubcd;
    @Column(name="publisher_Name")
    private String pubname;
    @Column(name="publisher_City")
    private String pubcity;
    @Column(name="publisher_State")
    private String pubstate;
    
    public Publisher() {
        this.pubcd = "";
        this.pubname = "";
        this.pubcity = "";
        this.pubstate = "";
    }

    public String getPubcd() {return pubcd;}
    public String getPubstate() {return pubstate;}
    public String getPubcity() {return pubcity;}
    public String getPubname() {return pubname;}

    public void setPubcd(String pubcd) {this.pubcd = pubcd;}
    public void setPubname(String pubname) {this.pubname = pubname;}
    public void setPubcity(String pubcity) {this.pubcity = pubcity;}
    public void setPubstate(String pubstate) {this.pubstate = pubstate;}
}
