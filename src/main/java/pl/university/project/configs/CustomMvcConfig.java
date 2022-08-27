package pl.university.project.configs;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/campaigns");
        registry.addViewController("/").setViewName("campaigns");
        registry.addViewController("/campaigns/").setViewName("campaigns");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/clients").setViewName("clients");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/notFound").setViewName("notFound");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> {
//            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,
//                    "/error"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                    "/error"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                    "/notFound"));
            container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,
                    "/notFound"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,
                    "/notFound"));
        };
    }

}
