package com.moin.support;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

/**
 * Spring Boot 초기화시 {@code application-local-overrides.yml} 파일을 추가로 읽어들이도록 설정한다.
 */
@SuppressWarnings("JavadocReference")
public class LocalConfigOverrideInitializer implements BootstrapRegistryInitializer {

	/**
	 * @see org.springframework.boot.context.config.ConfigDataEnvironment#ADDITIONAL_LOCATION_PROPERTY
	 */
	@SuppressWarnings({
			// Standard outputs should not be used directly to log anything
			// 의도적으로 stderr로 출력함
			"java:S106"
	})
	@Override
	public void initialize(BootstrapRegistry registry) {
		System.err.println("Using local-overrides");
		System.setProperty("spring.config.additional-location", "optional:classpath:/config/application-local-overrides.yml");
	}
}
