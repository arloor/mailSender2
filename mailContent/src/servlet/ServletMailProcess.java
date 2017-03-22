package servlet;

import helper.DataHelper;
import helper.JavaMail;
import vo.MailMessageVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by arloor on 2017/3/21.
 */
public class ServletMailProcess extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JavaMail javaMail = new JavaMail();
        request.setCharacterEncoding("UTF-8");//必须设置request的编码
        response.setContentType("text/html;charset=UTF-8");//这个也是必须的


        DataHelper dataHelper = new DataHelper();

        String action = request.getParameter("action");
        String to=request.getParameter("to");
        String title=request.getParameter("title");
        int contentNum = Integer.parseInt(request.getParameter("num"));
        String withWeather = request.getParameter("withWeather");
        String[] addrs = to.split(";");
        boolean boolWithWeather = false;

        if (withWeather.equals("yes")) {
            boolWithWeather = true;
        }

        ArrayList<String> contentList = new ArrayList<>();
        for (int i = 0; i < contentNum; i++) {
            String content = request.getParameter("content" + i);
            if (content != null && !content.equals("") && !content.equals("<h>")) {
                contentList.add(content);
            }
        }

        String content = javaMail.createHtmlEmailContent(title, boolWithWeather, contentList);


        PrintWriter writer = response.getWriter();

        writer.print("当前选中的操作是: " + action + "<br>");

        if (action.equals("save")) {
            String contentBeforeWeather = javaMail.createHtmlBeforeWeather(title);
            String contentAfterWeather = javaMail.createHtmlAfterWeather(contentList);
            dataHelper.saveMailContent(title, to, contentBeforeWeather, boolWithWeather, contentAfterWeather);
            writer.print("<br>收件人邮箱为:<br>");
            for (String addr : addrs
                    ) {
                writer.print(addr + "<br>");
            }
            writer.print(content);
            writer.print(content);
        } else if (action.equals("send")) {
            writer.print("<br>收件人邮箱为:<br>");
            for (String addr : addrs
                    ) {
                writer.print(addr + "<br>");
                javaMail.sendEmail(addr, title, content);
            }
            writer.print(content);
        } else if (action.equals("savesend")) {
            String contentBeforeWeather = javaMail.createHtmlBeforeWeather(title);
            String contentAfterWeather = javaMail.createHtmlAfterWeather(contentList);
            dataHelper.saveMailContent(title, to, contentBeforeWeather, boolWithWeather, contentAfterWeather);

            writer.print("<br>收件人邮箱为:<br>");
            for (String addr : addrs
                    ) {
                writer.print(addr + "<br>");
                javaMail.sendEmail(addr, title, content);
            }
            writer.print(content);
        } else if (action.equals("viewlast")) {
            MailMessageVO mailMessageVO = dataHelper.getLastMailContent();

            if (mailMessageVO.getIntWithWeather() == 1) {
                boolWithWeather = true;
            } else boolWithWeather = false;
            String lastContent = mailMessageVO.getContentBeforeWeather() + javaMail.createWeatherContent(boolWithWeather) + mailMessageVO.getContentAfterWeather();
            String[] emails = mailMessageVO.getAddrs();
            writer.print("<br>收件人邮箱为:<br>");
            for (String cell : emails
                    ) {
                writer.print(cell + "<br>");
            }
            writer.write(lastContent);
        } else if (action.equals("sendlast")) {
            MailMessageVO mailMessageVO = dataHelper.getLastMailContent();

            if (mailMessageVO.getIntWithWeather() == 1) {
                boolWithWeather = true;
            } else boolWithWeather = false;
            String lastContent = mailMessageVO.getContentBeforeWeather() + javaMail.createWeatherContent(boolWithWeather) + mailMessageVO.getContentAfterWeather();

            String[] lastAddrs = mailMessageVO.getAddrs();

            for (String addr : lastAddrs
                    ) {
                javaMail.sendEmail(addr, mailMessageVO.getTitle(), lastContent);
            }
            writer.print("<br>收件人邮箱为:<br>");
            String[] emails = mailMessageVO.getAddrs();
            for (String cell : emails
                    ) {
                writer.print(cell + "<br>");
            }
            writer.write(lastContent);
        }



    }


}
