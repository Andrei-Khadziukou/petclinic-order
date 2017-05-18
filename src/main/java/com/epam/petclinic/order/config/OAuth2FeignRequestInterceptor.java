package com.epam.petclinic.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.util.Assert;

public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    /**
     * The logger instance used by this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);

    /**
     * The {@code Bearer} token type.
     */
    private static final String TOKEN_TYPE = "Bearer";

    /**
     * Current OAuth2 authentication context.
     */
    private final OAuth2ClientContext oauth2ClientContext;

    /**
     * Creates new instance of {@link OAuth2FeignRequestInterceptor} with client context.
     *
     * @param oauth2ClientContext the OAuth2 client context
     */
    public OAuth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
        Assert.notNull(oauth2ClientContext, "Context can not be null");
        this.oauth2ClientContext = oauth2ClientContext;
    }

    @Override
    public void apply(RequestTemplate template) {
        LOGGER.info("Constructing Header {} for Token {}", HttpHeaders.AUTHORIZATION, TOKEN_TYPE);
        template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", TOKEN_TYPE,
                oauth2ClientContext.getAccessTokenRequest().getExistingToken()));
    }
}
