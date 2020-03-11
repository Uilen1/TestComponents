package model.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Tests.TestAlert;
import Tests.TesteCadastro;
import model.scenarios.Scenarios;

@RunWith(Suite.class)

@SuiteClasses({
	TestAlert.class,
	TesteCadastro.class,
	Scenarios.class
})

public class Suites {

}
