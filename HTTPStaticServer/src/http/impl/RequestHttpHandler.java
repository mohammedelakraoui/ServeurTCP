package http.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import http.Interfaces.ICookie;
import http.Interfaces.IRequestHttpHandler;
import http.conf.Host;

public class RequestHttpHandler implements IRequestHttpHandler {
	public HashMap<String, String> httpHeaders = new HashMap<>();

	public HashMap<String, String> httpParameters = new HashMap<>();
	public HashMap<String, String> httpCookies = new HashMap<>();

	public Host host;
	
	public String port;
	public String method;
	public String url;
	public String httpVersion;
	private String remoteAddress;
	
	@Override
	public String[] getParameterNames() {
		return this.httpParameters.keySet().toArray(new String[this.httpParameters.size()]);
	}

	@Override
	public String getParameter(String key) {
		return this.httpParameters.get(key);
	}

	Cookie[] _cookies = null;
	@Override
	public ICookie[] getCookies() {
		if(null == _cookies) {
			_cookies = new Cookie[this.httpCookies.size()];
			int i = 0;
			for(String key : this.httpCookies.keySet()) {
				_cookies[i++] = new Cookie(key, this.httpCookies.get(key));
			}
		}
		return _cookies;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getHttpVersion() {
		return this.httpVersion;
	}

	@Override
	public String[] getHeaderNames() {
		return this.httpHeaders.keySet().toArray(new String[this.httpHeaders.size()]);
	}

	@Override
	public String getHeader(String key) {
		return this.httpHeaders.get(key);
	}

	@Override
	public String getRealPath(String path) {
		return host.document_root + path;
	}

	@Override
	public String getHostname() {
		return this.host.name;
	}

	@Override
	public String getRemoteAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public String getPort() {
		return this.port;
	}
}