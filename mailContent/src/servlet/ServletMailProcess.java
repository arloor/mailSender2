package servlet;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by arloor on 2017/3/15.
 */
public class ServletMailProcess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//必须设置request的编码
        response.setContentType("text/html;charset=UTF-8");//这个也是必须的
        response.setHeader("","");
        /*

        out.print(request.getParameter("title"));
        */
        String to=request.getParameter("to");
        String title=request.getParameter("title");
        String rawContent=request.getParameter("content");

        String content=createHtmlEmailContent(title,rawContent);
        sendEmail(to,title,content);

        PrintWriter out=response.getWriter();
        out.print(content);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static void sendEmail(String userEmail,String theme,String content) {
// 收件人电子邮箱
        String to = userEmail;
// 发件人电子邮箱 这里使用学校邮箱
        String from = "151250095@smail.nju.edu.cn";
// 指定发送邮件的主机为 smtp.exmail.qq.com
        String host = "smtp.exmail.qq.com"; //QQ 邮件服务器
// 获取系统属性
        Properties properties = System.getProperties();
// 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
// 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("151250095@smail.nju.edu.cn", "!@QWqw426543"); //发件人邮件用户名、密码
            }
        });
        try {
// 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
// Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
// Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
// Set Subject: 头部头字段
            message.setSubject(theme,"UTF-8");
// 设置消息体
            message.setContent(content, "text/html;charset=UTF-8");
// 发送消息
            Transport.send(message);

            System.out.println("邮件发送成功");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static String createHtmlEmailContent(String title,String rawContent){
        StringBuilder content=new StringBuilder();

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
                "                                            <h1 style=\"font-family:Courier New;color: antiquewhite\">"+title+"</h1>\n" +
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





        content.append("<!-- 特殊日子追加内容的模板2 使用h3标签 -->\n" +
                "<div style=\"font-family: Consolas; font-size: 20px;text-align: left;line-height:200%;\">");
        String[] strings=rawContent.split("\n");
        for (String cell:strings
                ) {
            if(!cell.startsWith("<image>")){
                content.append("<h3>"
                        +cell
                        +"</h3>");
            }else{
                content.append("<img src=\""+cell.substring(7)+"\" width=\"414\" alt=\"图片显示失败\">");
                System.out.println(cell.substring(7));
            }
        }

        content.append("</div>");



        content.append(
                "                                                                           </td>\n" +
                        "                                                                       </tr>\n" +
                        "                                                                    </tbody>\n" +
                        "                                                                 </table>\n" +
                        "                                                            </td>\n" +
                        "                                                         </tr>\n" +
                        "                                                    </table>\n" +
                        "                                                 </td>\n" +
                        "                                            </tr>\n" +
                        "                                        </tbody>\n" +
                        "                                    </table>\n" +
                        "                                </td>\n" +
                        "                            </tr>\n" +
                        "                            <!-- body end-->\n" +
                        "\n" +
                        "                            <!--footer begin-->\n" +
                        "                            <tr>\n" +
                        "                                <td valign=\"top\" id=\"templateFooter\">\n" +
                        "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                        "                                        <tbody class=\"mcnTextBlockOuter\">\n" +
                        "                                            <tr>\n" +
                        "                                                <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                        "                                                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                        "                                                        <tbody>\n" +
                        "                                                            <tr>\n" +
                        "                                                                <td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px; text-align: center\" >\n" +
                        "                                                                    <em>Copyright @ 2017 arloor.com, All rights reserved.</em>\n" +
                        "                                                                    <br>\n" +
                        "                                                                    <strong>Our mailing address is:</strong><br>\n" +
                        "                                                                    151250095@smail.nju.edu.cn\n" +
                        "                                                                    <br>\n" +
                        "                                                                    <br>\n" +
                        "                                                                </td>\n" +
                        "                                                            </tr>\n" +
                        "                                                        </tbody>\n" +
                        "                                                    </table>\n" +
                        "                                                </td>\n" +
                        "                                            </tr>\n" +
                        "                                    </table>\n" +
                        "                                </td>\n" +
                        "                            </tr>\n" +
                        "                            <!-- footer end -->\n" +
                        "\n" +
                        "                            </tbody>\n" +
                        "                        </table>\n" +
                        "                    </td>\n" +
                        "                </tr>\n" +
                        "            </tbody>\n" +
                        "        </table>\n" +
                        "    </center>\n" +
                        "</body>\n" +
                        "</html>");

        return content.toString();
    }

}

