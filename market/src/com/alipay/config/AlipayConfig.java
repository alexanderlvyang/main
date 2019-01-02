package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092100563760";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuyIiLuDi92/boYA1h+EOxrdd90sOQcMUqoSnYh4xWfueDwsSSwYdngF+qfdhzPmT8CULrKsEJhtHFv31/7SUhiRSuoeFa5zUjKbZeUPZfx26N35zFVjPVPFhGBOTHmzUCs9zgdhgUadGVYcTOiUtKo55Pnlgsf3QOLJB57jejyNZ6MMUPaH0Bchmso3OkhHsBYIyFIMA1jA4uXsSIOkPpN/thnmLkaUfW19U4pGFLni3eXP/UOExjnc3Ab2YFwIy6t+k516aawWq3mBTnboz7JalpoZQ2unrNrfJJ9NRgG1zG0JXNvuxZ2vHVwwFn8a54Z6O3amVMcASwOoV7co+9AgMBAAECggEAKAiQlggdHweZ90HA7vaxcqoR2KUE3DmoP4pojksFzu4EUz6yTbb8L63Mu71VbZgWA1CPvlOtoWP6+mNQ+JM11QeT8Cft+SdKGYK9Xb8Hn4qLfP2B6AVFnArVYTxPtpSLQNI3vkXuYAaY1bFuJax2zWLKqQvowFB1IaX9daAlqGK+bus8xce+cWYU5KP4QD/PGyxhCi3n7W9+XvCb6W6Wzzm55hKodeg4d1WJUyv0HM86I8qUk5gqBe7pG5ru9uFcAPq3ciFwO4dGAruvZgcKqcucWfT9RE8Kja2pD1VBFC51QrEj2g9lXcveHhmz2pn6UhoH7XeZmGHztYnENlQgFQKBgQD/uR9TcDweZeVhphaxlMPY9CTqH77aNMDrGdbiVfQo6dn4QgJ89GToH4QvUreRHAehItT3tvp50MPF4e+Ukx46Nsh9km2khprIZzZHPLW+YuE1sP8yEfxYEH54SLm7tSuOrivbF5h9RRzZ+dHVEoFNVpA+YNIrURshl3anksoydwKBgQCu+Pov7cjAnY2ZxrfsiiqM40V874FGun0z7BzfCmjkTtj4d9IWU9/n3fiv3N1SvVHFa5YT+x7b1RdW+X7NYAFiWlhL3hvXu9pXcjSkSAWfzQubpwL1D15cpIWAkh4dQ9Fw7USV9NZFpzd8dlHEyvHslSw0qD2vVOzAuWg9NLJIawKBgQDAeeZKN0AI4Uk1pn2OTBrdQWZHkn1kYDCUxG4pDekooludsJGO26TX0mD7/ZaNcjuL3VvO17rP1YKFfvmFKvalenaNjZlZLLdz/7urAtcI5gP9Fav/U+pLnp6SiqrBzitqyU3ZKnlsUIjWyGJXrgYE4BLR4K5QTEvPjOGSX7oxIQKBgDFxS4k1oQ/EtZmpjJl7pXBwXFdkelxZID4Gz1W7ZE53dVooyaXjh5bGo5pWR/g0ZSHrN9pnkJ3ABjtefxVbtqhrLR4rIiUBAT7Kl9OAQo6Ya1pNhElpUVnOZ6cgIwBUBJ/rfAD/0XiXOtehu5NnYZtfv8EVdXDRB6GiArGg7WwHAoGBAPpCaz4Z0m0h9Ky6SYYR5gJ/NmFb09ocm5R5laHT0UYsEmlyb4AxaWlkG9n2H1e+zvRY2mkLTitiOZ3SuQNy3VFm3y8SZCJftNxyt+iuPSo5oxDUhS1/jbCAh6HMf6/ZQSAMEBeh6H7xmx4RJUqWrkC0I/NxJaSak9K+iKTwFYxM";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqJRPmt8LvoZqKqttUVLQ0IKepJHk4eAr0bt+nGF9NHe1MPYFCnO7cm6DDtKMeOk8kVP5tZGi0GDtMg9DAx+xJH4hOxUXfHQAednsMde/KGU+bLMbxNMjaY7cqX15Udwh7RcFAvYT+a2sz5xES+zEQUmE7zM3rKu1kjrwTafNUDOePCjbR2Piwht8gGcYx9P5VZ6u3aBenONCUyBwf/rYyROghZOoxA7e0EykhZ6EMTPfWK719DOFldMyM9UhCsa9ppQDd5FeLZS9MhDlVW1RnzBWXSTL99xSOPIEJiZ1oG2rQ22C7hbJ+/XFNBUQx1Y+LgbTAJuz55ylTMuiVY1ZLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/market/return_url.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C://";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

