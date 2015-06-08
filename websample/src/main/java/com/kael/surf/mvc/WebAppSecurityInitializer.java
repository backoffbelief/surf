package com.kael.surf.mvc;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class WebAppSecurityInitializer extends
		AbstractSecurityWebApplicationInitializer {

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}

}
