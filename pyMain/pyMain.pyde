import xml.etree.ElementTree as ET
#import pyrow.py

waypoints = []


def setup():
    size(1280, 720)
    XMLtoWaypoints('maloitGPS.xml')
    for waypoint in waypoints:
        println(waypoint.toString())
    
    
def draw():
    background(200)
    
def XMLtoWaypoints(fileName):
    tree = ET.parse(fileName)
    root = tree.getroot()
    println(root)

    
    
    counter = -1
    totalDist = 0
    totalTime = 0
    for trkpt in root[1][3]:
        println("yes")
        lat = float(trkpt.get('lat'))
        lon = float(trkpt.get('lon'))
        ele = float(trkpt[0].text)
        time = trkpt[1].text
        
        waypoints.append(Waypoint(lat, lon, ele, time))
        
        if len(waypoints) > 1:
            dis = getDistance(waypoints[counter - 1], waypoints[counter])
            totalDist += dis
            waypoints[counter].prevDistance = dis
            waypoints[counter].totalDistance = totalDist
            
            t = getTime(waypoints[counter - 1], waypoints[counter])
            totalTime += t
            waypoints[counter].prevTime = t
            waypoints[counter].totalTime = totalTime
    calcNextValues()
    calcSpeed()
                                
def calcNextValues():
    for i in range(len(waypoints)-1):
        waypoints[i].nextDistance = waypoints[i+1].prevDistance
        waypoints[i].nextTime = waypoints[i+1].prevTime

def calcSpeed():
    for i in range(len(waypoints)-1):
        if waypoints[i].nextTime != 0:
            waypoints[i].speed = waypoints[i].nextDistance/waypoints[i].nextTime

def getDistance(a, b):
    xSquared = ((111321 * cos(PI/180 * (a.lattitude + b.lattitude)/2)) * (b.longitude - a.longitude)) ** 2
    ySquared = (111321 * (a.lattitude - b.lattitude)) ** 2
    
    d = (xSquared + ySquared) ** (1/2)
    return d

def getTime (a, b):
    time = 0
    
    t1 = int(a.time[len(a.time) - 2:])
    t2 = int(b.time[len(b.time) - 2:])
    
    if (t2 - t1) < 0:
        time = 60 + t2 - t1
    
    else:
        time = t2 - t1
    
    return time
    
class Waypoint:
    
    def __init__(self, lat, lon, elev, dateTime):
        self.lattitude = lat
        self.longitude = lon
        self.elevation = elev
        self.time = dateTime[dateTime.find("T") + 1:len(dateTime) - 1]
        
        self.prevDistance = 0.0
        self.nextDistance = 0.0
        self.totalDistance = 0.0
        
        self.prevTime = 0
        self.nextTime = 0
        self.totalTime = 0
        
        self.speed = 0.0
        self.slope = 0.0
        
    def toString(self):
        return "Lattitude: " + str(self.lattitude) + " Longitude: " + str(self.longitude) + " Slope: " + str(self.slope) + "\nElevation: " + str(self.elevation) + " Time: " + self.time + " | " + str(self.totalTime) + "s Speed:" + str(self.speed) + "m/s\nPrev Distance: " + str(self.prevDistance) + " Next Distance: " + str(self.nextDistance) + " Total Distance: " + str(self.totalDistance)
