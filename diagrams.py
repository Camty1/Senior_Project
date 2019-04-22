Waypoint class
    
    # Given Data 
    latitude # Float: The Latitude in degrees of the Waypoint from GPS Data
    longitude # Float: The Longitude in degrees of the Waypoint from GPS Data
    elevation # Float: The Elevation in meters of the Waypoint from GPS Data
    time # String: HH:MM:SS format representing the time of the Waypoint from GPS Data
    
    # Calculated Data
    prevDistance # Float: Distance in meters to last Waypoint calculated using getDistance()
    nextDistance # Float: Distance in meters to next Waypoint calculated using getDistance()
    totalDistance # Float: Total elapsed distance in meters from beginning to this Waypoint
        
    prevTime # Integer: Time in seconds to last Waypoint calculated using getTime()
    nextTime # Integer: Time in seconds to next Waypoint calculated using getTime()
    totalTime # Integer: Total elapsed Time in seconds from beginning to this Waypoint
        
    speed # Float: Speed in meters/ second to next Waypoint
    slope # Float: Gradient of Waypoint in degrees
    
    def toString # Returns the Waypoint as a String with all instance variables and labels
    
    