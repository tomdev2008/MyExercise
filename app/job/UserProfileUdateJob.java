package job;

import models.StudentProfile;
import models.Tag;
import models.User;
import models.UserExercise;
import play.jobs.Job;
import play.modules.morphia.Model.MorphiaQuery;

public class UserProfileUdateJob extends Job {

	
	private User user;
	private Tag course;
	public UserProfileUdateJob(User u,Tag course){
		user = u;
		this.course = course;
	}
	public void doJob() throws Exception {
		if(user == null || course == null){
			return;
		}
		/***
		 2. 用户Profile信息更新：
			 学科预计分数更新
			 知识点图谱更新
			 知识点属性(新增，预测分数，强化考点？，提升考点？)
		 **/
		MorphiaQuery query = UserExercise.find("user",user).filter("course", course);
		long CR = query.sum("correctCount");
		long All = query.sum("displayCount");
		
		StudentProfile sp = StudentProfile.filter("user", user).filter("course", course).first();
		sp.courses.put(course, CR/All);
		sp.save();
	}
}
