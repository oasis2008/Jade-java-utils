package testsuite;

import jadeutils.code.BinaryCoderTest;
import jadeutils.code.MD5CoderTest;
import jadeutils.datastructures.TreeNodeTest;
import jadeutils.formater.FormaterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BinaryCoderTest.class, //
		MD5CoderTest.class, FormaterTest.class, TreeNodeTest.class })
public class AllSuite {

}