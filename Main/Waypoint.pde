public class Waypoint {
  
  private double lon;
  private double lat;
  private double elev;
  
  private String t;
  
  private Waypoint next;
  
  private double prevDistance;
  private double totalDistance;
    
  public Waypoint (double longitude, double lattitude, double elevation, String dateTime) {
    lon = longitude;
    lat = lattitude;
    elev = elevation;
    t = dateTime.substring(dateTime.indexOf("T") + 1, dateTime.length()-1);
    prevDistance = 0;
    totalDistance = 0;
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
  
  public double getPrevDistance() {
    return prevDistance;
  }
  
  public double getTotalDistance() {
    return totalDistance;
  }
  
  public String getTime() {
    return t;
  }
  
  public String toString() { 
    return "Lattitude: " + getLat() + " Longitude: " + getLon() + "\nElevation: " + getElev() + " Time: " + getTime() + "\nPrev Distance: " + getPrevDistance() + " Total Distance: " + getTotalDistance();
  }
  
  public void setDistance(double prevDist) {
    prevDistance = prevDist;
  }
  
  public void setDistance(double prevDist, double totalDist) {
    prevDistance = prevDist;
    totalDistance = totalDist;
  }
}
