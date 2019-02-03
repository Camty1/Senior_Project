XML xml;

ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();

void setup(){
  // XML setup
  xml = loadXML("AspenGPS.xml");
  XML trkseg = xml.getChild("trk/trkseg");

  // Array of trkpts
  XML[] points = trkseg.getChildren();
  
  convertToWaypoints(points);
  
  // Calculate nextDist and nextTime values for waypoints
  calcNextValues();
  
  // Print every Waypoint in waypoints
  for (int j = 0; j < waypoints.size(); j++) {
    println(waypoints.get(j).toString());
  }
  
}

void draw() {
  
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
      println(lon);
      println(lat);
      println(elev);
      println(time);
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

public double getDistance (Waypoint a, Waypoint b) {
    double xSquared = Math.pow(((111321 * cos(PI/180 * (float)(a.getLat() + b.getLat())/2)) * (b.getLon() - a.getLon())), 2);
    double ySquared = Math.pow(111321 * (a.getLat() - b.getLat()), 2);
    double d;
    
    d = Math.sqrt(xSquared + ySquared);
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
