package timerTask;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import java.util.*;

public class ScheduleServlet extends HttpServlet {
   
    
    Timer timer;
    int i=0;      
   
    public void init() throws ServletException {
      TimerTask task = new TimerTask(){ 
	        public void run() {
            System.out.println("i="+i);
            System.out.println("i="+i);
            i++;	      
	        } 
      };
      timer = new Timer(); 
      Calendar cal = new GregorianCalendar(2021, Calendar.OCTOBER, 3, 0, 0, 0);
      timer.scheduleAtFixedRate(task, cal.getTime(), 1*60*60*1000);
      System.out.println("已建立排程!");       
    }
    

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    }                           	

    public void destroy() {
        timer.cancel();
        System.out.println("已移除排程!");
    }
    
}