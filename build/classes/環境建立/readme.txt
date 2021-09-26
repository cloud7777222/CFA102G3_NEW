1.把context.xml 丟到tomcat的servers取代就可以使用連線池
預設為:
新增
name="jdbc/TestDB3"
password="123456" 
url="jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei" 
username="root"
原來的可以使用，請安心覆蓋，請自行注意帳號密碼是否相符
或自行貼上
<Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/TestDB3" password="123456" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei" username="root"/>

2.使用表格建立資料庫
請測試可以使用belovedb，與功能測試

3.把範本copy貼上，改名，就可以自行使用

4.初始化表格，請自行添加你的東西

5.test

notice:環境我設定統一為utf-8，老師的有亂碼(MS950)，可以先copy正常(MS950)，再貼上專案(utf-8)