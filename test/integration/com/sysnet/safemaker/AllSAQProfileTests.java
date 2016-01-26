package com.sysnet.safemaker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SAQAEPProfile.class, SAQAProfile.class, SAQBIPProfile.class,
		SAQBProfile.class, SAQCProfile.class, SAQCVTProfile.class,
		SAQDProfile.class, SAQP2PEProfile.class })
public class AllSAQProfileTests {

}
