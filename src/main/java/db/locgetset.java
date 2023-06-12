package db;
import java.sql.*;

public class locgetset {
	
	 private int ID;
	 private Double Xloc;
	 private Double Yloc;
	 private Timestamp searchdate;
	 
	 public int getID() {
	        return ID;
	    }

	    public void setID(int ID) {
	        this.ID = ID;
	    }

	    public Double getXloc() {
	        return Xloc;
	    }

	    public void setXloc(Double xloc) {
	        Xloc = xloc;
	    }

	    public Double getYloc() {
	        return Yloc;
	    }

	    public void setYloc(Double yloc) {
	        Yloc = yloc;
	    }

	    public Timestamp getSearchdate() {
	        return searchdate;
	    }

	    public void setSearchdate(Timestamp searchdate) {
	        this.searchdate = searchdate;
	    }

}
