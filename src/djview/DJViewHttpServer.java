package djview;

import com.sun.net.httpserver.HttpServer; 
import java.net.InetSocketAddress;

public class DJViewHttpServer { 
	 public static void main(String args [] ){ 
	  try{ 
	   System.out.println("DJView Http Server Running "); 
	   BeatModel beatModel = new BeatModel(); 
	   beatModel.initialize(); 
	   
	   
	   
	   HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
	   server.start(); 
	   System.out.println("DJView Serv is running at http: // localhost:8080/djview");
	  } catch(Exception e ){ 
	  e.printStackTrace(); 
	  
	  }
	 }


} 
