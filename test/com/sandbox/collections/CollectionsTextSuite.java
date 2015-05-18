package com.sandbox.collections;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by DKachurovskiy on 9/19/2014.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ParametrizedMultiplicationTest.class, SortingTest.class})
public class CollectionsTextSuite extends TestSuite {
}
