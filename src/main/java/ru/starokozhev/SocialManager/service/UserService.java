package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.UserFilter;
import ru.starokozhev.SocialManager.dto.UserWrapper;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.enums.Role;
import ru.starokozhev.SocialManager.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final static String USER_NOT_FOUND = "Пользователь не найден";
    private final static String USER_EXIST = "Пользователь с таким логином или электронной почтой уже существует!";
    private final static Double DEFAULT_USER_BALANCE = 0D;

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public UserWrapper add(UserWrapper wrapper) {
        User userFormDb = userRepository.findUserByEmailIgnoreCaseOrLoginIgnoreCase(wrapper.getEmail(), wrapper.getLogin());

        if (userFormDb != null)
            throw new IllegalArgumentException(USER_EXIST);

        User user = new User();
        wrapper.fromWrapper(user);

        user.setBalance(DEFAULT_USER_BALANCE);
        user.setDateLastAuth(LocalDateTime.now());
        user.setEmail(wrapper.getEmail());
        user.setPassword(passwordEncoder.encode(wrapper.getPassword()));
        user.setLogin(wrapper.getLogin());
        user.setRole(Role.USER);
        user.setDateRegister(LocalDateTime.now());

        return new UserWrapper(userRepository.save(user));
    }

    //TODO add method @GET CURRENT USER@

    @Transactional
    public UserWrapper edit(UserWrapper wrapper) {
        User userFromDb = userRepository.findUserById(wrapper.getId());

        //TODO выводить id аккаунта, который ищем
        if (userFromDb == null)
            throw new IllegalArgumentException(USER_NOT_FOUND);

        wrapper.fromWrapper(userFromDb);

        if (wrapper.getPassword() != null)
            userFromDb.setPassword(passwordEncoder.encode(wrapper.getPassword()));

        return new UserWrapper(userRepository.save(userFromDb));
    }

    public UserWrapper getById(Long id) {
        User userFromDb = userRepository.findUserById(id);

        //TODO выводить id аккаунта, который ищем
        if (userFromDb == null)
            throw new IllegalArgumentException(USER_NOT_FOUND);

        return new UserWrapper(userFromDb);
    }

    public void delete(Long id) {
        User userFromDb = userRepository.findUserById(id);

        if (userFromDb == null)
            throw new IllegalArgumentException(USER_NOT_FOUND);

        userFromDb.setDateClose(LocalDateTime.now());

        userRepository.save(userFromDb);
    }

    /*public List<UserWrapper> list() {
        return userRepository.findAllByDateCloseIsNullOrderByIdDesc()
                .stream().map(UserWrapper::new).collect(Collectors.toList());
    }*/

    public List<UserWrapper> list(UserFilter filter) {
        //TODO specifications
        return null;
    }

}
