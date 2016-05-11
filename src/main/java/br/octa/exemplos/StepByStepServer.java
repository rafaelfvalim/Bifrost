package br.octa.exemplos;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.ServerDataProvider;
import com.sap.conn.jco.server.DefaultServerHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerErrorListener;
import com.sap.conn.jco.server.JCoServerExceptionListener;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerState;
import com.sap.conn.jco.server.JCoServerStateChangedListener;
import com.sap.conn.jco.server.JCoServerTIDHandler;
public class StepByStepServer
{
    static String SERVER_NAME1 = "SERVER";
    static String DESTINATION_NAME1 = "ABAP_AS_WITHOUT_POOL";
    static String DESTINATION_NAME2 = "ABAP_AS_WITH_POOL";
    static
    {
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "10.11.50.153");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  "00");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "300");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   "rvalim");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "r4f43l11");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   "PT");
        createDataFile(DESTINATION_NAME1, "jcoDestination", connectProperties);
        
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3");
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,    "10");
        createDataFile(DESTINATION_NAME2, "jcoDestination", connectProperties);
        
        Properties servertProperties = new Properties();
        servertProperties.setProperty(ServerDataProvider.JCO_GWHOST, "10.11.50.153");
        servertProperties.setProperty(ServerDataProvider.JCO_GWSERV, "sapgw00");
        servertProperties.setProperty(ServerDataProvider.JCO_PROGID, "JCO_SERVER");
        servertProperties.setProperty(ServerDataProvider.JCO_REP_DEST, "ABAP_AS_WITH_POOL");
        servertProperties.setProperty(ServerDataProvider.JCO_CONNECTION_COUNT, "2");
        
       
        createDataFile(SERVER_NAME1, "jcoServer", servertProperties);
    }
    
    static void createDataFile(String name, String suffix, Properties properties)
    {
        File cfg = new File(name+"."+suffix);
        if(!cfg.exists())
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(cfg, false);
                properties.store(fos, "for tests only !");
                fos.close();
            }
            catch (Exception e)
            {
                throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
            }
        }
    }
    
    static class StfcConnectionHandler implements JCoServerFunctionHandler
    {
        public void handleRequest(JCoServerContext serverCtx, JCoFunction function)
        {
            System.out.println("----------------------------------------------------------------");
            System.out.println("call              : " + function.getName());
            System.out.println("ConnectionId      : " + serverCtx.getConnectionID());
            System.out.println("SessionId         : " + serverCtx.getSessionID());
            System.out.println("TID               : " + serverCtx.getTID());
            System.out.println("repository name   : " + serverCtx.getRepository().getName());
            System.out.println("is in transaction : " + serverCtx.isInTransaction());
            System.out.println("is stateful       : " + serverCtx.isStatefulSession());
            System.out.println("----------------------------------------------------------------");
            System.out.println("gwhost: " + serverCtx.getServer().getGatewayHost());
            System.out.println("gwserv: " + serverCtx.getServer().getGatewayService());
            System.out.println("progid: " + serverCtx.getServer().getProgramID());
            System.out.println("----------------------------------------------------------------");
            System.out.println("attributes  : ");
            System.out.println(serverCtx.getConnectionAttributes().toString());
            System.out.println("----------------------------------------------------------------");
            System.out.println("req text: " + function.getImportParameterList().getString("REQUTEXT"));
            function.getExportParameterList().setValue("ECHOTEXT", function.getImportParameterList().getString("REQUTEXT"));
            function.getExportParameterList().setValue("RESPTEXT", "Hello World");
        }
    }
    static class StfcConnectionHandler1 implements JCoServerFunctionHandler
    {
    	public void handleRequest(JCoServerContext serverCtx, JCoFunction function)
    	{
    		System.out.println("----------------------------------------------------------------");
    		System.out.println("call              : " + function.getName());
    		System.out.println("ConnectionId      : " + serverCtx.getConnectionID());
    		System.out.println("SessionId         : " + serverCtx.getSessionID());
    		System.out.println("TID               : " + serverCtx.getTID());
    		System.out.println("repository name   : " + serverCtx.getRepository().getName());
    		System.out.println("is in transaction : " + serverCtx.isInTransaction());
    		System.out.println("is stateful       : " + serverCtx.isStatefulSession());
    		System.out.println("----------------------------------------------------------------");
    		System.out.println("gwhost: " + serverCtx.getServer().getGatewayHost());
    		System.out.println("gwserv: " + serverCtx.getServer().getGatewayService());
    		System.out.println("progid: " + serverCtx.getServer().getProgramID());
    		System.out.println("----------------------------------------------------------------");
    		System.out.println("attributes  : ");
    		System.out.println(serverCtx.getConnectionAttributes().toString());
    		System.out.println("----------------------------------------------------------------");
    		System.out.println("req text: " + function.getName());
//    		function.getExportParameterList().setValue("ECHOTEXT", function.getImportParameterList().getString("REQUTEXT"));
//    		function.getExportParameterList().setValue("RESPTEXT", "Hello World");
    	}
    }
    
    static void step1SimpleServer()
    {
        JCoServer server;
        try
        {
            server = JCoServerFactory.getServer(SERVER_NAME1);
        }
        catch(JCoException ex)
        {
            throw new RuntimeException("Unable to create the server " + SERVER_NAME1 + ", because of " + ex.getMessage(), ex);
        }
        
        JCoServerFunctionHandler stfcConnectionHandler = new StfcConnectionHandler();
        JCoServerFunctionHandler stfcConnectionHandler1 = new StfcConnectionHandler1();
        DefaultServerHandlerFactory.FunctionHandlerFactory factory = new DefaultServerHandlerFactory.FunctionHandlerFactory();
        factory.registerHandler("STFC_CONNECTION", stfcConnectionHandler);
        factory.registerHandler("/KTT/SENDCONSNFEDESTWS", stfcConnectionHandler1);
        server.setCallHandlerFactory(factory);
        server.start();
        
        System.out.println("The program can be stopped using <ctrl>+<c>");
    }
    
    static class MyThrowableListener implements JCoServerErrorListener, JCoServerExceptionListener
    {
        
        public void serverErrorOccurred(JCoServer jcoServer, String connectionId, JCoServerContextInfo serverCtx, Error error)
        {
            System.out.println(">>> Error occured on " + jcoServer.getProgramID() + " connection " + connectionId);
            error.printStackTrace();
        }
        public void serverExceptionOccurred(JCoServer jcoServer, String connectionId, JCoServerContextInfo serverCtx, Exception error)
        {
            System.out.println(">>> Error occured on " + jcoServer.getProgramID() + " connection " + connectionId);
            error.printStackTrace();
        }
    }
    
    static class MyStateChangedListener implements JCoServerStateChangedListener
    {
        public void serverStateChangeOccurred(JCoServer server, JCoServerState oldState, JCoServerState newState)
        {
             
            // Defined states are: STARTED, DEAD, ALIVE, STOPPED; 
            // see JCoServerState class for details.  
            // Details for connections managed by a server instance 
            // are available via JCoServerMonitor
            System.out.println("Server state changed from " + oldState.toString() + " to " + newState.toString() + 
                    " on server with program id " + server.getProgramID());
        }
    }
    static void step2SimpleServer()
    {
        JCoServer server;
        try
        {
            server = JCoServerFactory.getServer(SERVER_NAME1);
        }
        catch(JCoException ex)
        {
            throw new RuntimeException("Unable to create the server " + SERVER_NAME1 + ", because of " + ex.getMessage(), ex);
        }
        
        JCoServerFunctionHandler stfcConnectionHandler = new StfcConnectionHandler();
        DefaultServerHandlerFactory.FunctionHandlerFactory factory = new DefaultServerHandlerFactory.FunctionHandlerFactory();
        factory.registerHandler("STFC_CONNECTION", stfcConnectionHandler);
        server.setCallHandlerFactory(factory);
        
        //additionally to step 1
        MyThrowableListener eListener = new MyThrowableListener();
        server.addServerErrorListener(eListener);
        server.addServerExceptionListener(eListener);
        
        MyStateChangedListener slistener = new MyStateChangedListener();
        server.addServerStateChangedListener(slistener);
        server.start();
        System.out.println("The program can be stopped using <ctrl>+<c>");
    }
    
    static class MyTIDHandler implements JCoServerTIDHandler
    {
        
        Map<String, TIDState> availableTIDs = new Hashtable<String, TIDState>();
        
        public boolean checkTID(JCoServerContext serverCtx, String tid)
        {
            System.out.println("TID Handler: checkTID for " + tid);
            if(availableTIDs.containsKey(tid))
                return false;
            //notify other instances about the starting of an tRFC call, if necessary
            availableTIDs.put(tid, TIDState.STARTED);
            return true;
        }
        public void commit(JCoServerContext serverCtx, String tid)
        {
            System.out.println("TID Handler: commit for " + tid);
            //react on commit e.g. commit on the database
            //if necessary throw a RuntimeException if the commit was not possible
            availableTIDs.put(tid, TIDState.FINISHED);
        }
        public void rollback(JCoServerContext serverCtx, String tid)
        {
            System.out.println("TID Handler: rollback for " + tid);
            //react on rollback e.g. rollback on the database
            availableTIDs.put(tid, TIDState.FINISHED);
        }
        public void confirmTID(JCoServerContext serverCtx, String tid)
        {
            System.out.println("TID Handler: confirmTID for " + tid);
            try 
            {
                //clean up the resources
            }
            //catch(Throwable t) {} //partner wont react on an exception at this point
            finally
            {
                availableTIDs.remove(tid);
            }
        }
        
        private enum TIDState
        {
            STARTED, FINISHED;
        }
    }
    
    
    static void step3SimpleTRfcServer()
    {
        JCoServer server;
        try
        {
            server = JCoServerFactory.getServer(SERVER_NAME1);
        }
        catch(JCoException ex)
        {
            throw new RuntimeException("Unable to create the server " + SERVER_NAME1 + ", because of " + ex.getMessage(), ex);
        }
        
        JCoServerFunctionHandler stfcConnectionHandler = new StfcConnectionHandler();
        DefaultServerHandlerFactory.FunctionHandlerFactory factory = new DefaultServerHandlerFactory.FunctionHandlerFactory();
        factory.registerHandler("STFC_CONNECTION", stfcConnectionHandler);
        server.setCallHandlerFactory(factory);
        
        //additionally to step 1
        MyTIDHandler myTIDHandler = new MyTIDHandler();
        server.setTIDHandler(myTIDHandler);
        server.start();
        System.out.println("The program can be stopped using <ctrl>+<c>");
    }
        
    public static void main(String[] a)
    {
        step1SimpleServer();
//        step2SimpleServer();
//        step3SimpleTRfcServer();
    }
}