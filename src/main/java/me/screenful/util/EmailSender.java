package me.screenful.util;

import com.postmark.java.*;

import java.util.*;
import java.io.*;

/**
 * Hello world!
 *
 */
public class EmailSender 
{
    public static void SendEmail(String emailID)
    {
        PostmarkMessage message = new PostmarkMessage(
        	"support@screenful.me", // fromAddress
        	emailID, // toAddress
        	"sayed@screenful.me", // replyToAddress
        	"", // ccAddress
        	"Testing ...", // subject
        	"Hello world!", // body
        	false, // isHTML
        	"" // tag
        	);

        try {
        	Attachment attachment = new Attachment();
	        attachment.setContentType("image/png");
	        attachment.setName("snap1.png");

		  	// convert file contents to base64
	        File file = new File("snap1.png");
	        byte[] ba = new byte[(int) file.length()];
	        FileInputStream fis = new FileInputStream(file);
	        fis.read(ba);
	        fis.close();
	        attachment.setContent(Base64.getEncoder().encodeToString(ba));
		  	
		  	// can handle multiple attachments
	        Vector<Attachment> v = new Vector<Attachment>();
	        v.add(attachment);
	        message.setAttachments(v);
    	} catch (FileNotFoundException fe) {
    		System.out.println("Attachment file not found : " + fe.getMessage());
    	} catch (IOException ie) {
    		System.out.println("An error occured while processing attachment : " 
    			+ ie.getMessage());    		
    	}

        String apiKey = "5913eaa0-923a-40ed-b1bd-a5497af2dac3";
        
        PostmarkClient client = new PostmarkClient(apiKey);

        try {
            client.sendMessage(message);
        } catch (PostmarkException pe) {
            System.out.println("An error has occurred : " + pe.getMessage());
        }
    }
}
