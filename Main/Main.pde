XML xml;

ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();

void setup(){
  xml = loadXML("AspenGPS.xml");
  XML trkseg = xml.getChild("trk/trkseg");


  XML[] points = trkseg.getChildren();
 
  println(points[0].getName() + " " + points[1].getName() + points[2].getName() + " " + points[3].getName());
  int counter = -1;
  double totalDist = 0;
  
  for(int i = 0; i < points.length; i++) {
    if (points[i].getName().equals("trkpt")) {
      double lon = points[i].getFloat("lon");
      double lat = points[i].getFloat("lat");
      double elev = points[i].getChild("ele").getFloatContent();
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
  
  for (int j = 0; j < waypoints.size(); j++) {
    println(waypoints.get(j).toString());
  }
  
}

void draw() {
  
}

public double getDistance(Waypoint a, Waypoint b) {
    double xSquared = Math.pow(((111321 * cos(PI/180 * (float)(a.getLat() + b.getLat())/2)) * (b.getLon() - a.getLon())), 2);
    double ySquared = Math.pow((111321 * (b.getLat() - a.getLat())), 2);
    double d;
    
    d = Math.sqrt(xSquared + ySquared);
    return d;
  }
