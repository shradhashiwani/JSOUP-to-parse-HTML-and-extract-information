package ss2898_A1_HTMLparsing;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SchedQuery {
	
	static String inputURL;
	static ArrayList<String> inputColumnNames = new ArrayList<String>();
	static ArrayList<String> inputColumnDatas = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		
		//hardcoding start
/**/		String arg[]=new String[5] ;
		arg[0]="http://www.njit.edu/registrar/schedules/courses/spring/2016S.CS.html";
		arg[1]="instructor";arg[2]="Bruckner";
		arg[3]="days";arg[4]="T";
		collectInput(arg);/**/
		//hardcoding end
		
		//collectInput(args);
		
		 
	     
		LinkedHashMap<String, String> eachRow = new LinkedHashMap<String, String>();
	     ArrayList allRows=new ArrayList<HashMap<String, String>>();
	     int i=0; //boolean checkInput1= false; boolean checkInput2= false; 
	     String rowName=""; String temp="";
	     //int totNoInputs=inputColumnDatas.size();
				
		 
	     Document document = Jsoup.connect(inputURL).get();
	     
	     String rowNameArray[] = findRowNames(document);
	    // int totalRows= rowNameArray.length-1;
	     
	     //saving data row wise in an arraylist: each row is a hashmap: start
	     
	     	Elements contentOfAllTR = document.getElementsByTag("tr");
	        Iterator<Element> iterator_eachTR = contentOfAllTR.select("tr").iterator();
	        
	        while(iterator_eachTR.hasNext()){
	        	
	        	eachRow = new LinkedHashMap(); i=0;
	        	
	        	Element contentOfAllTD = iterator_eachTR.next();
	        	Iterator<Element> iterator_eachTD = contentOfAllTD.select("td").iterator();
	        		        	
		        while(iterator_eachTD.hasNext()){
		        	rowName=rowNameArray[i];
		        	eachRow.put(rowName.toLowerCase(), iterator_eachTD.next().text());
		            i++;
		        	
		        }
		        if(!eachRow.isEmpty()||eachRow.size()!=0)
		        	allRows.add(eachRow);
		       
	        }
	       
	      //saving data: end
	        
	        
	        
	      //retrieve data: start
	        
	        	        
	      //instructor ryan days F
	       
	        Boolean print=false;
	      
	      
	      for(i=0;i<allRows.size();i++){
	    	  
	    	 // checkInput1=false; checkInput2=false;
	    	  
	    	  eachRow=(LinkedHashMap<String, String>) allRows.get(i);
	    	  
	    	  print= checkAllInputs(inputColumnNames,inputColumnDatas,eachRow);
	    	  
	    	  /*checkInput1=eachRow.get(input1.toLowerCase()).toLowerCase().contains(input2.toLowerCase());
	    	  checkInput2=eachRow.get(input3.toLowerCase().toLowerCase()).toLowerCase().contains(input4.toLowerCase());
	    	  */
	    	  if(print){
	    	  //if(checkInput1&&checkInput2){
	    		  
	    		  temp=""+eachRow.values();
	    		  System.out.println(temp.substring(1, temp.length()-1));
	    		  
	    	  }
	      }
	        
	      //retrieve data: end
	        
	        //working code start
	        /*
	        Elements tr = document.select("tr:contains(Ryan)");
	        Elements tr1 = tr.select("tr:contains(F)");
	        for(Element p : tr1)
	          System.out.println(p.text());
	        */
	        //working code end
	        
	}

	private static Boolean checkAllInputs(ArrayList<String> inputColumnName2, ArrayList<String> inputColumnData2,
			LinkedHashMap<String, String> eachRow) {

		
		ArrayList<Boolean> allCondition= new ArrayList<>();
			
			for(int i=0;i<inputColumnData2.size();i++){
				allCondition.add(eachRow.get(inputColumnName2.get(i).toLowerCase()).toLowerCase().contains(inputColumnData2.get(i).toLowerCase()));
				//eachRow.get(inputColumnName2.get(i).toLowerCase()) = for column name entered by user(eg professor) find relevant value in each row(eg: ryan, foreman....ie; name of all professor)
				//.toLowerCase().contains(inputColumnData2.get(i).toLowerCase())= now check if the list of names of professor returned contains the name of the professor entered by user.
			}
			
			if (allCondition.contains(false))
				return false;
			else
				return true;
		
	}


	private static String[] findRowNames(Document document) {

		Elements contentA = document.getElementsByTag("table");
        Element k = contentA.get(0);
        Elements content2 = k.getElementsByTag("tr");
        Element k1 = content2.get(0);

        Object[] a= k1.getElementsByTag("th").toArray();
        String b[] = new String[a.length];
        
        for(int i=0; i<a.length;i++){
        	a[i]=a[i].toString().subSequence(4, a[i].toString().length()-5);
        	b[i]=(String) a[i];
         }
       
		return  b;
	}
	
	
	private static void collectInput(String[] args) {
		
		inputURL = "http://www.njit.edu/registrar/schedules/courses/spring/2016S.CS.html";
		if(args.length%2==0){
			System.out.println("incorrect input");
			System.exit(0); 
					
		}
	
		for (int i=1;i<args.length;i+=2) {
			inputColumnNames.add(args[i]) ;
			inputColumnDatas.add(args[i+1]);
        }
	}

}

