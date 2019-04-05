import processing.serial.*;
import processing.video.*;
import processing.io.*;

byte[] cmdBytes = new byte[5];


XML xml;
Movie testVideo;
ArrayList<PImage> movieFrames;
Serial ergPort;

int indexTimer = 0;

boolean hasFinishedLoading = false;
boolean startedWorkout = true;
int frameCounter = 0;
ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();


void setup(){
  size(1280, 720);
  
  // XML setup
  xml = loadXML("MaloitGPS.xml");
  XML trkseg = xml.getChild("trk/trkseg");
  
  // Movie setup
  testVideo = new Movie(this, "Maloit.mov");
  movieFrames = new ArrayList<PImage>();

  // Array of trkpts
  XML[] points = trkseg.getChildren();
  
  convertToWaypoints(points);
  
  // Calculate nextDist and nextTime values for waypoints
  calcNextValues();
  
  // Calculate Speed and Slope for each Waypoint
  calcSpeed();
  calcSlope();
  
  /* Print every Waypoint in waypoints 
  for (int j = 0; j < waypoints.size(); j++) {
    println(waypoints.get(j).toString());
  }*/
  testVideo.play();
  testVideo.frameRate(30);
  
  
  
  cmdBytes[0] = (byte)0xF1; // Start Flag
  cmdBytes[1] = (byte)0xA1; // GetHorizontal
  cmdBytes[2] = (byte)0xA6; // GetPace
  cmdBytes[3] = (byte)0x07; // CheckSum
  cmdBytes[4] = (byte)0xF2; // EndFlag
  
}

void movieEvent (Movie m) {
  
  m.read();
  movieFrames.add(m);
  
}

void draw() {
  double skiErgSpeed = 5.0; // in m/s, this is just test data
  int timer = millis()/1000;
  /*if (indexTimer < waypoints.size() && testVideo.available()) {
    testVideo.read();
    image (testVideo, 0, 0);
    if (timer > waypoints.get(indexTimer).getTotalTime()) {
      indexTimer++;
      float scaleFactor = (float)(skiErgSpeed/waypoints.get(indexTimer).getSpeed());
      if (scaleFactor < 20 && scaleFactor > 0) {
        testVideo.speed(scaleFactor);
      } 
      //println(indexTimer);
    }
  }*/
  
  if (!testVideo.available() && !hasFinishedLoading) {
    
    hasFinishedLoading = true;
    System.out.println("Movie array has finished loading.");
    
  }
  
  if (hasFinishedLoading && startedWorkout) {
    image(movieFrames.get(frameCounter), 0, 0);
  }
}

// Convert trkpts to Waypoints
public void convertToWaypoints(XML[] points) {
  int counter = -1;
  double totalDist = 0;
  int totalTime = 0;
  
  for(int i = 0; i < points.length; i++) {
    if (points[i].getName().equals("trkpt")) {
      double lon = points[i].getFloat("lon");
      double lat = points[i].getFloat("lat");
      float elev = points[i].getChild("ele").getFloatContent();
      String time = points[i].getChild("time").getContent();
      /*
      println(lon);
      println(lat);
      println(elev);
      println(time);
      */
      waypoints.add(new Waypoint(lon, lat, elev, time));
      counter++;
      if (waypoints.size() > 1) {
        double dist = getDistance(waypoints.get(counter-1), waypoints.get(counter));
        totalDist += dist;
        waypoints.get(counter).setDistance(dist, totalDist);
        
        int t = getTime(waypoints.get(counter - 1), waypoints.get(counter));
        totalTime += t;
        waypoints.get(counter).setTime(t, totalTime);
      }
    }
  }
}

public void calcNextValues() {
  for (int i = 0; i < waypoints.size() - 1; i++) {
    double dist = getDistance(waypoints.get(i), waypoints.get(i + 1));
    int t = getTime(waypoints.get(i), waypoints.get(i + 1));
    
    waypoints.get(i).setNextDistance(dist);
    waypoints.get(i).setNextTime(t);
  }
}

public void calcSpeed() {
  for (int i = 0; i < waypoints.size()-1; i++) {
    double dist = waypoints.get(i).getNextDistance();
    int t = waypoints.get(i).getNextTime();
    double speed = dist/t; 
    
    waypoints.get(i).setSpeed(speed);
  }
}

public void calcSlope() {
  for (int i = 1; i < waypoints.size()-1; i++) {
    double prevElev = waypoints.get(i-1).getElev();
    double nextElev = waypoints.get(i + 1).getElev();
    
    double dist = waypoints.get(i).getNextDistance() + waypoints.get(i).getPrevDistance();
    double slope = 0;
    
    if (dist != 0) {
      slope = 180/PI * atan((float)((nextElev - prevElev)/(dist)));
    }
    waypoints.get(i).setSlope(slope);
  }
}

public double getDistance (Waypoint a, Waypoint b) {
    double xSquared = Math.pow(((111321 * cos(PI/180 * (float)(a.getLat() + b.getLat())/2)) * (b.getLon() - a.getLon())), 2);
    double ySquared = Math.pow(111321 * (a.getLat() - b.getLat()), 2);
    
    double d = Math.sqrt(xSquared + ySquared);
    return d;
}

public int getTime (Waypoint a, Waypoint b) {
  int time = 0;
  
  String t1 = a.getStringTime();
  String t2 = b.getStringTime();
  
  int t1Int = Integer.parseInt(t1.substring(t1.length()-2));
  int t2Int = Integer.parseInt(t2.substring(t2.length()-2));
  
  if (t2Int - t1Int < 0) {
    time = 60 + t2Int - t1Int;
  }
  else {
    time = t2Int - t1Int;
  }
  
  return time;
}
