package jp.co.allsmart.batchbase.service;

import java.util.List;

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

	@Autowired
	SampleMapper mapper;

	public void execute() throws Exception{

		System.out.println("■ SampleService execute Start");

		// DB読み込み
		List<SampleEntity> entities = mapper.selectAll();

		// HTTP送信
		sendMessage(entities);

		// DB更新

		System.out.println("■ SampleService execute End");

	}

	private void sendMessage(List<SampleEntity> entities) {
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost/test";

		String request;

		// TODO:規定フォーマットでリクエストボディ作成
		request = "test";

		try {
			ResponseEntity<HttpResponse> rsp = restTemplate.postForEntity(url, request, HttpResponse.class);

		} catch (RestClientException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
