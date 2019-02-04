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
  
  private double speed;
  private double slope;
  
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
    
    speed = 0;
    slope = 0;
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
  
  public double getSpeed() {
    return speed;
  }
  
  public double getSlope() {
    return slope;
  }
  
  public String toString() { 
    return "Lattitude: " + getLat() + " Longitude: " + getLon() + " Slope: " + getSlope() + "\nElevation: " + getElev() + " Time: " + getStringTime() + " | " + getTotalTime() + "s Speed:" + getSpeed() + "m/s\nPrev Distance: " + getPrevDistance() + " Next Distance: " + getNextDistance() + " Total Distance: " + getTotalDistance();
  }
  
  // Setters
  
  public void setDistance(double prevDist) {
    prevDistance = prevDist;
  }
  
  public void setDistance(double prevDist, double totalDist) {
    prevDistance = prevDist;
    totalDistance = totalDist;
  }
  
  public void setNextDistance(double nextDist) {
    nextDistance = nextDist;
  }
  
  public void setTime(int prevT) {
    prevTime = prevT;
  }
  
  public void setTime(int prevT, int totalT) {
    prevTime = prevT;
    totalTime = totalT;
  }
  
  public void setNextTime(int nextT) {
    nextTime = nextT;
  }
  
  public void setSpeed(double s) {
    speed = s;
  }
  
  public void setSlope(double sl) {
    slope = sl;
  }
}
