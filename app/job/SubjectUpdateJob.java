package job;

import java.util.Iterator;
import java.util.List;

import models.Subject;
import models.Tag;
import models.User;
import models.UserExercise;
import play.jobs.Job;
import play.modules.morphia.Model.MorphiaQuery;

public class SubjectUpdateJob extends Job {

	
	private Subject sb;
	private List<UserExercise> ues;
	public SubjectUpdateJob(Subject sb ,List<UserExercise> ues){
		
		this.sb =sb;
		this.ues = ues;
	}
	public void doJob() throws Exception {
		if(sb == null){
			return;
		}
		/**
		 1. 题库信息更新：
				题目：属性：FCR(一次正确率)--如果是该用户第一次展现	
				题目：属性：SCR(二次正确率)--如果是该用户第二次展现
				题目：属性：综合正确率
		 */
		MorphiaQuery query = UserExercise.filter("subject",sb);
		long fc = query.sum("isFC");
		long sc = query.sum("isSC");
		long all = query.countAll();
		long ccnt = query.sum("correctCount");
		long complete = query.sum("completeCount");
		double FCR = (fc/all)*100;
		double SCR = (sc/all)*100;
		double ACR = (ccnt/complete)*100;
		sb.FCR= FCR;
		sb.SCR =SCR;
		sb.ACR = ACR;
		sb.save();
		
		/**展现权重更新
		QS
		if (is 强化考点) +3
		if (abs(U.USS-100*(1-FCR))<10) +2
		if (abs(U.USS-100*(1-FCR))>20) -2
		if (U.KTS == Null) +3
		if (U.KTS > (U.USS+10)) -2
		if (AR==历史题库推荐) +4
		***/
		for(UserExercise ue:ues){
			int weight =ue.weight;
			Iterator it =ue.subject.tags.iterator();
			while(it.hasNext()){
				Tag t = (Tag)it.next();
				if(t.info.containsKey("isImportant")){
					 weight +=3;
				}
			}
			
			
		}
		
    }
}
