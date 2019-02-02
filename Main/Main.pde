XML xml;

ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();

void setup(){
  // XML setup
  xml = loadXML("AspenGPS.xml");
  XML trkseg = xml.getChild("trk/trkseg");

  // Array of trkpts
  XML[] points = trkseg.getChildren();
  
  // Convert trkpts to Waypoints
  int counter = -1;
  double totalDist = 0;
  
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
      }
    }
  }
  
  
  // Convert String times to Integer times for each waypoint
  int totalTime = 0;
  for (int i = 1; i < waypoints.size(); i++) {
    int time = getTime(waypoints.get(i - 1), waypoints.get(i));
    totalTime += time;
    waypoints.get(i).setTime(time, totalTime);
  }
  
  // Print every Waypoint in waypoints
  for (int j = 0; j < waypoints.size(); j++) {
    println(waypoints.get(j).toString());
  }
  
}

void draw() {
  
}

public double getDistance (Waypoint a, Waypoint b) {
    double xSquared = Math.pow(((111321 * cos(PI/180 * (float)(a.getLat() + b.getLat())/2)) * (b.getLon() - a.getLon())), 2);
    double ySquared = Math.pow((111321 * (b.getLat() - a.getLat())), 2);
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
