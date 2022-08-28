package dat3;

import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.security.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void contextLoads() {
        prepareTestData();
        testCreate();
        testRead();
        testUpdate();
        testDelete();
    }

    public void prepareTestData() {
        Member member1 = new Member("abc", "abc123", "abc@email.com", "testFN1", "testLN1", "testSTR1", "testCTY1", 1000, true, 1);
        Member member2 = new Member("def", "abc456", "def@email.com","testFN2", "testLN2", "testSTR2", "testCTY2", 2000, false, 2);
        member1.addRole(Role.USER);
        member2.addRole(Role.USER);
        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    public void testCreate() {
        Member createdMember = new Member("newUsername", "newPassword", "newuser@email.com", "testName", "testLastName", "testStreet", "testCity", 3000, true, 1);
        memberRepository.save(createdMember);
        Member foundMember = memberRepository.findById(createdMember.getUsername()).get();
        assertNotNull(foundMember);
    }

    public void testRead() {
        Member foundMember = memberRepository.findById("abc").get();
        assertNotNull(foundMember);
    }

    public void testUpdate() {
        Member foundMember = memberRepository.findById("abc").get();

        foundMember.setFirstName("changedFirstName");
        memberRepository.save(foundMember);

        foundMember = memberRepository.findById("abc").get();
        assertEquals("changedFirstName", foundMember.getFirstName());
    }

    public void testDelete() {
        memberRepository.deleteById("def");
        assertFalse(memberRepository.findById("def").isPresent());
    }

}
