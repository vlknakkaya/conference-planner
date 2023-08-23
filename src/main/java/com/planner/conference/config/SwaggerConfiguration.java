package com.planner.conference.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

        @Bean
        public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.planner.conference"))
                        .paths(PathSelectors.any())
                        .build().apiInfo(apiInfoMetaData());
        }

        private ApiInfo apiInfoMetaData() {
                return new ApiInfoBuilder().title("ConferencePlanner API Documentation")
                        .description("Makes conference plan for given presentations")
                        .contact(new Contact("Volkan Akkaya", "https://github.com/vlknakkaya/conference-planner", "vlkn.akkaya@gmail.com"))
                        .license("Apache 2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                        .version("1.0.0")
                        .build();
        }

}
