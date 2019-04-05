class Waypoint:
    
    prevDistance = 0.0
    nextDistance = 0.0
    totalDistance = 0.0
    
    prevTime = 0
    nextTime = 0
    totalTime = 0
    
    speed = 0.0
    slope = 0.0
    
    def __init__(self, lat, lon, elev, dateTime):
        this.lattitude = lat
        this.longitude = lon
        this.elevation = elev
        this.time = dateTime[dateTime.find("T") + 1:]
        
