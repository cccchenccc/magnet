package org.nfa.panel.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.encoding.BaseRequestInterceptor;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@EnableConfigurationProperties(FeignClientEncodingProperties.class)
public class FeignClientConfig {

	@Bean
	public RequestInterceptor customfeignInterceptor(FeignClientEncodingProperties properties) {
		return new FeignInterceptor(properties);
	}

	private class FeignInterceptor extends BaseRequestInterceptor {

		protected FeignInterceptor(FeignClientEncodingProperties properties) {
			super(properties);
		}

		@Override
		public void apply(RequestTemplate template) {
			addHeader(template, "MY_HEADER", "MY_HEADER_VALUE");
		}

	}

}