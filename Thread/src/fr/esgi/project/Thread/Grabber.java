package fr.esgi.project.Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grabber implements Runnable {
	private String url;
	private Pattern htmltag;
	private Pattern link;
	
	public Grabber(String u){
		this.url = u;
	    htmltag = Pattern.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
	    link = Pattern.compile("href=\"[^>]*\">");
	}
	
	@Override
	public void run() {
        try {
    		URL oracle = new URL(this.url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) 
                System.out.println(inputLine);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}

	public List<String> getLinks(String url) {
		List<String> links = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String s;
			StringBuilder builder = new StringBuilder();
			while ((s = bufferedReader.readLine()) != null) {
				builder.append(s);
			}
	
			Matcher tagmatch = htmltag.matcher(builder.toString());
			while (tagmatch.find()) {
				Matcher matcher = link.matcher(tagmatch.group());
				matcher.find();
				String link = matcher.group().replaceFirst("href=\"", "").replaceFirst("\">", "").replaceFirst("\"[\\s]?target=\"[a-zA-Z_0-9]*", "");
				if (valid(link)) {
					links.add(makeAbsolute(url, link));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return links;
	}
	
	private boolean valid(String s) {
		return !s.matches("javascript:.*|mailto:.*");
	}
	
	private String makeAbsolute(String url, String link) {
		if (link.matches("http://.*")) {
			return link;
		}
		if (link.matches("/.*") && url.matches(".*$[^/]")) {
			return url + "/" + link;
		}
		if (link.matches("[^/].*") && url.matches(".*[^/]")) {
			return url + "/" + link;
		}
		if (link.matches("/.*") && url.matches(".*[/]")) {
			return url + link;
		}
		if (link.matches("/.*") && url.matches(".*[^/]")) {
			return url + link;
		}
		throw new RuntimeException("Cannot make the link absolute. Url: " + url + " Link " + link);
	}
}