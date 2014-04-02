package http.conf;

import java.util.List;

public class Config {
	public int port;
	public List<Host> hosts;
    public int  proxyport;
	
	@Override
	public String toString() {
		return "Config [port=" + port + ", hosts=" + hosts + ",Proxy Port="+proxyport+"]";
	}
}