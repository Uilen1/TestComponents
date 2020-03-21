package model.suite;

import static model.core.DriverFactory.killDriver;

import org.junit.AfterClass;
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

	@AfterClass
	public static void closeAll() {
		killDriver();
	}
	
	
}
