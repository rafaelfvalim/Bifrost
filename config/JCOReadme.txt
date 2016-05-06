SAP Property	Description	Mule Config Attribute	Default Value
jco.server.gwhost *
Gateway host on which the server should be registered
jcoGwHost
 
jco.server.gwserv *
Gateway service, i.e. the port on which a registration can be done
jcoGwService
 
jco.server.progid *
The program ID with which the registration is done
jcoProgId
 
jco.server.connection_count *
The number of connections that should be registered at the gateway
jcoConnectionCount
2
jco.server.saprouter
SAP router string to use for a system protected by a firewall
 
jco.server.max_startup_delay
The maximum time (in seconds) between two startup attempts in case of failures
 
jco.server.repository_destination
Client destination from which to obtain the repository
 
jco.server.repository_map
repository map, if more than one repository should be used by JCoServer
 
jco.server.trace
Enable/disable RFC trace (0 or 1)
 
jco.server.worker_thread_count
set the number of threads that can be used by the JCoServer instance
 
jco.server.worker_thread_min_count
set the number of threads always kept running by JCoServer
 
jco.server.snc_mode **
Secure network connection (SNC) mode, 0 (off) or 1 (on)
 
jco.server.snc_qop **
SNC level of security, 1 to 9
 
jco.server.snc_myname **
SNC name of your server. Overrides the default SNC name. Typically something like p:CN=JCoServer, O=ACompany, C=EN
 
jco.server.snc_lib **
Path to library which provides SNC service.
