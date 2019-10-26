package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.gmail;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import f196695_v206681.ft.unicamp.br.pos_creditos.model.Utils;

// Created by ivan0 on 07/06/2016.
public class GmailSend extends javax.mail.Authenticator {

    private static String username = "contato.poscreditos";

    public static boolean sendEmail(String email_to, String email_subject, String email_body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, Utils.getEmailPassword());
            }
        });

        try {
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_to));
            message.setSubject(email_subject);
            message.setText(email_body);

            new AsyncTask<Void, Void, Void>() {
                @Override
                public Void doInBackground(Void... arg) {
                    try {
                        Transport.send(message);
                    } catch (Exception e) {
                        Log.e("GmailSend", e.getMessage(), e);
                    }
                    return null;
                }
            }.execute();

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
