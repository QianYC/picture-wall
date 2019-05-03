package magus.pi.picturewall;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${store}")
    String store;

    /**
     * 一般来说禁止访问除了static文件夹外的其他本地资源，为了可以访问本地资源，需要配置tomcat的虚拟文件夹
     * 由于springboot使用了内嵌容器，故写个配置修改内嵌容器即可
     * @param registry
     */

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:"+store);
    }
}
