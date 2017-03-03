package com.ys.dao;

import com.ys.bean.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 配置的代码
 */
//@Configuration//自动的开启
@EnableBatchProcessing//开启批处理的支持，不能忘记了
public class CsvBatchConfig {

    /**
     * ItemReader的定义
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ItemReader<Person> reader() throws Exception {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>(); //1使用FlatFileItemReader进行读取文件
        reader.setResource(new ClassPathResource("people.csv")); //2setResource方法设置cvs的路径
        reader.setLineMapper(new DefaultLineMapper<Person>() {{ //3此处对cvs文件的数据和领域模型做对应的映射
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "age", "nation", "address"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    /**
     * ItemProcessor的定义
     *
     * @return
     */
    @Bean
    public ItemProcessor<Person, Person> processor() {
        CsvItemProcessor processor = new CsvItemProcessor(); //1使用我们自己的ItemProcessor实现CsvItemProcessor
        processor.setValidator(csvBeanValidator()); //2指定校验器的实现
        return processor;
    }

    /**
     * ItemWriter的定义
     *
     * @param dataSource
     * @return
     */
    @Bean
    public ItemWriter<Person> writer(DataSource dataSource) {//1Spring容器能够让已有的Bean以参数的形式进行注入，SpringBoot已经定义好了dataSource
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>(); //2使用JDBC批处理数据写到数据库里面去
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        String sql = "insert into person " + "(id,name,age,nation,address) "
                + "values(hibernate_sequence.nextval, :name, :age, :nation,:address)";
        writer.setSql(sql); //3设置sql
        writer.setDataSource(dataSource);
        return writer;
    }


    /**
     * JobRepository定义
     * JobRepository需要dataSource和transactionManager两个参数，SpringBoot已经自动配置这两个类，Spring可通过方法进行注入
     *
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1) //1为Job指定Step
                .end()
                .listener(csvJobListener()) //2帮id那个监听器
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer,
                      ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory
                .get("step1")
                .<Person, Person>chunk(65000) //1每次提交条数
                .reader(reader) //2给step绑定reader
                .processor(processor) //3给step绑定processor
                .writer(writer) //4给step绑定writer
                .build();
    }


    @Bean
    public CsvJobListener csvJobListener() {
        return new CsvJobListener();
    }

    @Bean
    public Validator<Person> csvBeanValidator() {
        return new CsvBeanValidator<Person>();
    }


}
