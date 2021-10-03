package timerTask;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ScheduleServlet2 extends HttpServlet{    
    Timer timer ; 
    int count = 0;        
    public void destroy(){
        timer.cancel();
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    }
            
    public void init(){        
        timer = new Timer();
        Calendar cal = new GregorianCalendar(2011, Calendar.MARCH, 5, 0, 0, 0);        
        TimerTask task = new TimerTask(){
                   
            public void run(){
                System.out.println("This is Task"+ count);
                System.out.println("�u�@�Ʃw���ɶ� = " + new Date(scheduledExecutionTime()));
                System.out.println("�u�@���檺�ɶ� = " + new Date() + "\n");                
                count++;
            }
        };
        timer.scheduleAtFixedRate(task, cal.getTime(), 60*60*1000); 
    }
}
