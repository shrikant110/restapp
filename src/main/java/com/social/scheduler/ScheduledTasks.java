package com.social.scheduler;

import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
		private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	    
		private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		/*@Scheduled(fixedRate = 2000)
	    public void scheduleTaskWithFixedRate() {
			logger.info("Current Thread : {}", Thread.currentThread().getName());
			 logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
		}
		
		@Scheduled(fixedDelay = 2000)
	    public void scheduleTaskWithFixedDelay() {
			logger.info("Current Thread : {}", Thread.currentThread().getName());
			logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		    try {
		        TimeUnit.SECONDS.sleep(5);
		    } catch (InterruptedException ex) {
		        logger.error("Ran into an error {}", ex);
		        throw new IllegalStateException(ex);
		    }
		}

		@Scheduled(fixedRate = 2000, initialDelay = 5000)
	    public void scheduleTaskWithInitialDelay() {
			logger.info("Current Thread : {}", Thread.currentThread().getName());
			logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		}*/
		
		/*@Scheduled(cron = "10 * * * * ?")
	    public void scheduleTaskWithCronExpression() {
			logger.info("Current Thread : {}", Thread.currentThread().getName());
		    logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		}*/
}
