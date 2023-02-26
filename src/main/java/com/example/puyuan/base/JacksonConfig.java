package com.example.puyuan.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.annotation.JsonFormat.*;

@Configuration
public class JacksonConfig {
    /** 全局DateTime字串格式定義 */
    private final DateTimeFormatter GLOBAL_DTF = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //配置全局實體類中的Boolean Json序列化為0或1
        mapper.configOverride(Boolean.class).setFormat(Value.forShape(Shape.NUMBER));

        var localDateTimeModule = new SimpleModule();
        localDateTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.format(GLOBAL_DTF));
            }
        });

        localDateTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
                return LocalDateTime.parse(p.getText(), GLOBAL_DTF);
            }
        });

        mapper.registerModule(localDateTimeModule);
        // 同意接收帶有註解的JSON
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        return mapper;
    }
}
