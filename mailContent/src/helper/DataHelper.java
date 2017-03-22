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

    public Map<String, String> getDailyWeatherEmails() {
        String sql = "SELECT * FROM emails";
        Map<String, String> emailThemeMap = new HashMap<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String emailAddr = resultSet.getString(2);
                String theme = resultSet.getString(3);
                emailThemeMap.put(emailAddr, theme);
            }
            resultSet.close();
            statement.close();
            return emailThemeMap;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
