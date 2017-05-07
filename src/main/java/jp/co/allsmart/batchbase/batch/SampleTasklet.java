package jp.co.allsmart.batchbase.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.allsmart.batchbase.service.SampleService;

@Component
public class SampleTasklet implements Tasklet {

	@Autowired
	SampleService sampleService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		System.out.println("■ SampleTasklet execute Start");

		try {
			sampleService.execute();
		} catch (Exception e) {

			// TODO:例外処理
			e.printStackTrace();
		}

		System.out.println("■ SampleTasklet execute End");

		return RepeatStatus.FINISHED;
	}

}
