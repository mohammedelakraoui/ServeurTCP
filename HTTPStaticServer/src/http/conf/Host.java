package http.conf;

import java.util.List;

public class Host {

	public String name;
	public String document_root;
	public List<Handler> handlers;
	public int proxyport;
	@Override
	public String toString() {
		return "Host [name=" + name + ", document_root=" + document_root
				+ ", handlers=" + handlers + ",Proxy="+proxyport+"]";
	}
}
