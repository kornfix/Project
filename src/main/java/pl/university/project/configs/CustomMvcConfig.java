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

    private static final String NOT_FOUND_URL = "/notFound";

    private static final String ERROR_URL = "/error";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/campaigns");
        registry.addViewController("/").setViewName("campaigns");
        registry.addViewController("/campaigns/").setViewName("campaigns");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin/users").setViewName("users");
        registry.addViewController("/clients").setViewName("clients");
        registry.addViewController("/account").setViewName("user");
        registry.addViewController(ERROR_URL).setViewName("error");
        registry.addViewController(NOT_FOUND_URL).setViewName("notFound");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,
                    ERROR_URL));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_URL));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                    NOT_FOUND_URL));
            container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,
                    NOT_FOUND_URL));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,
                    NOT_FOUND_URL));
        };
    }

}
