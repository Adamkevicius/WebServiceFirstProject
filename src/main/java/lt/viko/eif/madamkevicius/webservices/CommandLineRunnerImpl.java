package lt.viko.eif.madamkevicius.webservices;


import lt.viko.eif.madamkevicius.webservices.menu.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {


    private UserMenu userMenu;

    @Autowired
    public CommandLineRunnerImpl(UserMenu userMenu) {
        this.userMenu = userMenu;
    }

    @Override
    public void run(String... args) throws Exception {
        userMenu.showMenu();
    }
}
