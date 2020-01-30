package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.ProxyWrapper;
import ru.starokozhev.SocialManager.dto.filter.ProxyFilter;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Proxy;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.repository.BotRepository;
import ru.starokozhev.SocialManager.repository.ProxyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProxyService {

    private final ProxyRepository proxyRepository;
    private final BotRepository botRepository;
    private final UserService userService;

    public ProxyWrapper add(ProxyWrapper wrapper) {
        Proxy proxy = new Proxy();
        User user = userService.getCurrentUser();

        Proxy existProxy = proxyRepository.findProxyByIpAndPortAndUser(wrapper.getIp(), wrapper.getPort(), user);
        if (existProxy != null)
            throw new IllegalArgumentException("Прокси уже существует!");

        Bot bot = botRepository.findByNameAndUser(wrapper.getBot().getName(), user);
        if (bot == null)
            throw new IllegalArgumentException("Бот не найден!");

        wrapper.fromWrapper(proxy);
        proxy.setBot(bot);
        proxy.setUser(user);

        proxy.setDateCreate(LocalDateTime.now());

        return new ProxyWrapper(proxyRepository.save(proxy));
    }

    public ProxyWrapper edit(ProxyWrapper wrapper) {
        User user = userService.getCurrentUser();
        Proxy proxy = proxyRepository.findProxyByIpAndPortAndUser(wrapper.getIp(), wrapper.getPort(), user);

        if (proxy == null)
            throw new IllegalArgumentException("Прокси не найден!");

        Bot bot = botRepository.findByNameAndUser(wrapper.getBot().getName(), user);
        if (bot == null)
            throw new IllegalArgumentException("Бот не найден!");

        wrapper.fromWrapper(proxy);
        proxy.setBot(bot);

        return new ProxyWrapper(proxyRepository.save(proxy));
    }

    public ProxyWrapper get(Long id) {
        Proxy proxy = proxyRepository.findProxyById(id);

        if (proxy == null)
            throw new IllegalArgumentException("Прокси не найден!");

        return new ProxyWrapper(proxy);
    }

    public ProxyWrapper getByIpAndPortAndUser(String ip, String port) {
        User user = userService.getCurrentUser();
        Proxy proxy = proxyRepository.findProxyByIpAndPortAndUser(ip, port, user);

        if (proxy == null)
            throw new IllegalArgumentException("Прокси не найден!");

        return new ProxyWrapper(proxy);
    }

    public void delete(Long id) {
        Proxy proxy = proxyRepository.findProxyById(id);

        if (proxy == null)
            throw new IllegalArgumentException("Прокси не найден!");

        proxy.setDateClose(LocalDateTime.now());
        proxyRepository.save(proxy);
    }

    public List<ProxyWrapper> list(ProxyFilter filter) {
        return proxyRepository.findAllByUser(userService.getCurrentUser())
                .stream().map(ProxyWrapper::new).collect(Collectors.toList());
    }
}
