import com.bianyiit.ServerConsumerApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest(classes = ServerConsumerApp.class)
public class LoadBalanceTest {
    //通过LoadBalancerClient对象的choose方法可以获取服务实例
    @Autowired
    LoadBalancerClient client;

    @Test
    public void testloadBalance(){
        for (int i = 0; i < 10; i++) {
            /*获取服务实例*/
            ServiceInstance instance = client.choose("server-provider");
            System.out.println(instance.getHost()+":"+instance.getPort());
        }
    }
}
