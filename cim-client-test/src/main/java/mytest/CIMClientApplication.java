package mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author crossoverJie
 */
@Slf4j
@SpringBootApplication
public class CIMClientApplication implements CommandLineRunner {

	@Resource
	private CIMClient cIMClient;

	public static void main(String[] args) {
        SpringApplication.run(CIMClientApplication.class, args);
        log.info("启动成功");
	}

	@Override
	public void run(String... strings) throws Exception {
		Thread.sleep(10*1000);

		int n = 10;
		while(n-->0){
			String msg = "hello;;;;;;;;;;;;;;;;;;;="+n;
			log.info(msg);
			cIMClient.getChannel().writeAndFlush(msg);

			Thread.sleep(10*1000);

		}


	}
}