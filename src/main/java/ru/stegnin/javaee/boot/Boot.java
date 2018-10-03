package ru.stegnin.javaee.boot;

import ru.stegnin.javaee.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

@Startup
@Singleton
public class Boot {

    private static final Logger LOGGER = Logger.getLogger(Boot.class.getName());

    @Inject
    private UserRepository userRepo;

    @PostConstruct
    private void start() {
        LOGGER.info("***** APPLICATION STARTED *****");
        userRepo.init("guest", "guest@guest.ru", "guest");
        userRepo.init("admin", "admin@admin.ru", "admin");
    }

    @PreDestroy
    private void stop() {
        LOGGER.info("***** APPLICATION STOPPED *****");
    }
}
