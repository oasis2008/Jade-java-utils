package jadeutils.misc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CodeGenTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenExtjs4ModelField() {
		String format = ",{header:messages['omsStkoutHd.%s'],dataIndex:'%s',hidden:true,sortable:false}";
		String[][] fields = { { "storeId" }, { "channelId" },
				{ "omsMemberId" }, { "omsMemberCard" }, { "omsOrderConsumer" },
				{ "sourceOrderNo" }, { "omsOrderNo" }, { "paymentTime" },
				{ "orderTime" }, { "orderInternalStatus" },
				{ "paymentStatus" }, { "orderType" }, { "orderStatus" },
				{ "shippingNo" }, { "shippingTotal" }, { "isNeedInvoice" },
				{ "field4" }, { "field5" }, { "field6" }, { "orderComment" },
				{ "sellerComment" }, { "refundAmount" }, { "payedAmount" },
				{ "lockedBy" }, { "commentStatus" }, { "cancelTime" },
				{ "omsAddressRegion" }, { "paymentMethod" },
				{ "cancelReason" }, { "cancelComment" }, { "omsCurrency" },
				{ "brandStoreId" }, { "assignTo" }

		};
		for (String[] f : fields) {
			System.out.println(String.format(format, f[0], f[0]));
		}
	}

	@Test
	public void testGenI18N() {
		String domain = "omsStkoutHd";
		String format = "%s.%s=%s";
		String[][] fields = { { "storeId" }, { "channelId" },
				{ "omsMemberId" }, { "omsMemberCard" }, { "omsOrderConsumer" },
				{ "sourceOrderNo" }, { "omsOrderNo" }, { "paymentTime" },
				{ "orderTime" }, { "orderInternalStatus" },
				{ "paymentStatus" }, { "orderType" }, { "orderStatus" },
				{ "shippingNo" }, { "shippingTotal" }, { "isNeedInvoice" },
				{ "field4" }, { "field5" }, { "field6" }, { "orderComment" },
				{ "sellerComment" }, { "refundAmount" }, { "payedAmount" },
				{ "lockedBy" }, { "commentStatus" }, { "cancelTime" },
				{ "omsAddressRegion" }, { "paymentMethod" },
				{ "cancelReason" }, { "cancelComment" }, { "omsCurrency" },
				{ "brandStoreId" }, { "assignTo" }

		};
		for (String[] f : fields) {
			System.out.println(String.format(format,domain, f[0], f[0]));
		}
	}

}
