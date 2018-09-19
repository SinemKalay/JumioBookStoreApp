package com.sinem.jumio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JumioApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumioApplication.class, args);
	}

	//TODO:order gönderilince ok se approved yap order status book stock sayısını düşür kitabın ownerına customer ı ekle
	//TODO: eger stokta yoksa rejected döndür

	//TODO: DTO - DO dönüşümlerini servicelere ekle
	//TODO: repositorylerdeki fonksiyonları yaz
	//TODO: search için Specification ı kullanarak book search yaz
	//TODO: Authentication yap H2 dbdeki customerları baz alan
	//TODO: Loglamaları yap
	//TODO: Token based authenticaion??
	//TODO:Event logging to have an async transaction logging araştır???
	//TODO: Resilience/ Failure cascading mitigations araştır??
	//TODO: Request Tracking/Tracing??
}
