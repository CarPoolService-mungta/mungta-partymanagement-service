package partymanagement.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import partymanagement.PartyManagementApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { PartyManagementApplication.class })
public class CucumberSpingConfiguration {}
