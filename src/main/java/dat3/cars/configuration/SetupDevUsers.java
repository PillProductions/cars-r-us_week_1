package dat3.cars.configuration;

import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    MemberRepository memberRepository;
    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, MemberRepository memberRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.memberRepository = memberRepository;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        Member member = new Member("member1", passwordUsedByAll, "mem1@a.dk", "Peter", "Jensen", "Abegaardsvej 42", "Aarhus", 8000, true, 1);
        member.addRole(Role.USER);
        memberRepository.save(member);
        setupUserWithRoleUsers();
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("test1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("test2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("test3", passwordUsedByAll, "user3@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}