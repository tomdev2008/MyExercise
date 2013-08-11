package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Option;
import models.StudentPofile;
import models.Subject;
import models.Tag;
import models.User;
import models.UserExercise;
import play.mvc.Controller;
import service.TagService;

public class Exercises extends Controller {

	public static void index(){
		
		User user = Users.getLoginUser();
		StudentPofile profile = StudentPofile.filter("user", user).first();
		if(profile == null  
				|| profile.grades == null  
				|| profile.courses ==null 
				|| profile.schedule == null){
			//创建资料
			render();
		}
		render(profile);
	}
	
	
	public static void start(String courseName){
		Tag course = TagService.getTag(courseName);
		List<UserExercise> exercise = UserExercise.findAll();
		render(exercise);
	}
	
	
	public static void end(String answer){
		String[] an = answer.split(",");
		List<UserExercise> exercises = new ArrayList();
		int correctCnt=0;
		int mistakeCnt=0;
		for(String a :an){
			String[] item = a.split("_");
			UserExercise  userExercise = UserExercise.findById(item[0]);
			int cnt =0;
			userExercise.userAnswer = new ArrayList<Option>();
			for(int i=1;i< item.length;i++){
				Option  option = Option.findById(item[1]);
				userExercise.userAnswer.add(option);
				
				for (Option o :userExercise.subject.answer){
					if(o.equals(option)){
						cnt++;
					}
				}
			}
			if(userExercise.subject.answer.size() == cnt){
				userExercise.correctCount = userExercise.correctCount+1;
				correctCnt++;
			}else{
				userExercise.mistakeCount = userExercise.mistakeCount+1;
				mistakeCnt++;
			}
			userExercise.completeCount= userExercise.completeCount+1;
			userExercise.updateAt = new Date();
			userExercise.save();
			exercises.add(userExercise);
		}	
		
		render(exercises,correctCnt,mistakeCnt);
	}
}
