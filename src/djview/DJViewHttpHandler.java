package djview;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.sun.net.httpserver.*;

public class DJViewHttpHandler implements HttpHandler{
	 BeatModel beatModel; 
	 Map<String, String> queryPairs; 
	 
	  public DJViewHttpHandler(BeatModel beatModel) { 
		 this.beatModel = beatModel; 
		 
	  }
	  
	   public void handle(HttpExchange httpExchange) throws IOException{
	    String uri = httpExchange.getRequestURI().toString();
		System.out.println("URI" + uri);
		String[] params =  uri.split("\\?");
		String queryStrings = " ";
		if(params.length > 1) { 
			queryStrings = params[1];
		}
		
		boolean noAction = true; 
		 try{ 
		 queryPairs = this.splitQuery(queryStrings);
		 } catch(Exception e) {
			System.out.println("error spliting query " + e.getMessage()); 
		 }
		  System.out.println("Query pairs: " + queryPairs);

			if(!noAction) { 
			String bpm = this.getParameter("bpm");
			 if(bpm == null) { 
				bpm = beatModel.getBPM() + ""; 
			 
			 }
			 String set = this.getParameter("set"); 
			 if(set != null) { 
				int bpmNumber = 90; 
				bpmNumber = Integer.parseInt(bpm); 
				beatModel.setBPM(bpmNumber);
			 }
			 
			 String decrease = this.getParameter("decrease");
			  if( decrease != null) { 
				beatModel.setBPM(beatModel.getBPM() - 1);
			  }
			  String increase = this.getParameter("increase");
				if(increase != null ) { 
				 beatModel.setBPM(beatModel.getBPM() + 1);
				}
				String on = this.getParameter("on"); 
					if(on != null) { 
					beatModel.on(); 
					}
				String off = this.getParameter("off");
					if(off != null){ 
					  beatModel.off(); 
					  }
				 Headers headers =  httpExchange.getResponseHeaders(); 
				StringBuilder responseStringBody = new  StringBuilder(); 
			responseStringBody.append(beatModel.getBPM());
			System.out.println("BPM" + beatModel.getBPM());
			
			OutputStream outputStream   = httpExchange.getResponseBody(); 
			StringBuilder responseStringBuilder = new StringBuilder();
			responseStringBuilder.append(beatModel.getBPM());
			System.out.println("BPM" + beatModel.getBPM());
			
			String responseString = responseStringBuilder.toString();
			httpExchange.sendResponseHeaders(200, responseString.length());
			outputStream.write(responseString.getBytes());
			outputStream.flush();
			outputStream.close();
			}	  
			}
			
			Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {  
				Map<String, String> query_pairs =  new HashMap<String, String>();
				String[] pairs = query.split("&");
				for (String pair : pairs) { 
					int idx = pair.indexOf("=");
					query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF_8"));
				}
				return query_pairs; 
	   }
	    String getParameter(String param) { 
	    	
		return queryPairs.get(param);
		}
	}
