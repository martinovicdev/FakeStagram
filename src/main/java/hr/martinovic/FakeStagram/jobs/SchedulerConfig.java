package hr.martinovic.FakeStagram.jobs;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    
    @Bean 
    public JobDetail imagePrintJobDetail() {
        //Builder pattern
        return JobBuilder.newJob(ImagePrintJob.class).withIdentity("imagePrintJob")
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger exercisePrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60).repeatForever();

        return TriggerBuilder.newTrigger().forJob(imagePrintJobDetail())
                .withIdentity("imagePrintTrigger").withSchedule(scheduleBuilder).build();
    }

}