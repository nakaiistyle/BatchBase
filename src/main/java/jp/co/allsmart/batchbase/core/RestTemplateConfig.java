package jp.co.allsmart.batchbase.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate customRestTemplate() {

        // TODO:タイマ値は仮で10秒。この辺はコンフィグで外だしする
        int connectTimeout = 10000;
        int readTimeout = 10000;

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        // JSON形式のコンバータ
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(jacksonObjectMapper());
        messageConverters.add(jsonMessageConverter);

        // RestTemplateを生成して返却。
        return new RestTemplateBuilder()
                .messageConverters(messageConverters)
                .setConnectTimeout(connectTimeout)
                .setReadTimeout(readTimeout)
//                .requestFactory(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()))
                .build();
    }

    /**
     * RESTテンプレート用Jackson ObjectMapper定義。
     *
     * @return
     */
    @Bean
    public ObjectMapper jacksonObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();

        // JSONはsnake_caseを基本形式に設定
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        // 不明なパラメータはデシリアライズ対象外として読み飛ばし
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // NULLのプロパティはシリアライズ対象外
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // プロパティ名を利用したシリアライズ指定
        mapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);

        return mapper;
    }
}
