package email;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailService extends HttpServlet implements Runnable{
	String to,subject, messageText;
	
	public void run() {
		// send email
		MailService mailSv = new MailService();
		mailSv.sendMail(to, subject, messageText);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		

		// 聯絡我們
		// send email
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String subject = req.getParameter("subject");
			String message = req.getParameter("message");
			
			
			MailService mailSv = new MailService();
			mailSv.to="cfa102g3@gmail.com";
			mailSv.subject="[客戶詢問]"+subject;
			mailSv.messageText= "客戶姓名:"+name+"\n"
					+"客戶email:"+email
					+"客戶問題:"+message;
			Thread t=new Thread(mailSv);
			t.start();
//			mailSv.sendMail("cfa102g3@gmail.com", subject, message);
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/index/index.jsp");
				failureView.forward(req, res);
				return;
			}
			
			String successMsgs = "寄出成功";
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("successMsgs", successMsgs);
			
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/index/index.jsp");
			successView.forward(req, res);
			
		}catch(Exception e) {
			errorMsgs.add("無法寄出:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/index/index.jsp");
			failureView.forward(req, res);
		}
	}

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "cfa102g3@gmail.com";
			final String myGmail_password = "cfa123456";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		String to = "cloud7777222@yahoo.com.tw";

		String subject = "密碼通知";

		String ch_name = "peter1";
		String passRandom = "111";
		String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";

		MailService mailService = new MailService();
		mailService.sendMail(to, subject, messageText);

	}

}
