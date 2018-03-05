package corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description
 * @Author qicaizhao
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplicationMain.class, args);
    }

}
