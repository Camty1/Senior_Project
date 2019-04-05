import xml.etree.ElementTree as ET
#import pyrow.py

waypoints = []


def setup():
    size(1280, 720)
    XMLtoWaypoints('maloitGPS.xml')
    println(waypoints)
    
    
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
        lat = trkpt.get('lat')
        lon = trkpt.get('lon')
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
    
