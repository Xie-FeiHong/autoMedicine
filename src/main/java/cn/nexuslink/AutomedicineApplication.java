package cn.nexuslink;

import cn.nexuslink.socket.ReceiveSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class AutomedicineApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AutomedicineApplication.class, args);
//	}
//}

@SpringBootApplication
public class AutomedicineApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(AutomedicineApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AutomedicineApplication.class, args);
		new Thread(new ReceiveSocket()).start();
	}
}

