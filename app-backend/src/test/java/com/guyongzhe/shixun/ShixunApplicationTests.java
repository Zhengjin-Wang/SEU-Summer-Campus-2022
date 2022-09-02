package com.guyongzhe.shixun;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.guyongzhe.shixun.mapper.InspectTaskItemMapper;
import com.guyongzhe.shixun.pojo.InspectEmployee;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectPointLog;
import com.guyongzhe.shixun.pojo.InspectTaskItem;
import com.guyongzhe.shixun.service.InspectEmployeeService;
import com.guyongzhe.shixun.service.InspectPointLogService;
import com.guyongzhe.shixun.service.InspectPointService;
import com.guyongzhe.shixun.service.InspectTaskItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootTest
class ShixunApplicationTests {

    @Autowired
    private InspectEmployeeService inspectEmployeeService;
//
//    @Test
//    void contextLoads() {
//        System.out.println(new Date());
//    }
//
    @Autowired
    private InspectPointService pointService;

    @Autowired
    private InspectPointLogService logService;

    @Test
    public void logTest(){
        InspectPointLog a = new InspectPointLog();
        a.setDescription("asdqw");
        logService.save(a);
    }

    @Test
    public void pointTest(){
        InspectPoint point = new InspectPoint();
        point.setErcode("asd");
        pointService.save(point);
    }

    @Test
    void insertTest(){
        InspectEmployee user = new InspectEmployee();
        user.setWorkNo("admin");
        String password = new BCryptPasswordEncoder().encode("123456");
        user.setPassword(password);
        System.out.println(user);
        inspectEmployeeService.save(user);
    }

    @Autowired
    private InspectTaskItemMapper itemMapper;

    @Test
    void queryPoints(){
        List<InspectPoint> inspectPoints = itemMapper.queryPointsByTaskItemId(1);
        for (InspectPoint point : inspectPoints) {
            System.out.println(point);
        }
    }

    @Test
    void queryTaskName(){
        System.out.println(itemMapper.queryTaskNameByTaskItemId(1));
    }

    @Test
    void timerTest(){
        Date date = new Date();
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        Date date1 = new Date();
//        System.out.println(date1.getTime() - date.getTime());
    }

    @Autowired
    InspectTaskItemService taskItemService;

    @Test
    public void timerTest2(){

            //创建两个定时任务
        Date expStartTime = new Date();
        Date expEndTime = new Date();
        expStartTime.setMinutes(expStartTime.getMinutes() + 1);
        expEndTime.setMinutes(expEndTime.getMinutes() + 1);
            try {
                Timer timer = new Timer();
                //exp_start_time时的行为
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("timer1");
                    }
                }, expStartTime);
                //exp_end_time时的行为
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("timer2");
                    }
                }, expEndTime);
                Thread.sleep(1000 * 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Test
    public void esTest(){
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost("101.132.107.178", 9200)).build();

// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

// And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        try {
            client.indices().create(c -> c.index("device_log"));
            System.out.println("asd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private InspectTaskItemService service;

    @Test
    public void getInspectEmployeeService() {
        for (InspectPoint point : service.queryPointsByTaskItemId(1)) {
            System.out.println(point);
        }
    }

    @Test
    public void adsf(){
        InspectEmployee employee = inspectEmployeeService.getById(1);
        employee.setPassword(new BCryptPasswordEncoder().encode("123456"));
        inspectEmployeeService.updateById(employee);
    }
}
