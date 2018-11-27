package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface Client_Handler {
	void hc(InputStream input, OutputStream output);
}
