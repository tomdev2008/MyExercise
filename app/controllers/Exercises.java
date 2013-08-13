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
			
			/***
			 * 4. 用户历史题目信息更新：
				属性更新
					完成次数(complete_count)
					展现次数
					正确次数(correct_count)
					错误次数(mistake_count)
					上一次完成时间(update_at)
					下一次展现时间
					题目入库时间(create_at)
					剩余重复次数(URT=User should Reapet Time)
				展现权重更新
					QS
					if (is 强化考点) +3
					if (abs(U.USS-100*(1-FCR))<10) +2
					if (abs(U.USS-100*(1-FCR))>20) -2
					if (U.KTS == Null) +3
					if (U.KTS > (U.USS+10)) -2
					if (AR==历史题库推荐) +4
			 */
			
			if(userExercise.subject.answer.size() == cnt){
				userExercise.correctCount = userExercise.correctCount+1;
				correctCnt++;
				userExercise.currentScore = 1;
				userExercise.completeCount= userExercise.completeCount+1;
			}else{
				if(userExercise.userAnswer.size() ==0){
					userExercise.currentScore = -1;
				}else{
					userExercise.mistakeCount = userExercise.mistakeCount+1;
					mistakeCnt++;
					userExercise.currentScore = 0;
					userExercise.completeCount= userExercise.completeCount+1;
				}
			}
			userExercise.displayCount = userExercise.displayCount +1;
			//TODO:下次可以展现的时间
			userExercise.nextDate = new Date();
			userExercise.updateAt = new Date();
			userExercise.save();
			exercises.add(userExercise);
		}	
		
		render(exercises,correctCnt,mistakeCnt);
	}
}
