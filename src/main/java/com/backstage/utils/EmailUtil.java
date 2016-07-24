package com.backstage.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * send email
 *
 * @author
 */
public class EmailUtil {
/*

    */
/**
     * 以文本格式发送邮件
     *
     * @param emailInfo - 待发送的邮件的信息
     * @throws MessagingException
     *//*

    public static boolean sendTextMail(EmailInfo emailInfo) throws MessagingException {
        if (emailInfo != null) {
            Properties pro = emailInfo.getProperties();
            Session sendMailSession = Session.getDefaultInstance(pro);
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(emailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            String[] toArray = emailInfo.getToAddress();
            if (toArray.length == 0)
                return false;
            InternetAddress[] toAddrArray = new InternetAddress[toArray.length];
            for (int i = 0; i < toArray.length; i++) {
                toAddrArray[i] = new InternetAddress(toArray[i]);
            }
            mailMessage.setRecipients(Message.RecipientType.TO, toAddrArray);
            // 设置邮件消息的主题
            mailMessage.setSubject(emailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = emailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        }
        return false;
    }

    */
/**
     * 以HTML格式发送邮件
     *
     * @param emailInfo - 待发送的邮件信息
     * @throws MessagingException
     *//*

    public static boolean sendHtmlMail(EmailInfo emailInfo) throws MessagingException {
        if (emailInfo != null) {
            Properties pro = emailInfo.getProperties();
            Session sendMailSession = Session.getDefaultInstance(pro);
            // sendMailSession.setDebug(true);
            Message mailMessage = new MimeMessage(sendMailSession);
            Address from = new InternetAddress(emailInfo.getFromAddress());
            mailMessage.setFrom(from);
            String[] toArray = emailInfo.getToAddress();
            if (toArray.length == 0)
                return false;
            Address[] toAddrArray = new InternetAddress[toArray.length];
            for (int i = 0; i < toArray.length; i++) {
                toAddrArray[i] = new InternetAddress(toArray[i]);
            }
            mailMessage.setRecipients(Message.RecipientType.TO, toAddrArray);
            mailMessage.setSubject(emailInfo.getSubject());
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(emailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            mailMessage.saveChanges();
            Transport transport = sendMailSession.getTransport("smtp");
            transport.connect(emailInfo.getMailServerHost(), emailInfo.getUsername(), emailInfo.getPassword());
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();
            return true;
        }
        return false;
    }
*/

}
