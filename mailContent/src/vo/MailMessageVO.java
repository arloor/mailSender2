package vo;

/**
 * Created by arloor on 2017/3/21.
 */
public class MailMessageVO {
    String title;
    private String[] addrs;
    private String contentBeforeWeather;
    private int intWithWeather;
    private String contentAfterWeather;

    public MailMessageVO(String title, String[] addrs, String contentBeforeWeather, int intWithWeather, String contentAfterWeather) {
        this.title = title;
        this.addrs = addrs;
        this.contentBeforeWeather = contentBeforeWeather;
        this.intWithWeather = intWithWeather;
        this.contentAfterWeather = contentAfterWeather;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAddrs() {
        return addrs;
    }

    public void setAddrs(String[] addrs) {
        this.addrs = addrs;
    }

    public String getContentBeforeWeather() {
        return contentBeforeWeather;
    }

    public void setContentBeforeWeather(String contentBeforeWeather) {
        this.contentBeforeWeather = contentBeforeWeather;
    }

    public int getIntWithWeather() {
        return intWithWeather;
    }

    public void setIntWithWeather(int intWithWeather) {
        this.intWithWeather = intWithWeather;
    }

    public String getContentAfterWeather() {
        return contentAfterWeather;
    }

    public void setContentAfterWeather(String contentAfterWeather) {
        this.contentAfterWeather = contentAfterWeather;
    }
}
