package helper;

import vo.MailMessageVO;

import java.util.Map;

/**
 * Created by arloor on 2017/3/22.
 */
public class DailyWeatherMail {
    public static void main(String[] args) {
        DataHelper dataHelper = new DataHelper();
        JavaMail javaMail = new JavaMail();
        boolean boolWithWeather;
        MailMessageVO mailMessageVO = dataHelper.getLastMailContent();

        if (mailMessageVO.getIntWithWeather() == 1) {
            boolWithWeather = true;
        } else boolWithWeather = false;
        String lastContent = mailMessageVO.getContentBeforeWeather() + javaMail.createWeatherContent(boolWithWeather) + mailMessageVO.getContentAfterWeather();

        Map<String, String> emailThemeMap = dataHelper.getDailyWeatherEmails();

        for (Map.Entry<String, String> cell : emailThemeMap.entrySet()
                ) {
            javaMail.sendEmail(cell.getKey(), cell.getValue(), lastContent);
        }
    }
}
