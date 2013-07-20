package jadeutils.formater;

import org.junit.Test;

public class FormaterTest {

	@Test
	public void testFormatJson() {
		String jsonStr = "{\"sejieCategory\":[{\"id\":19279,\"subCategory\":[{\"id\":19280,\"subCategory\":[],\"name\":19280},{\"id\":19285,\"subCategory\":[],\"name\":19285},{\"id\":19286,\"subCategory\":[],\"name\":19286},{\"id\":19287,\"subCategory\":[],\"name\":19287},{\"id\":19288,\"subCategory\":[],\"name\":19288},{\"id\":19290,\"subCategory\":[],\"name\":19290},{\"id\":19289,\"subCategory\":[],\"name\":19289}],\"name\":19279},{\"id\":19436,\"subCategory\":[{\"id\":19437,\"subCategory\":[],\"name\":19437},{\"id\":19438,\"subCategory\":[],\"name\":19438},{\"id\":19439,\"subCategory\":[],\"name\":19439},{\"id\":19440,\"subCategory\":[],\"name\":19440},{\"id\":19441,\"subCategory\":[],\"name\":19441}],\"name\":19436},{\"id\":19432,\"subCategory\":[{\"id\":19462,\"subCategory\":[],\"name\":19462},{\"id\":19461,\"subCategory\":[],\"name\":19461},{\"id\":19463,\"subCategory\":[],\"name\":19463}],\"name\":19432},{\"id\":19423,\"subCategory\":[{\"id\":19456,\"subCategory\":[],\"name\":19456},{\"id\":19457,\"subCategory\":[],\"name\":19457}],\"name\":19423},{\"id\":19442,\"subCategory\":[{\"id\":19443,\"subCategory\":[],\"name\":19443},{\"id\":19444,\"subCategory\":[],\"name\":19444},{\"id\":19445,\"subCategory\":[],\"name\":19445}],\"name\":19442}],\"guangCategory\":[{\"id\":19467,\"subCategory\":[{\"id\":19453,\"subCategory\":[],\"name\":19453},{\"id\":19452,\"subCategory\":[],\"name\":19452},{\"id\":19454,\"subCategory\":[],\"name\":19454}],\"name\":19467},{\"id\":19428,\"subCategory\":[{\"id\":19446,\"subCategory\":[{\"id\":19471,\"subCategory\":[],\"name\":19471},{\"id\":19472,\"subCategory\":[],\"name\":19472},{\"id\":19473,\"subCategory\":[],\"name\":19473},{\"id\":19474,\"subCategory\":[],\"name\":19474},{\"id\":19477,\"subCategory\":[],\"name\":19477},{\"id\":19478,\"subCategory\":[],\"name\":19478},{\"id\":19479,\"subCategory\":[],\"name\":19479}],\"name\":19446},{\"id\":19459,\"subCategory\":[],\"name\":19459},{\"id\":19460,\"subCategory\":[],\"name\":19460}],\"name\":19428}],\"stage\":\"success\"}";
//		System.out.println(
		JsonFormater.formatJson(jsonStr, "  ")
//		)
		;
	}

}
