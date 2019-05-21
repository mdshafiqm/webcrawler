package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;



/**
 * Servlet implementation class CrawlerServlet
 */
@WebServlet("/CrawlerServlet")
public class CrawlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private HashSet<String> links;

	    public CrawlerServlet() {
	        links = new HashSet<String>();
	    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getParameter("url_form");
		System.out.println("Form url"+url);
		
		
		 ArrayList<String> listout=new CrawlerServlet().getPageLinks(url);
	
		 request.setAttribute("webout", listout);
		 request.getRequestDispatcher("webcrawler.jsp").forward(request, response);
		doGet(request, response);
	}

	  private ArrayList<String> getPageLinks(String URL) {
		
		  ArrayList<String> list=new ArrayList<>();
	        //4. Check if you have already crawled the URLs 
	        //(we are intentionally not checking for duplicate content in this example)
	        if (URL.startsWith("https://wiprodigital.com") && !links.contains(URL)) {
	            try {
	                //4. (i) If not add it to the index
	                if (links.add(URL)) {
	                    System.out.println("Parse URL"+URL);
	                    list.add(URL);
	                
	                }

	                //2. Fetch the HTML code
	                Document document = Jsoup.connect(URL).get();
	                //3. Parse the HTML to extract links to other URLs
	                Elements linksOnPage = document.select("a[href]");

	                //5. For each extracted URL... go back to Step 4.
	                for (Element page : linksOnPage) {
	                    getPageLinks(page.attr("abs:href"));
     
	                }
	               
	                
	            } catch (IOException e) {
	                System.err.println("For '" + URL + "': " + e.getMessage());
	            }
	        }
			return list;
	       
	    }


}
