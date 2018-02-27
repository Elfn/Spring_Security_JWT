## CACHE MEMOIRE PARTAGÉ (SHARED CACHE MEMORY )

=> * IS a type of server charged to save sessions IDs in order to make any server 
instance able to respond to clients requests

##SPOF(SINGLE POINT OF FAILURE)
=> * IN the concern of avoid spof we must alternate shared cache memory by distributed cache

##DISTRIBUTED CACHE

=> * ALLOWS to start off again all sessions ids in severals and linked 
Shared Cache Memory(distributed cache) to prevent spof due to the fact to have only one 
Shared Cache Memory, it works with fault tolerance

##STICKY SESSIONS
=> IS the fact to configure a clever load balancer which can redirect a request to the convenient 
server instance, except that it doesnt work with fault tolerance