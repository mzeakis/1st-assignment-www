import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class AddProduct extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

    
		boolean flag ;//flag to check if barcode is unique (true : exists / false : not exists)
        try {
            String prodBarcode=request.getParameter("barcode").trim();
			String prodName=request.getParameter("name").trim();
			String prodCcolor=request.getParameter("color").trim();
			String prodDescription=request.getParameter("description").trim();	

			Dao dao=new Dao(); 
          	boolean flag = dao.saveDetails(prodBarcode, prodName, prodCcolor, prodDescription );
        			 

			if (flag == true){//insert new product into db 
            	
	            
	            PrintWriter out = response.getWriter();
	            response.setContentType("text/html");
	
	            out.println("<h2 style='color:red'>You succeffully added the product</h2>");
	            RequestDispatcher rd=request.getRequestDispatcher("add-product.html");  
	            rd.include(request, response);
	            out.close();
			}else{//this barcode already exists 
				PrintWriter out = response.getWriter();
            	response.setContentType("text/html");
            	out.println("<h2 style='color:red'>THE PRODUCT WITH THIS BARCODE ALREADY EXISTS</h2>");
            	RequestDispatcher rd=request.getRequestDispatcher("add-product.html");  
          		rd.include(request, response);
            	out.close();
			}
              
        }
        catch (Exception ex){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2 style='color:red;'>Please confirm the barcode you entered is unique.<br></h2>");
            RequestDispatcher rd=request.getRequestDispatcher("add-product.html");
            rd.include(request, response);
            //out.println("<p>Error: " + ex.getMessage() + "</p>");  // debuging
            //ex.printStackTrace();
            out.close();
        } 
    }
}
