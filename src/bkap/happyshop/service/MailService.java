package bkap.happyshop.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import bkap.happyshop.bean.MailInfo;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void send(MailInfo mail) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper hepler= new MimeMessageHelper(message,true,"utf-8");
		hepler.setFrom(mail.getFrom()); //Gửi từ đâu
		hepler.setTo(mail.getTo());//Gửi đến
		hepler.setSubject(mail.getSubject());//Thiết lập tiêu đề mail
		hepler.setText(mail.getBody(),true);//Nôi dung mail
		hepler.setReplyTo(mail.getFrom());//Địa chỉ khi có phản hồi
		
		if (mail.getCc()!=null) {
			hepler.setCc(mail.getCc());
		}
		if (mail.getBcc()!= null) {
			hepler.setBcc(mail.getBcc());
		}
		if (mail.getFiles()!=null) {
			String[] paths = mail.getFiles().split(";");
			for (String path : paths) {
				File file = new File(path);
				hepler.addAttachment(file.getName(),file);
			}
		}
		javaMailSender.send(message);
	}
}
