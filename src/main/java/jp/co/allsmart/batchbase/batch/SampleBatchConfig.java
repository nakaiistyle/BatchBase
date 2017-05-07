package jp.co.allsmart.batchbase.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SampleBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	SampleTasklet sampleTasklet;

	/**
	 * Taskletバッチ用のStep
	 *
	 * @return
	 */
	@Bean
	public Step sampleStep1() {

		return stepBuilderFactory.get("sampleStep1")
				.tasklet(sampleTasklet)
				.build();
	}

	/**
	 * Taskletのバッチサンプル
	 *
	 * @param sampleStep1
	 * @return
	 */
	@Bean
	public Job sampleJob(Step sampleStep1) {

		return jobBuilderFactory
				.get("sampleJob")
				.incrementer(new RunIdIncrementer())
				.start(sampleStep1)
				.build();
	}


}
