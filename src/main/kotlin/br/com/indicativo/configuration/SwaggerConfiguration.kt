package br.com.indicativo.configuration

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    description = "Utilize o token para acessar o IndicAtivo"
)
class SwaggerConfiguration {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("Indicativo API RestFull")
                .version("V0.0.1")
                .termsOfService("https://github.com/DevMendesc/IndicAtivo-G2")
                .license(
                    License()
                        .name("Apache License 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")
                )
                .contact(
                    Contact()
                        .name("IndicAtivo")
                        .email("noreply.indicativos@gmail.com")
                        .url("")
                )
                .summary(
                    "Aplicativo financeiro que mapeia os Ativos de uma empresa," +
                            " e tras os valors corrrespondetes dos indicadores"
                )
        )
    }

}