package helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arloor on 2017/2/10.
 */
public class WeatherGetter {

    public ArrayList<String> getWeathers() {
        String urlXml = "../webapps/mail/cities.xml";
        Map<String, String> urls = new DataHelper().getUrlsByMysql();


        ArrayList<String> weathers = new ArrayList<String>();

        for (Map.Entry<String, String> cell : urls.entrySet()
                ) {
            weathers.add(getWeather(cell.getKey(), cell.getValue()));
        }
        return weathers;
    }


    public String getWeather(String location, String url) {
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Element> list = doc.getElementsByAttributeValue("id", "hidden_title");

        String weather = location + " ";
        for (Element link : list
                ) {
            weather += link.attr("value").substring(link.attr("value").indexOf(" "));
        }
        System.out.println(weather);
        return weather;
    }

    //解析xml文档来获取要查询天气的城市
    public static Map<String, String> getUrlsByXml(String fileName) {
        Map<String, String> urls = new HashMap<String, String>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(fileName);
            NodeList rootChilds = doc.getChildNodes();

            for (int i = 0; i < rootChilds.getLength(); i++) {
                Node cities = rootChilds.item(i);
                NodeList city = cities.getChildNodes();
                for (int j = 0; j < city.getLength(); j++) {
                    Node cityInfo = city.item(j);
                    NodeList cityInfoList = cityInfo.getChildNodes();
                    for (int k = 1; k < cityInfoList.getLength(); k = k + 2) {//这里要注意，因为必须从1开始，每次递增2，因为两个节点之间好像有空节点
                        //System.out.println(k+" "+cityInfoList.item(k).getNodeName()+" "+cityInfoList.item(k).getTextContent());
                        urls.put(cityInfoList.item(k).getTextContent(), cityInfoList.item(k + 2).getTextContent());
                        k += 2;
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
