package com.sbs.vc.config.util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

@PropertySources({ @PropertySource("classpath:application_env.properties") })
public class GetLocation {

	// Country Data.
	public static final String DATABASE_COUNTRY_PATH = "/home/ec2-user/aurora/masterfile/GeoLite2-Country.mmdb";

	// City Data.
	public static final String DATABASE_CITY_PATH = "/home/ec2-user/aurora/masterfile/GeoLite2-City.mmdb";
	public static File dbFile;
	public static DatabaseReader reader;
	static {
		File dbFile = new File(DATABASE_CITY_PATH);
		try {
			reader = new DatabaseReader.Builder(dbFile).build();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static String getCountry(String reqipAddress) throws IOException, GeoIp2Exception {
		/*if(reqipAddress.contains("0:0:0:0:0:0:0:1")) { return "Israel";} 
		InetAddress ipAddress = InetAddress.getByName(reqipAddress);
		CityResponse response = reader.city(ipAddress);
		Country country = response.getCountry();
		return country.getName();*/
		return "India";
	}

	public static void main(String[] args) throws IOException, GeoIp2Exception  {
        System.out.println(getCountry("54.169.212.182"));
		
		// A IP Address
		InetAddress ipAddress = InetAddress.getByName("5.22.135.255");

		// Get City info
		CityResponse response = reader.city(ipAddress);

		// Country Info
		Country country = response.getCountry();
		System.out.println("Country IsoCode: " + country.getIsoCode()); // 'US'
		System.out.println("Country Name: " + country.getName()); // 'United States'
		System.out.println(country.getNames().get("zh-CN")); // '美国'

		Subdivision subdivision = response.getMostSpecificSubdivision();
		System.out.println("Subdivision Name: " + subdivision.getName()); // 'Minnesota'
		System.out.println("Subdivision IsoCode: " + subdivision.getIsoCode()); // 'MN'

		// City Info.
		City city = response.getCity();
		System.out.println("City Name: " + city.getName()); // 'Minneapolis'

		// Postal info
		Postal postal = response.getPostal();
		System.out.println(postal.getCode()); // '55455'

		// Geo Location info.
		Location location = response.getLocation();

		// Latitude
		System.out.println("Latitude: " + location.getLatitude()); // 44.9733

		// Longitude
		System.out.println("Longitude: " + location.getLongitude()); // -93.2323

	}
}
