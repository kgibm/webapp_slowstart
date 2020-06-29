package com.example;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ApplicationInitializer {

	@Inject
	@ConfigProperty(name = "com.example.startDelayMilliseconds", defaultValue = "100")
	private long startDelay;

	public void postConstruct(@Observes @Initialized(ApplicationScoped.class) Object o) {
		System.out.println(toString() + " started " + startDelay);
		
		try {
			Thread.sleep(startDelay);
		} catch (InterruptedException e) {
			System.err.println("Interrupted while sleeping during startup: " + e);
			e.printStackTrace();
		}
		
		System.out.println(toString() + " finished");
	}
}
