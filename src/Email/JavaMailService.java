package Email;

import Tool.Configuration;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class JavaMailService {
    public static void send(String from, Collection<String> recipients, String subject, String text)
            throws Exception {

        send(from, recipients, subject, text, null, null, null);
    }

    public static void send(String from, String recipient, String subject,
            String text, InputStream attachment, String fileName, String mimeType)
            throws Exception {

        send(from, Arrays.asList(recipient), subject,
                text, Arrays.asList(attachment), Arrays.asList(fileName),
                Arrays.asList(mimeType));
    }

    public static void send(String from, Collection<String> recipients,
            String subject, String text, List<InputStream> attachments,
            List<String> fileNames, List<String> mimeTypes)
            throws Exception {

        // check for null references
        Objects.requireNonNull(from);
        Objects.requireNonNull(recipients);

        // a message with attachments consists of several parts in a multipart
        MimeMultipart multipart = new MimeMultipart();

        // create text part
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(text, "utf-8", "html");

        // add the text part to the multipart
        multipart.addBodyPart(textPart);

        // create attachment parts if required
        if (attachments != null) {
            // check that attachment and fileNames arrays sizes match
            if (attachments.size() != fileNames.size() || attachments.size() != mimeTypes.size()) {
                throw new IllegalArgumentException(
                        "Attachments, file names, and mime types array sizes must match");
            }

            // create parts and add them to the multipart
            for (int i = 0; i < attachments.size(); i++) {
                // create a data source to wrap the attachment and its mime type
                ByteArrayDataSource dataSource = new ByteArrayDataSource(attachments.get(i), mimeTypes.get(i));

                // create a dataHandler wrapping the data source
                DataHandler dataHandler = new DataHandler(dataSource);

                // create a body part for the attachment and set its data handler and file name
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.setDataHandler(dataHandler);
                attachmentPart.setFileName(fileNames.get(i));

                // add the body part to the multipart
                multipart.addBodyPart(attachmentPart);
            }
        }


        // ------------------- Email.Profile ------------------- \\

        // create a Session instance specifying the system properties
        Session session = Session.getInstance(Configuration.getMailProperties());

        // create a message instance associated to the session
        MimeMessage message = new MimeMessage(session);

        // set the multipart as content for the message
        message.setContent(multipart);

        // configure from address, add recipients, and set the subject of the message
        message.setFrom(from);
        message.addRecipients(Message.RecipientType.TO, String.join(",", recipients));
        message.setSubject(subject);

        // send the message
        Transport.send(
                message,
                Configuration.getMailProperty("mail.smtp.username"),
                Configuration.getMailProperty("mail.smtp.password")
        );
    }
}
