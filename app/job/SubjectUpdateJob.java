package job;

import models.Subject;
import models.User;
import models.UserExercise;
import play.jobs.Job;
import play.modules.morphia.Model.MorphiaQuery;

public class SubjectUpdateJob extends Job {

	
	private Subject sb;
	public SubjectUpdateJob(Subject sb){
		
		this.sb =sb;
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
		
		
		
		
		
		/**
		 * 0. 答题内容记录(log)
			
			2. 用户Profile信息更新：
				学科预计分数更新
				知识点图谱更新
				知识点属性(新增，预测分数，强化考点？，提升考点？)
			3. 新题入库：
				根据知识点
				根据试题相似度
				根据协同过滤
			4. 用户历史题目信息更新：
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
		 * **/
		
		
    }
}
