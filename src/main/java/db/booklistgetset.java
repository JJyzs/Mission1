package db;
import java.sql.*;

public class booklistgetset {
	
    private int ID;
    private String book;
    private String wifi;
    private Timestamp residate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBookname() {
        return book;
    }

    public void setBookname(String bookname) {
        this.book = bookname;
    }

    public String getWifiname() {
        return wifi;
    }

    public void setWifiname(String wifiname) {
        this.wifi = wifiname;
    }

    public Timestamp getResi() {
        return residate;
    }

    public void setResi(Timestamp resi) {
        this.residate = resi;
    }

}
