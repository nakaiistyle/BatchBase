package jp.co.allsmart.batchbase.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jp.co.allsmart.batchbase.dto.HttpResponse;
import jp.co.allsmart.batchbase.entity.SampleEntity;
import jp.co.allsmart.batchbase.repository.SampleMapper;

@Component
public class SampleService {

    private static final Logger logger = LoggerFactory.getLogger(SampleService.class);

	@Autowired
	SampleMapper mapper;

	@Autowired
	RestTemplate restTemplate;

	public void execute() throws Exception{

	    logger.info("■ SampleService execute Start");

		// DB読み込み
		List<SampleEntity> entities = mapper.selectAll();

		// HTTP送信
		sendMessage(entities);

		// DB更新

		logger.info("■ SampleService execute End");

	}

	private void sendMessage(List<SampleEntity> entities) {

		String url = "http://private-0c0ec-test13416.apiary-mock.com/test";

		String request;

		// TODO:規定フォーマットでリクエストボディ作成
		request = "test";

		try {
			ResponseEntity<HttpResponse> rsp = restTemplate.postForEntity(url, request, HttpResponse.class);

			logger.info("result=" + rsp.getBody().getResult());
            logger.info("message=" + rsp.getBody().getMessage());

		} catch (RestClientException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
