package helper;

import vo.MailMessageVO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arloor on 2017/3/21.
 */
public class DataHelper {
    static String url = "jdbc:mysql://115.159.161.236:3306/WeatherMail?"
            + "user=lgh&password=lgh8023lfy&useUnicode=true&characterEncoding=UTF8";

    Connection conn;

    public DataHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("mysql驱动缺失");
        }
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataHelper helper = new DataHelper();
        Map<String, String> urls = helper.getUrlsByMysql();
        for (Map.Entry<String, String> cell : urls.entrySet()
                ) {
            System.out.println(cell.getKey() + " " + cell.getValue());
        }

        StringBuilder content = new StringBuilder();
        content.append("<!doctype html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <center>\n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"450px\" id=\"bodyTable\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" valign=\"top\"  id=\"bodyCell\">\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\" style=\"max-width:450px\">\n" +
                "                            <tbody class=\"mcnTextBlockOuter\">\n" +
                "\n" +
                "\n" +
                "                            <!-- header begin -->\n" +
                "                            <tr bgcolor=\"#1a8556\">\n" +
                "                                <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\"></td>\n" +
                "                                <table bgcolor=\"#1a8556\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"text-align: center; max-width:450px; min-width:450px;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "                                    <tbody><tr>\n" +
                "\n" +
                "                                        <td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\">\n" +
                "\n" +
                "                                            <h1 style=\"font-family:Courier New;color: antiquewhite\">" + "title" + "</h1>\n" +
                "                                            <h4 style=\"font-family:Courier New;color: antiquewhite\">love my love</h4>\n" +
                "\n" +
                "                                    </tr>\n" +
                "                                    </tbody></table>\n" +
                "                            </tr>\n" +
                "                            <!-- header end-->\n" +
                "\n" +
                "                            <!--body begin -->\n" +
                "                            <tr>\n" +
                "                                <td valign=\"top\" id=\"templateBody\" bgcolor=\"#d8bfd8\" width=\"450px\" style=\" max-width:450px; min-width:450px;\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"max-width:450px; min-width:450px;\">\n" +
                "                                        <tbody class=\"mcnTextBlockOuter\">\n" +
                "                                            <tr>\n" +
                "                                                <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                "                                                    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;height: 360px\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td valign=\"top\"  style=\"width:450px;\"><![endif]-->\n" +
                "                                                                 <table align=\"center\"    border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "                                                                    <tbody>\n" +
                "                                                                         <tr >\n" +
                "                                                                            <td  valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:40px; padding-right:18px; padding-bottom:9px; padding-left:18px;\">\n");


        helper.saveMailContent("ceshi", "1293181335@qq.com", content.toString(), true, "asddddddddddddddddddddddd");
    }

    public Map<String, String> getUrlsByMysql() {
        Map<String, String> urls = new HashMap<String, String>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cities");
            while (resultSet.next()) {
                String cname = resultSet.getString(1);
                String url = resultSet.getString(2);

                urls.put(cname, url);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return urls;
    }

    public void saveMailContent(String title, String emails, String contentBeforeWeather, boolean withWeather, String contentAfterWeather) {
        int intWithWeather = 1;
        if (withWeather == false) {
            intWithWeather = 0;
        }
        try {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO mailMessage(title,emails,contentBeforeWeather,withWeather,contentAfterWeather) VALUES (" + "'" +
                    title + "','" +
                    emails + "','" +
                    contentBeforeWeather + "'," +
                    intWithWeather + ",'" +
                    contentAfterWeather + "'" +
                    ")");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //return ResultMessage.USER_EXIST;
        }
    }

    public MailMessageVO getLastMailContent() {
        String sql = "SELECT * FROM mailMessage WHERE id = (select MAX(id) from mailMessage)";
        MailMessageVO mailMessageVO = null;

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String title = resultSet.getString(2);
                String emails = resultSet.getString(3);
                String[] addrs = emails.split(";");
                String contentBeforeWeather = resultSet.getString(4);
                int intWithWeather = resultSet.getInt(5);
                String contentAfterWeather = resultSet.getString(6);

                mailMessageVO = new MailMessageVO(title, addrs, contentBeforeWeather, intWithWeather, contentAfterWeather);

            }
            resultSet.close();
            statement.close();
            return mailMessageVO;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
