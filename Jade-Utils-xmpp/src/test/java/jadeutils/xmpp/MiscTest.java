package jadeutils.xmpp;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MiscTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws XMPPException, InterruptedException {
		Connection connection = new XMPPConnection("jabber.org");
		connection.connect();
		connection.login("jade-shan", "yunyun811203");
		Chat chat = connection.getChatManager().createChat(
				"evokeralucard@gmail.com", new MessageListener() {
					public void processMessage(Chat chat, Message message) {
						System.out.println("Received message: " + message);
					}
				});
		chat.sendMessage("Howdy!");
		Thread.sleep(5 * 1000);
	}
}
