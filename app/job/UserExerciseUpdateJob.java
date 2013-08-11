package job;

import models.User;
import play.jobs.Job;

public class UserExerciseUpdateJob extends Job {

	private User user;
	public UserExerciseUpdateJob(User u){
		user = u;
	}
	public void doJob() throws Exception {
		if(user == null){
			return;
		}
		
		
		
		
    }
}
