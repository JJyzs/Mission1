package db;
import java.sql.*;

public class bookgetset {
	

    private int ID;
    private String name;
    private int turn;
    private Timestamp resisterdate;
    private Timestamp modifydate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Timestamp getResisterdate() {
        return resisterdate;
    }

    public void setResisterdate(Timestamp resisterdate) {
        this.resisterdate = resisterdate;
    }

    public Timestamp getModifydate() {
        return modifydate;
    }

    public void setModifydate(Timestamp modifydate) {
        this.modifydate = modifydate;
    }
}

