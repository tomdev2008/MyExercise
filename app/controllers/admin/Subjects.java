package controllers.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import models.Option;
import models.Subject;
import models.Tag;
import models.enums.SubjectStatus;
import models.enums.SubjectType;
import play.modules.morphia.Model.MorphiaQuery;
import play.mvc.Controller;
import service.EasyUIDataGridService;
import service.TagService;
import service.UserService;


public class Subjects extends  Controller{

	public static void index() {
		render();
    }
	
	public static void list(final int page, final int rows, final String order,
			final String sort, final String keyword) {
		MorphiaQuery query = Subject.find();
		Map result = EasyUIDataGridService.getDataGridMap(page, rows, sort, order, query);
		renderJSON(result, new play.modules.morphia.utils.ObjectIdGsonAdapter());
    }
	
	public static void add() {
        render();
    }
	
	public static void create(String title,String subjectType,String tags,String solution) {
		
		String[] options = params.getAll("option");
		Subject sb = new Subject();
		sb.title =title;
		int cnt =1;
		for(String option : options){
			Option o = new Option();
			o.content=option;
			o.save();
			sb.options.add(o);
			if(cnt ==1){
				sb.answer = o;
			}
			cnt++;
		}
//		String[] tagArr = tags.split(",");
//		for(String tag:tagArr){
//			Tag t = TagService.getTag(tag);
//			sb.tags.add(t);
//		}
		
		sb.owner = UserService.getUser("admin");
		sb.status = SubjectStatus.VALID;
		sb.type = SubjectType.valueOf(subjectType);
		sb.solution = solution;
		sb.save();
		index();
    }
	
	public static void update(final String id){
		render();
	}
	
	
	public static void delete(final String ids){
		Map result = new HashMap();
		if (StringUtils.isBlank(ids)) {
			result.put("error", "请选择要删除的资源");
			renderJSON(result);
		}
		String[] idArray = ids.split(",");
		for (String id:idArray) {
			Subject sl = Subject.findById(id);
			if (sl == null) {
				continue;
			}
		}
		result.put("success", "删除成功");
		render();
	}
}
