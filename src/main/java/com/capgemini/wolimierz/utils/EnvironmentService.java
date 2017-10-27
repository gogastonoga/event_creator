package com.capgemini.wolimierz.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class EnvironmentService {

    private final Environment environment;
    private static final String WOLIMIERZ = "/wolimierz";
    private static final String SERVER_PORT = "server.port";
    private static final String MEDIA_ID = "/media?id=";
    @Value("${server.port}")
    private String serverPort;
    @Getter
    private String hostUrl;
    @Getter
    private String mediaBasicUrl;

    @Autowired
    public EnvironmentService(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void getHostUrlWithPort() throws UnknownHostException {
        String port = environment.getProperty(SERVER_PORT);
        String host = InetAddress.getLocalHost().getHostAddress();
        this.hostUrl = host + ':' + port + WOLIMIERZ;
        this.mediaBasicUrl = hostUrl + MEDIA_ID;
    }

}
