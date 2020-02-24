package hr.martinovic.FakeStagram.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import hr.martinovic.FakeStagram.db.ImageRepository;
import hr.martinovic.FakeStagram.model.Image;


public class ImagePrintJob extends QuartzJobBean {

	private Logger log = LoggerFactory.getLogger(ImagePrintJob.class);

	private final ImageRepository imageRepository;

	public ImagePrintJob(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		SingletonText singleton = SingletonText.getInstance();
		Iterable<Image> images = imageRepository.findAll();

		if (images.iterator().hasNext()) {
			log.info(singleton.outputMessage());
			images.forEach(im -> log.info(im.getName() + " on path: " + im.getPath() ));
		}
		else {
			log.info(singleton.outputMessageEmpty());
		}

	}


}