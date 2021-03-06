package http.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequestBuilder {

	HttpStaticServer httpServer;
	
	public HttpRequestBuilder(HttpStaticServer _httpServer) {
		this.httpServer = _httpServer;
	}
	
	public RequestHttpHandler build(Socket socket) throws Exception {
		RequestHttpHandler request = new RequestHttpHandler();
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		buildMethodAndGetParameters(request, reader);
		buildHeaders(request, reader);
		buildCookies(request);
		String host = request.getHeader("host");
		if (null == host) {
			throw new Exception("Not Host Supplied (HTTP/1.1)");
		}
		request.host = httpServer.hosts.get(host.substring(0, host.indexOf(":")));
		return request;
	}
	
	private void buildMethodAndGetParameters(RequestHttpHandler request, BufferedReader reader) throws Exception {
		String line = reader.readLine();
		request.rawRequest += line+"\r\n";
		if(null != line) {
			String[] parts = line.split(" ");
			if (3 != parts.length)
				throw new Exception("Invalid Method");
			request.method = parts[0];
			request.httpVersion = parts[2];
	
			String[] url = parts[1].split("\\?");
			request.url = url[0];
			if (1 < url.length){
				String[] params = url[1].split("&");
				for (String keyValue : params) {
					String[] keyValueParts = keyValue.split("=");
					// TODO DecodeUri.
					request.httpParameters.put(keyValueParts[0], 1<keyValueParts.length ?keyValueParts[1]: null);
				}
			}
		}
	}
	
	private void buildHeaders(RequestHttpHandler request, BufferedReader reader) throws IOException {
		String line;
		while ( null != (line = reader.readLine()) && ! line.isEmpty() ) {
			request.rawRequest += line+"\r\n";
			int splitIndex = line.indexOf(':');
			if (-1 != splitIndex) {
				// TODO DecodeUri.
				request.httpHeaders.put(line.substring(0, splitIndex).trim().toLowerCase(), line.substring(splitIndex+1).trim());
			}
		}
	}
	
	private void buildCookies(RequestHttpHandler request) {
		String cookieString = request.getHeader("Cookie");
		if (null != cookieString) {
			String[] cookiePairParts = cookieString.split(";");
			for (String cookiePair : cookiePairParts){
				String[] keyValue = cookiePair.split("=");
				request.httpCookies.put(keyValue[0].trim(), keyValue[1].trim());
			}
		}
	}
}
