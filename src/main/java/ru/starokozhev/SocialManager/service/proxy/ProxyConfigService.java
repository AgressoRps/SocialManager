package ru.starokozhev.SocialManager.service.proxy;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.*;
import java.util.Scanner;

@Service
public class ProxyConfigService {

    @SneakyThrows
    public Proxy configureCurrentProxy(String ip, String port, String username, String password) {
        InetSocketAddress proxyAddress = new InetSocketAddress(ip, Integer.parseInt(port)); // Set proxy IP/port.
        Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
        URL url = new URL("http://ipinfo.io/ip"); //enter target URL
        Authenticator authenticator = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return (new PasswordAuthentication(username, password.toCharArray())); //enter credentials
            }
        };


        Authenticator.setDefault(authenticator);
        URLConnection urlConnection = url.openConnection(proxy);


        //Scanner to view output

        Scanner scanner = new Scanner(urlConnection.getInputStream());
        System.out.println(scanner.next());
        scanner.close();

        return proxy;
    }

}
