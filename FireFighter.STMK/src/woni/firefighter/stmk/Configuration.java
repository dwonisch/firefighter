package woni.FireFighter.stmk;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import woni.FireFighter.Common.IConfiguration;
import woni.FireFighter.Common.District;
import woni.FireFighter.Common.Mission;
import woni.FireFighter.Common.RetreiveMissionsTask;

public class Configuration implements IConfiguration {

	public LinkedHashMap<String, String> getDistricts() {
		LinkedHashMap<String, String> districts = new LinkedHashMap<String,String>();
		
		districts.put("BM", "Bereich Bruck an der Mur");
		districts.put("DL", "Bereich Deutschlandsberg");
		districts.put("FB", "Bereich Feldbach");
		districts.put("FF", "Bereich F�rstenfeld");
		districts.put("GU", "Bereich Graz Umgebung");
		districts.put("HB", "Bereich Hartberg");
		districts.put("JU", "Bereich Judenburg");
		districts.put("KF", "Bereich Knittelfeld");
		districts.put("LB", "Bereich Leibnitz");
		districts.put("LE", "Bereich Leoben");
		districts.put("LI", "Bereich Liezen");
		districts.put("MU", "Bereich Murau");
		districts.put("MZ", "Bereich M�rzzuschlag");
		districts.put("RA", "Bereich Radkersburg");
		districts.put("VO", "Bereich Voitsberg");
		districts.put("WZ", "Bereich Weiz");
		
		return districts;
	}

	public String getUrl(District district) {
		return String.format("http://178.188.171.236/rpweb/onlinestatus.aspx?form=EVENT&bez=%s", district.getShortText());
	}

	public void parseMissions(RetreiveMissionsTask task, BufferedReader reader) {
		
		char[] buffer = new char[128];
		StringBuilder builder = new StringBuilder(1024);
		int length;
		
		Pattern pattern = Pattern.compile("<td>(([&\\.0-9A-Za-z :/�������-]*)|(<img src='[A-Za-z\\.0-9/]*'>))</td>");
		
		int parsePosition = 0;
		String[] resultBuffer = new String[8];
		int resultCounter = 0;
		
		try {
			while((length = reader.read(buffer)) > 0){
				builder.append(buffer);
				
				Matcher matcher = pattern.matcher(builder);
				while(matcher.find(parsePosition)){
					resultBuffer[resultCounter] = matcher.group(1);
					resultCounter++;
					if(resultCounter == 8){
						resultCounter = 0;
						task.setItem(new Mission(resultBuffer));
					}
					parsePosition = matcher.end();
				}
				
				builder.delete(0, parsePosition);
				parsePosition = 0;
				
				if(task.isCancelled()){
					return;
				}
			}
		} catch (IOException e) {
		}
	}

}
