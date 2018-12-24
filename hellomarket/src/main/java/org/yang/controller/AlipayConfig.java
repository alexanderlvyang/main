package org.yang.controller;

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
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYLhIArVigLJsJHree709dJTjsIb4OSmqe0V2zOTh0IAppE3IKDlWNdMSUly7ZmwCFbu3Uz5ayxXUCFfimuV/ZBLvcNbUn03o2Uf4S80FK00DoNCDBE5GqdXih0x7wt8oMqNHR10ugB422vFskD3QNMvjUGeS56+U7K1zWUsnvIsAGIexFVnsRt0/jxKE3X9gnokkNyOH1NW5OFzWumEwOzVRovKYGO/1iAOkqiF61pY3s9AExOvg+rxHE+LG/cENDRoPAdEKlnFYILuseM4wGmQtvYcPerUrhHpZR5hhRFRemPMxc0GL24NQ4iuoubZa0YtA/oVF6R5PnFY4mmbGhAgMBAAECggEAPEfDidEgkhIZN3ubcLGK1nKwIZkcF70SjuaQHzZDTnyCoyy4t5MGAIqIc/OO81Oa2UAfpXS1VQsyVikt3Z0Ago6Fe60qnSP//RRteEQzUNhtlY1QjCqcQNFXf4KJdwDWYD1fH6gQpMtZL1FdKOOWLMRMHHhFSgBPRSjgFWWl57tXE6MZrBrQ6r6yb8H7/h3gPT418SaQCUWx6b4ZaTFF71N7V9gztyNareQ3SzEy9c5gNZ2OiKO/27QTi/QdxOIsVNfv9hBtQXxAHOma2/SU075cDHQRYF5bNfYsB6ZTmVFGhkl1Sv+/qRZLzI1mrjIvWHpmZP3IUjZyK7OSov5TgQKBgQDY7nrFIk2GFiJ21zZDQ1Ap2fo+dRUwp1ZsyK3x0HIRIbJPJOLmjphdSQbcGVnI7P1nWNxQcGFN66/jhVosuBl1UBG5GRpb0Qw/By+p0tbZwBDQiSYM9G06Le1e/Dk32nh2JzfZwWERG48FxPySAXr6/xdW4baBufNd3DqT4vQddQKBgQCzlkA6KEbB9u5CObeCy1XUsTtMefgUPBBwXjPTOA7jHXOWoKt7oMAWQ25I3z1G5yygqWo/e/TMfT5M78kGUgyOc+nghq/H4uf1Tl8wMv2L7nV0xv3Oidk8SK0iNqXnA8cniR4UF3rj9DCz/rUMP1IXERs0Q8NkifxIo7BQbUqh/QKBgB3X8RvdtDyWHveqc9SdFhIr4rlk8h1XaBRfJ071Fjw1Hoq00GJXi66JtV/iPO/fap0lJGoMi9ZkbjYmylkMmrQA5+9G/NFU2RJysqPyVYxvV+DYfVua5Mv0NYuzt0tvytK2YoCakfgAipSInRaZDYoqPdO+CZWfN1PaUFS4DE1hAoGAWi8W293EAr3NUmaHmGAiHyhNfg82HHs5SiSUY/qfxz/P/xF6bCACtmtwY5nvvL50s82/9Fd8TICMm7hCvDDR62ucREt+1KNHFvIfbSX+G5pJU7ZmWKTy+YoJn8fPsl48UcdS2oYcCYNkPc+n/ZmC0OCfR7TL155Pme7RqOHpe8UCgYEAolCC/H+kmJP0bQ+Fwv+YY18BdHr08xBQIxDSu4k4TlffqQg6rdZnYTWi7jrSOfhbpVg4MIVUl/Of+2QsIfJHjLecoq/Egz+fwNeZGRwZjBfhmSxbkFMHApJH097ySDh+B46GvdX0Ec4u8mJ+gWjGvBfgyhULia+onWtdv2+zHZw=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqJRPmt8LvoZqKqttUVLQ0IKepJHk4eAr0bt+nGF9NHe1MPYFCnO7cm6DDtKMeOk8kVP5tZGi0GDtMg9DAx+xJH4hOxUXfHQAednsMde/KGU+bLMbxNMjaY7cqX15Udwh7RcFAvYT+a2sz5xES+zEQUmE7zM3rKu1kjrwTafNUDOePCjbR2Piwht8gGcYx9P5VZ6u3aBenONCUyBwf/rYyROghZOoxA7e0EykhZ6EMTPfWK719DOFldMyM9UhCsa9ppQDd5FeLZS9MhDlVW1RnzBWXSTL99xSOPIEJiZ1oG2rQ22C7hbJ+/XFNBUQx1Y+LgbTAJuz55ylTMuiVY1ZLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost/notify_urlpage";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost/homePage";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


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

