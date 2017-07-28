package com.lesson.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringTokenizer;

import com.lesson.bean.MailMessage;

import sun.misc.BASE64Encoder;


public class SendMail {
	private String server_mail = "smtp.163.com";
	private  String subject = "验证码，请在5分钟内验证，过期无效";
	private String content;
	private static final String password = "br2br2";
	private static final BASE64Encoder encode = new BASE64Encoder();
	private boolean isSuccess = true;
	public SendMail() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i = 0;i < 4;i++){
			sb.append(base.charAt(random.nextInt(base.length())));
		}
		content = new String(sb);
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public boolean send(String targetMail) throws UnknownHostException, IOException{
		MailMessage message = new MailMessage();
		message.setFrom("13151567657@163.com");
		message.setTo(targetMail);
		String server = server_mail;
		message.setContent(content);
		message.setSubject(subject);
		message.setFrom("13151567657@163.com");
		message.setDatafrom("上课啦@163.com");
		message.setDatato(targetMail);
		message.setUser("13151567657");
		message.setPassword(password);
		Socket socket = new Socket(server_mail, 25);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		helo(server, in, out);// helo
		authLogin(message, in, out);// auth login
		mailfrom(message.getFrom(), in, out);// mail from
		rcpt(message.getTo(), in, out);// rcpt to
		data(message.getDatafrom(), message.getDatato(), message.getSubject(),
				message.getContent(), in, out);// DATA
		return isSuccess;
		
	}

	// 娉ㄥ唽鍒伴偖浠舵湇鍔″櫒
	public void helo(String server, BufferedReader in, BufferedWriter out)
			throws IOException {
		int result;
		result = getResult(in);

		// 杩炴帴涓婇偖浠舵湇鍔″悗,鏈嶅姟鍣ㄧ粰鍑�20搴旂瓟
		if (result != 220) {
			isSuccess = false;
		}

		result = sendServer("HELO " + server, in, out);

		// HELO鍛戒护鎴愬姛鍚庤繑鍥�50
		if (result != 250) {
			isSuccess = false;
		}
	}

	private int sendServer(String str, BufferedReader in,
			BufferedWriter out) throws IOException {
		out.write(str);
		out.newLine();
		out.flush();

		return getResult(in);
	}

	public int getResult(BufferedReader in) {
		String line = "";

		try {
			line = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 浠庢湇鍔″櫒杩斿洖娑堟伅涓鍑虹姸鎬佺爜,灏嗗叾杞崲鎴愭暣鏁拌繑鍥�
		StringTokenizer st = new StringTokenizer(line, " ");

		return Integer.parseInt(st.nextToken());
	}

	public void authLogin(MailMessage message, BufferedReader in,
			BufferedWriter out) throws IOException {
		int result;
		result = sendServer("AUTH LOGIN", in, out);

		if (result != 334) {
			isSuccess = false;
		}
		result = sendServer(encode.encode(message.getUser().getBytes()), in,
				out);

		if (result != 334) {
			isSuccess = false;
		}
		result = sendServer(encode.encode(message.getPassword().getBytes()),
				in, out);

		if (result != 235) {
			isSuccess = false;
		}
	}

	// 寮�鍙戦�娑堟伅锛岄偖浠舵簮鍦板潃
	public void mailfrom(String source, BufferedReader in,
			BufferedWriter out) throws IOException {
		int result;
		result = sendServer("MAIL FROM:<" + source + ">", in, out);

		if (result != 250) {
			isSuccess = false;
		}
	}

	// 璁剧疆閭欢鏀朵欢浜�
	public void rcpt(String touchman, BufferedReader in,
			BufferedWriter out) throws IOException {
		int result;
		result = sendServer("RCPT TO:<" + touchman + ">", in, out);

		if (result != 250) {
			isSuccess = false;
		}
	}

	// 閭欢浣�
	public void data(String from, String to, String subject,
			String content, BufferedReader in, BufferedWriter out)
			throws IOException {
		int result;
		result = sendServer("DATA", in, out);

		// 杈撳叆date鍥炶溅鍚�鑻ユ敹鍒�54搴旂瓟鍚�缁х画杈撳叆閭欢鍐呭
		if (result != 354) {
			isSuccess = false;
		}

		out.write("From: " + from);
		out.newLine();
		out.write("To: " + to);
		out.newLine();
		out.write("Subject: " + subject);
		out.newLine();
		out.newLine();
		out.write(content);
		out.newLine();

		// 鍙ョ偣鍔犲洖杞︾粨鏉熼偖浠跺唴瀹硅緭鍏�
		result = sendServer(".", in, out);
		// System.out.println(result);

		if (result != 250) {
			isSuccess = false;
		}
	}
}
