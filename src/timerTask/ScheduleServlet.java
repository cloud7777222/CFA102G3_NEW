package timerTask;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import com.ad.model.AdService;
import com.ad.model.AdVO;

import java.util.*;

public class ScheduleServlet extends HttpServlet {

	Timer timer;
	int i = 0;

	public void init() throws ServletException {
		TimerTask task = new TimerTask() {
			public void run() {
				System.out.println("i=" + i);
				System.out.println("工作排定的時間 = " + new Date(scheduledExecutionTime()));
				System.out.println("工作執行的時間 = " + new Date() + "\n");
				i++;

				// 廣告
				AdService adsvc = new AdService();
				List<AdVO> allAd = adsvc.getAll();

//            java.sql.Date date= new java.sql.Date(System.currentTimeMillis());
				// 現在時間
				long date = System.currentTimeMillis();

				for (AdVO adVO : allAd) {
//            	java.sql.Time deadTime= java.sql.Date.valueOf(ad.getDeadline().toString());
					long deadTime = java.sql.Timestamp.valueOf(adVO.getDeadline().toString() + " 00:00:00").getTime();
//            	System.out.println("deadTime="+deadTime);
//            	System.out.println("date="+date);

					// 每天檢查過了截止日自動下架
					if (deadTime <= date) {
						adsvc.updateAd(adVO.getAdNo(), adVO.getAdTitle(), adVO.getAd(), adVO.getAdPic1(),
								adVO.getAdPic2(), adVO.getAdPic3(), 0, adVO.getPostTime(), adVO.getDeadline());
						System.out.println("廣告編號=" + adVO.getAdNo() + "下架");
					}
				}
			}
		};
		int day = Calendar.DATE + 1;

		timer = new Timer();
		Calendar cal = new GregorianCalendar(2021, Calendar.OCTOBER, day, 0, 0, 0);
		timer.scheduleAtFixedRate(task, cal.getTime(), 24 * 60 * 60 * 1000);
		System.out.println("date=" + day);
		System.out.println("已建立排程!");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	public void destroy() {
		timer.cancel();
		System.out.println("已移除排程!");
	}

}