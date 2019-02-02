public class Waypoint {
  
  private double lon;
  private double lat;
  private float elev;
  
  private String t;
  
  private double prevDistance;
  private double nextDistance;
  private double totalDistance;
  
  private int prevTime;
  private int nextTime;
  private int totalTime;
  
  // Constructor
  
  public Waypoint (double longitude, double lattitude, float elevation, String dateTime) {
    lon = longitude;
    lat = lattitude;
    elev = elevation;
    t = dateTime.substring(dateTime.indexOf("T") + 1, dateTime.length()-1);
    prevDistance = 0;
    nextDistance = 0;
    totalDistance = 0;
    
    prevTime = 0;
    nextTime = 0;
    totalTime = 0;
  }
  
  // Getters
  
  public double getLon(){
    return lon;
  }
  
  public double getLat(){
    return lat; 
  }
  
  public float getElev() {
    return elev;
  }
  
  public double getPrevDistance() {
    return prevDistance;
  }
  
  public double getNextDistance() {
    return nextDistance;
  }
  
  public double getTotalDistance() {
    return totalDistance;
  }
  
  public String getStringTime() {
    return t;
  }
  
  public int getPrevTime() {
    return prevTime;
  }
  
  public int getNextTime() {
    return nextTime;
  }
  
  public int getTotalTime() {
    return totalTime;
  }
  
  public String toString() { 
    return "Lattitude: " + getLat() + " Longitude: " + getLon() + "\nElevation: " + getElev() + " Time: " + getStringTime() + " | " + getTotalTime() + "\nPrev Distance: " + getPrevDistance() + " Total Distance: " + getTotalDistance();
  }
  
  // Setters
  
  public void setDistance(double prevDist) {
    prevDistance = prevDist;
  }
  
  public void setDistance(double prevDist, double totalDist) {
    prevDistance = prevDist;
    totalDistance = totalDist;
  }
  
  public void setTime(int prevT) {
    prevTime = prevT;
  }
  
  public void setTime(int prevT, int totalT) {
    prevTime = prevT;
    totalTime = totalT;
  }
}
