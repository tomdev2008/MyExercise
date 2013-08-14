package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Option;
import models.ScheduleInfo;
import models.StudentPofile;
import models.Subject;
import models.Tag;
import models.User;
import models.UserExercise;
import models.enums.UserExerciseStatus;
import play.mvc.Controller;
import play.mvc.Util;
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
		User user = Users.getLoginUser();
		if(user == null){
			user = User.find("userName", "test1").first();
		}
		StudentPofile profile = StudentPofile.filter("user", user).first();
		
		if(profile == null  
				|| profile.grades == null  
				|| profile.courses ==null 
				|| profile.schedule == null){
			profile = new StudentPofile();
			//profile.grades.add((Tag)Tag.find("name", "高一").first());
			List<Tag> list = new ArrayList<Tag>();
			List<Tag> parentTag = null;
			if(courseName.equals("数学")){
				parentTag = Tag.find("name", "mathTag").asList();
			}else if(courseName.equals("英语")){
				parentTag = Tag.find("name", "eglishTag").asList();
			}
			list = getTag(parentTag,list);
			for(Tag t:list){
				ScheduleInfo sinfo = new ScheduleInfo();
				sinfo.user = user;
				sinfo.course = course;
				//sinfo.grade = grade;
				sinfo.tag = t;
				sinfo.createAt = new Date();
				sinfo.score=0;
				sinfo.save();
				profile.schedule.add(sinfo);
			}
			profile.user = user;
			profile.save();

		}
		
		List<UserExercise> exercises = UserExercise.find("user", user).filter("tags", course).asList();
		if(exercises.size() == 0){
			List<ScheduleInfo> list = ScheduleInfo.find("user", user).asList();
			List<Tag> tags= new ArrayList<Tag>();
			for(ScheduleInfo info:list){
				tags.add(info.tag);
			}
			List<Subject> sbs = Subject.find("tags in", tags).asList();
			for(Subject sb :sbs){
				UserExercise ue = new  UserExercise();
				ue.subject = sb;
				ue.createAt = new Date();
				ue.tags = sb.tags;
				ue.status = UserExerciseStatus.START;
				ue.user = user;
				ue.save();
				exercises.add(ue);
			}
		}
		render(exercises);
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
	
	public static void getTag(){
		String courseName ="";
		Tag parent = TagService.getTag(courseName);
		
		List<Tag> tags = new ArrayList<Tag>();
		//getTag(parent);
		
	}
	
	@Util
	public static List<Tag> getTag(List<Tag>  tags ,List<Tag> allTags){
		
		for(Tag tag:tags){
			List<Tag> temp = Tag.filter("context", tag).asList();
			if(temp.size() ==0){
				allTags.add(tag);
				System.out.println(tag.name);
			}else{
				getTag(temp,allTags);
			}
			
		}
		return allTags;
	}
	
	public  static void tesTags(){
		List<Tag> parents = Tag.find("name","MathTag").asList();
		List<Tag> list = new ArrayList<Tag>();
		list = getTag(parents,list);
		
		String txt ="";
		for(Tag t:list){
			txt= txt+"("+t.name+")";
		}
		renderText(txt);
	}
}
