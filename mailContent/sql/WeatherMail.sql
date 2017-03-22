-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2017-03-22 08:17:49
-- 服务器版本： 5.5.52-MariaDB
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `WeatherMail`
--

-- --------------------------------------------------------

--
-- 表的结构 `cities`
--

CREATE TABLE IF NOT EXISTS `cities` (
  `cname` VARCHAR(20) NOT NULL,
  `url`   VARCHAR(60) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `cities`
--

INSERT INTO `cities` (`cname`, `url`) VALUES
  ('南京', 'http://www.weather.com.cn/weather/101190101.shtml'),
  ('溧阳', 'http://www.weather.com.cn/weather/101191102.shtml'),
  ('颍上', 'http://www.weather.com.cn/weather/101220803.shtml');

-- --------------------------------------------------------

--
-- 表的结构 `emails`
--

CREATE TABLE IF NOT EXISTS `emails` (
  `user`  VARCHAR(10) NOT NULL,
  `addr`  VARCHAR(30) NOT NULL,
  `theme` VARCHAR(30) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `emails`
--

INSERT INTO `emails` (`user`, `addr`, `theme`) VALUES
  ('刘港欢', '1293181335@qq.com', '今日天气'),
  ('李芳悦', '792534691@qq.com', '欢欢恶势力给悦悦小公举的天气预报');

-- --------------------------------------------------------

--
-- 表的结构 `mailMessage`
--

CREATE TABLE IF NOT EXISTS `mailMessage` (
  `id`                   INT(11)  NOT NULL,
  `title`                TEXT     NOT NULL,
  `emails`               TEXT     NOT NULL,
  `contentBeforeWeather` LONGTEXT NOT NULL,
  `withWeather`          INT(11)  NOT NULL DEFAULT '1',
  `contentAfterWeather`  LONGTEXT NOT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `mailMessage`
--

INSERT INTO `mailMessage` (`id`, `title`, `emails`, `contentBeforeWeather`, `withWeather`, `contentAfterWeather`) VALUES
  (1, 'MailSender大功告成', 'admin@arloor.com',
   '<!doctype html>\n<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office">\n<head>\n    <meta charset="UTF-8">\n    <meta http-equiv="X-UA-Compatible" content="IE=edge">\n    <meta name="viewport" content="width=device-width, initial-scale=1">\n    <title>MailSender大功告成</title>\n</head>\n<body>\n    <center>\n        <table align="center" border="0" cellpadding="0" cellspacing="0" height="100%" width="450px" id="bodyTable">\n            <tbody>\n                <tr>\n                    <td align="center" valign="top"  id="bodyCell">\n                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="templateContainer" style="max-width:450px">\n                            <tbody class="mcnTextBlockOuter">\n\n\n                            <!-- header begin -->\n                            <tr bgcolor="#1a8556">\n                                <td valign="top" class="mcnTextBlockInner" style="padding-top:9px;"></td>\n                                <table bgcolor="#1a8556" align="center" border="0" cellpadding="0" cellspacing="0" style="text-align: center; max-width:450px; min-width:450px;" width="100%" class="mcnTextContentContainer">\n                                    <tbody><tr>\n\n                                        <td valign="top" class="mcnTextContent" style="padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;">\n\n                                            <h1 style="font-family:Courier New;color: antiquewhite">MailSender大功告成</h1>\n                                            <h4 style="font-family:Courier New;color: antiquewhite">love my love</h4>\n\n                                    </tr>\n                                    </tbody></table>\n                            </tr>\n                            <!-- header end-->\n\n                            <!--body begin -->\n                            <tr>\n                                <td valign="top" id="templateBody" bgcolor="#d8bfd8" width="450px" style=" max-width:450px; min-width:450px;">\n                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="mcnTextBlock" style="max-width:450px; min-width:450px;">\n                                        <tbody class="mcnTextBlockOuter">\n                                            <tr>\n                                                <td valign="top" class="mcnTextBlockInner" style="padding-top:9px;">\n                                                    <table align="left" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100%;height: 360px">\n                                                        <tr>\n                                                            <td valign="top"  style="width:450px;"><![endif]-->\n                                                                 <table align="center"    border="0" cellpadding="0" cellspacing="0" style="max-width:100%; min-width:100%;" width="100%" class="mcnTextContentContainer">\n                                                                    <tbody>\n                                                                         <tr >\n                                                                            <td  valign="top" class="mcnTextContent" style="padding-top:40px; padding-right:18px; padding-bottom:9px; padding-left:18px;">\n',
   0,
   '<!-- 特殊日子追加内容的模板2 使用h3标签 -->\n<div style="font-family: Consolas; font-size: 20px;text-align: left;line-height:200%;"><p align="center" style="font-size: xx-large">mailSender二期功能完成（伪微信公众号）</p><h3>经过两天的代码，完成了自己对这个项目二期功能的设想。现在不通过本地修改supercontent.txt，然后上传至服务器这种笨拙的方式来修改天气邮件的内容了。现在的工作方式是：在web上设定邮件天气以外（可以选择不包含天气）的内容，然后保存到数据库，然后DailyWeatherMail类会定时（通过linux crontab实现）从数据库读取邮件内容和收件人主题，发送天气邮件。</h3><img src="https://www.arloor.com/wordpress/wp-content/uploads/2017/03/dsc_7362_1.jpg" width="414" alt="图片显示失败"><h3>之前看到志愿者群里有一个小天使每天都会早起在群里发当日的天气，所以有了编写程序来自动发送天气邮件的想法，当时的自己对linux，数据库，服务器和javamail的基础知识有一些了解，判断自己能够做出这样一个小玩意，然后就尝试尝试做了，期间接触到的新的知识有jsoup,JavaMail发送html邮件。下面是一期所有产物的截图。</h3><img src="https://www.arloor.com/wordpress/wp-content/uploads/2017/03/BPMGGFBRQNBONXUIV8I_U.png" width="414" alt="图片显示失败"><h3>跟现在只有一个定时发送邮件只需要一个weather.jar的相比，这个阵容好像庞大，其实这实在太不人性化了，更改天气后追加的内容，需要修改并上传supercontent.txt才可以。</h3><h3>后来，因为软工课可能需要使用javaweb所以接触到了web一些相关的东西，期间html，css，bootstrap，js都接触过了一些，寒假也把jsp，servlet给过了一下，有了这些知识，觉得做成web来设定邮件内容很方便，而且觉得对当时的自己也不是很困难了，所以就尝试了一下。当时做成了一个相当于邮件编辑的网页，只有一个servlet一个jsp，下图展示。</h3><img src="https://www.arloor.com/wordpress/wp-content/uploads/2017/03/screenshot_20170316-002558.png" width="414" alt="图片显示失败"><img src="https://www.arloor.com/wordpress/wp-content/uploads/2017/03/screenshot_20170316-002611.png" width="414" alt="图片显示失败"><h3>然后的话，就开始整合两个不相互依赖的项目了。这期间新学习或者说巩固的知识就是js了。这一阶段的话就是一些优化工作了。（现在仍然有很不人性化的东西，但是现在的我不知道怎么去改。。。）现在项目的样子是（没有设置权限和登陆系统，所以网址保密，不然的话通过这个发一些不干净的东西，找到的是我）：</h3><img src="https://www.arloor.com/wordpress/wp-content/uploads/2017/03/wp-1490167209138.jpg" width="414" alt="图片显示失败"><h3>整个过程太难的东西没有，但是通过这个过程，还是学习了很多啊，从做一些简单的前端（不好意思说开发前端）到后端开发，从编程到测试到部署，意义还是挺大的，算是走完了一个项目开发的全部周期吧。</h3><h3>有了知识才会产生想法。有了之前接触的那些东西，才会想到可以做出这样一个东西。前面提到的那些东西如果少了一个，我都会对这个mailsender的可行性画个叉吧。软工一的时候选后端的工作没有错啊，通过软工一还是接触了不少的。要是说这个项目最大的喜悦的话，就是现在我还是能做一个完整的东西给自己玩一玩了。</h3></div>                                                                           </td>\n                                                                       </tr>\n                                                                    </tbody>\n                                                                 </table>\n                                                            </td>\n                                                         </tr>\n                                                    </table>\n                                                 </td>\n                                            </tr>\n                                        </tbody>\n                                    </table>\n                                </td>\n                            </tr>\n                            <!-- body end-->\n\n                            <!--footer begin-->\n                            <tr>\n                                <td valign="top" id="templateFooter">\n                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="mcnTextBlock" style="min-width:100%;">\n                                        <tbody class="mcnTextBlockOuter">\n                                            <tr>\n                                                <td valign="top" class="mcnTextBlockInner" style="padding-top:9px;">\n                                                    <table align="left" border="0" cellpadding="0" cellspacing="0" style="max-width:100%; min-width:100%;" width="100%" class="mcnTextContentContainer">\n                                                        <tbody>\n                                                            <tr>\n                                                                <td valign="top" class="mcnTextContent" style="padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px; text-align: center" >\n                                                                    <em>Copyright @ 2017 arloor.com, All rights reserved.</em>\n                                                                    <br>\n                                                                    <strong>Our mailing address is:</strong><br>\n                                                                    mail@arloor.com\n                                                                    <br>\n                                                                    <br>\n                                                                </td>\n                                                            </tr>\n                                                        </tbody>\n                                                    </table>\n                                                </td>\n                                            </tr>\n                                    </table>\n                                </td>\n                            </tr>\n                            <!-- footer end -->\n\n                            </tbody>\n                        </table>\n                    </td>\n                </tr>\n            </tbody>\n        </table>\n    </center>\n</body>\n</html>');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`cname`);

--
-- Indexes for table `emails`
--
ALTER TABLE `emails`
  ADD PRIMARY KEY (`user`);

--
-- Indexes for table `mailMessage`
--
ALTER TABLE `mailMessage`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mailMessage`
--
ALTER TABLE `mailMessage`
  MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 22;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
