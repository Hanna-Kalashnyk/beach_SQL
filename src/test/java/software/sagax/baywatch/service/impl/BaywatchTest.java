package software.sagax.baywatch.service.impl;

import javassist.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.sagax.baywatch.model.Beach;
import software.sagax.baywatch.model.Lifeguard;
import software.sagax.baywatch.service.BeachService;
import software.sagax.baywatch.service.LifeguardService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaywatchTest {

    @Autowired
    private BeachService beachService;
    @Autowired
    private LifeguardService lifeguardService;

    @BeforeAll
    void initData() {

        Beach malibuBeach = beachService.addBeach("Malibu");
        Beach elSegundoBeach = beachService.addBeach("El Segundo");
        Beach hermosaBeach = beachService.addBeach("Hermosa");
        Beach longBeachBeach = beachService.addBeach("Long Beach");
        Beach marinaDelReyBeach = beachService.addBeach("Marina Del Rey");

        Lifeguard mitchBuchannon = lifeguardService.addLifeguard("Mitch Buchannon", 5, malibuBeach, null);
        lifeguardService.addLifeguard("John D. Cort", 3, malibuBeach, mitchBuchannon);
        lifeguardService.addLifeguard("Eddie Kramer", 2, malibuBeach, mitchBuchannon);
        lifeguardService.addLifeguard("Summer Quinn", 4, malibuBeach, mitchBuchannon);


        Lifeguard cjParker = lifeguardService.addLifeguard("C. J. Parker", 4, elSegundoBeach, null);
        lifeguardService.addLifeguard("Hobie Buchannon", 4, elSegundoBeach, cjParker);
        lifeguardService.addLifeguard("Trevor Cole", 4, elSegundoBeach, cjParker);
        lifeguardService.addLifeguard("Gina Pomeroy", 2, elSegundoBeach, cjParker);
        lifeguardService.addLifeguard("Jimmy Slade", 5, elSegundoBeach, cjParker);
        lifeguardService.addLifeguard("Ben Edwards", 3, elSegundoBeach, cjParker);


        Lifeguard craigPomeroy = lifeguardService.addLifeguard("Craig Pomeroy", 4, hermosaBeach, null);
        lifeguardService.addLifeguard("Jill Riley", 3, hermosaBeach, craigPomeroy);
        lifeguardService.addLifeguard("Shauni McClain", 2, hermosaBeach, craigPomeroy);
        lifeguardService.addLifeguard("Hobie Buchannon", 5, hermosaBeach, craigPomeroy);
        lifeguardService.addLifeguard("Garner Ellerbee", 4, hermosaBeach, craigPomeroy);
        lifeguardService.addLifeguard("Don Thorpe", 3, hermosaBeach, craigPomeroy);
        lifeguardService.addLifeguard("Harvey Miller", 1, hermosaBeach, craigPomeroy);

        lifeguardService.addLifeguard("Matt Brody", 4, longBeachBeach, null);

    }

    @Test
    @Order(1)
    void initTest() {
        Assertions.assertEquals(5, beachService.findAll().size());
        Assertions.assertEquals(18, lifeguardService.findAll().size());
    }

    @Test
    @Order(2)
    void findAllBeachesWithLessThanFourLifeGuards() {
        Assertions.assertEquals(2, beachService.findAllBeachesWithLifeguardsLessThan(4).size());
    }

    @Test
    @Order(3)
    void findAllByMaxTotalSpeed() {

        List<String> allByMaxTotalSpeed = beachService.findAllByMaxTotalSpeed();

        Assertions.assertEquals(2, allByMaxTotalSpeed.size());
        Assertions.assertTrue(allByMaxTotalSpeed.contains("Hermosa"));
        Assertions.assertTrue(allByMaxTotalSpeed.contains("El Segundo"));
    }


    @Test
    @Order(4)
    void findWhenSpeedGreaterThanBoss() {

        List<String> whenSpeedGreaterThanBoss = lifeguardService.findWhenSpeedGreaterThanBoss();

        Assertions.assertEquals(2, whenSpeedGreaterThanBoss.size());
        Assertions.assertTrue(whenSpeedGreaterThanBoss.contains("Hobie Buchannon"));
        Assertions.assertTrue(whenSpeedGreaterThanBoss.contains("Jimmy Slade"));

    }

    @Test
    @Order(5)
    void findFastestLifeguards() {

        List<String> fastestLifeguards = lifeguardService.findFastestLifeguards();

        Assertions.assertEquals(3, fastestLifeguards.size());
        Assertions.assertTrue(fastestLifeguards.contains("Hobie Buchannon"));
        Assertions.assertTrue(fastestLifeguards.contains("Jimmy Slade"));
        Assertions.assertTrue(fastestLifeguards.contains("Mitch Buchannon"));

    }

    @Test
    @Order(6)
    void findBosses() {

        List<String> bosses = lifeguardService.findBosses();

        Assertions.assertEquals(3, bosses.size());
        Assertions.assertTrue(bosses.contains("Craig Pomeroy"));
        Assertions.assertTrue(bosses.contains("C. J. Parker"));
        Assertions.assertTrue(bosses.contains("Mitch Buchannon"));

    }

    @Test
    @Order(7)
    void groupAndCountBySpeed() {

        Map<Integer, Long> countBySpeed = lifeguardService.groupAndCountBySpeed();
        Assertions.assertEquals(1, countBySpeed.get(1));
        Assertions.assertEquals(3, countBySpeed.get(2));
        Assertions.assertEquals(4, countBySpeed.get(3));
        Assertions.assertEquals(7, countBySpeed.get(4));
        Assertions.assertEquals(3, countBySpeed.get(5));

    }


    @Test
    @Order(8)
    void fireLifeguard() throws NotFoundException {

        lifeguardService.fireLifeguard("Eddie Kramer");

        Assertions.assertTrue(lifeguardService.findAll()
                .stream().map(Lifeguard::getName)
                .noneMatch(s -> Objects.equals(s,"Eddie Kramer")));


    }

    @Test
    @Order(9)
    void changeSpeed() throws NotFoundException {


        Assertions.assertTrue(lifeguardService.findAll()
                .stream()
                .filter(lifeguard -> Objects.equals(lifeguard.getName(), "Harvey Miller"))
                .anyMatch(lifeguard -> Objects.equals(lifeguard.getSpeed(), 1)));

        lifeguardService.changeSpeed("Harvey Miller", 5);

        Assertions.assertTrue(lifeguardService.findAll()
                .stream()
                .filter(lifeguard -> Objects.equals(lifeguard.getName(), "Harvey Miller"))
                .anyMatch(lifeguard -> Objects.equals(lifeguard.getSpeed(), 5)));

    }


}