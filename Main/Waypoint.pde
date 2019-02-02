public class Waypoint {
  
  private double lon;
  private double lat;
  private double elev;
  
  private String t;
  
    
  public Waypoint (double longitude, double lattitude, double elevation, String dateTime) {
    lon = longitude;
    lat = lattitude;
    elev = elevation;
    t = dateTime.substring(dateTime.indexOf("T") + 1, dateTime.length()-1);
  }
  
  public double getLon(){
    return lon;
  }
  
  public double getLat(){
    return lat; 
  }
  
  public double getElev() {
    return elev;
  }
  
  public String getTime() {
    return t;
  }
  
  public String toString() { 
    return "Lattitude: " + getLat() + " Longitude: " + getLon() + " Elevation: " + getElev() + " Time: " + getTime();
  }
}
