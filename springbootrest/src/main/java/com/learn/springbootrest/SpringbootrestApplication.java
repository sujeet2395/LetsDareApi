package com.learn.springbootrest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SpringbootrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestApplication.class, args);
		System.out.println("Hello");
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    System.out.println(formatter.format(date));
	    formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");  
	    String strDate = formatter.format(date);  
	    
	    System.out.println("Date Format with E, dd MMM yyyy HH:mm:ss z : "+strDate);  
	}

}
/*
public class SpringbootrestApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootrestApplication.class, args);
		ApiClient ac=new ApiClient();
		ac.setName("sujeet");
		ac.setAPIKey("apikey");
		ac.setGrant(ApiGrant.APPFULLACCESS);
		SubjectWrapper sw =new SubjectWrapper(new ObjectMapper());
		String st=sw.getSubject(ac);
		ApiClient a=(ApiClient) sw.convertSubject(st, ApiClient.class);
		System.out.println(st+" "+a);
		
		User u
		=new User();
		u.setName("Sujeet Kumar");
		//sw =new SubjectWrapper(new ObjectMapper());
		String stu=sw.getSubject(u);
		User ua=(User) sw.convertSubject(stu, User.class);
		System.out.println(stu+" "+ua);
		
		//sw =new SubjectWrapper(new ObjectMapper());
		String sts=sw.getSubject("String");
		String s=(String)sw.convertSubject(sts, String.class);
		System.out.println(sts+" "+s);
		JwtCreator jwtCreator=new JwtCreator();
		String st_t= jwtCreator.generateToken(st);
		String stu_t=jwtCreator.generateToken(stu);
		String sts_t=jwtCreator.generateToken(sts);
		System.out.println(st_t+"\n"+stu_t+"\n"+sts_t);
		
		System.out.print(jwtCreator.validateToken(st_t, st));
		System.out.print(jwtCreator.validateToken(stu_t, stu));
		System.out.print(jwtCreator.validateToken(sts_t, sts));
		
		String to=jwtCreator.generateToken(a.toString());
		System.out.println(to);
		System.out.println(jwtCreator.validateToken(to,a.toString()));
		//System.out.println(RandomStringGenerator.nextString(30, ""));
	}

}*/