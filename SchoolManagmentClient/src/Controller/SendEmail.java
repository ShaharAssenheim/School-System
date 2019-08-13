package Controller;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


/**
 * The Class SendEmail.
 */
public class SendEmail  {
 
    /** The email address to. */
    private String emailAddressTo = new String();
    
    /** The msg subject. */
    private String msgSubject = new String();
    
    /** The msg text. */
    private String msgText = new String();

    /** The user name. */
    final String USER_NAME = "asisschoolmenegment@gmail.com";   //User name of the Google(gmail) account
    
    /** The passsword. */
    final String PASSSWORD = "asis2017";  //Password of the Google(gmail) account
    
    /** The from address. */
    final String FROM_ADDRESS = "asisschoolmenegment@gmail.com";  //From address
 
    /**
     * Instantiates a new send email.
     */
    public SendEmail() {
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	SendEmail email = new SendEmail();
     //Sending test email
      email.createAndSendEmail("lasanthals@gmail.com", "Test email subject",
      "Congratulations !!! \nThis is test email sent by java class.");
    }

    /**
     * Creates the and send email.
     *
     * @param emailAddressTo the email address to
     * @param msgSubject the msg subject
     * @param msgText the msg text
     */
    public void createAndSendEmail(String emailAddressTo, String msgSubject, String msgText) {
        this.emailAddressTo = emailAddressTo;
        this.msgSubject = msgSubject;
        this.msgText = msgText;
        sendEmailMessage();
    }
  
    /**
     * Send email message.
     */
    private void sendEmailMessage() {
     
     //Create email sending properties
     Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");
  
    Session session = Session.getInstance(props,
    new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(USER_NAME, PASSSWORD);
   }
    });

  try {

     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress(FROM_ADDRESS)); //Set from address of the email
     message.setContent(msgText,"text/html"); //set content type of the email
     
    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailAddressTo)); //Set email recipient
    
    message.setSubject(msgSubject); //Set email message subject
    Transport.send(message); //Send email message

    
   System.out.println("sent email successfully!");

  } catch (MessagingException e) {
       throw new RuntimeException(e);
  }
    }

    /**
     * Sets the email address to.
     *
     * @param emailAddressTo the new email address to
     */
    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    /**
     * Sets the subject.
     *
     * @param subject the new subject
     */
    public void setSubject(String subject) {
        this.msgSubject = subject;
    }

    /**
     * Sets the message text.
     *
     * @param msgText the new message text
     */
    public void setMessageText(String msgText) {
        this.msgText = msgText;
    }
  
}
