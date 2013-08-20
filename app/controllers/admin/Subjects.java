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
	
	public static void detail(String id){
		Subject item = Subject.findById(id);
		render(item);
	}
	
	public static void add() {
        render();
    }
	
	public static void create(String title,String subjectType,String course,String grade,String tags,String solution) {
		
		String[] options = params.getAll("option");
		String[] answer = params.getAll("isAnswer");
		Subject sb = new Subject();
		sb.title =title;
		int cnt =1;
		for(String option : options){
			Option o = new Option();
			o.content=option;
			o.save();
			sb.options.add(o);
			for(String t :answer ){
				if(cnt == Integer.valueOf(t)){
					sb.answer.add(o);
				}
			}
			cnt++;
		}
		String[] tagArr = tags.split(",");
		for(String tag:tagArr){
			Tag t = TagService.getTag(tag);
			sb.tags.add(t);
		}
		Tag t = TagService.getTag(course);
		sb.course =t;
		t = TagService.getTag(grade);
		sb.grade =t;
		sb.owner = UserService.getUser("admin");
		sb.status = SubjectStatus.VALID;
		sb.type = SubjectType.valueOf(subjectType);
		sb.solution = solution;
		sb.save();
		index();
    }
	
	public static void update(final String id){
		Subject item = Subject.findById(id);
		render(item);
	}
	
	public static void modify(String subjectId, String title,String subjectType,String course,String grade,String tags,String solution, String removeTagIds, String removeOptionIds) {
		Subject sb = Subject.findById(subjectId);
		if (StringUtils.isNotBlank(removeTagIds)) {
			String[] removeTags = removeTagIds.split(",");
			if (removeTags.length > 0) {
				for (String tag : removeTags) {
					Tag t = Tag.findById(tag);
					sb.tags.remove(t);
				}
			}
		}
		if (StringUtils.isNotBlank(removeOptionIds)) {
			String[] removeOptions = removeOptionIds.split(",");
			if (removeOptions.length > 0) {
				for (String option : removeOptions) {
					Option o = Option.findById(option);
					sb.options.remove(o);
				}
			}
		}
		String[] options = params.getAll("option");
		String[] answer = params.getAll("isAnswer");
		sb.title =title;
		int cnt =1;
		for(String option : options){
			Option o = new Option();
			o.content=option;
			o.save();
			sb.options.add(o);
			if (answer != null) {
				for(String t :answer ){
					if(cnt == Integer.valueOf(t)){
						sb.answer.add(o);
					}
				}
			}
			cnt++;
		}
		if (StringUtils.isNotBlank(tags)) {
			String[] tagArr = tags.split(",");
			for(String tag:tagArr){
				Tag t = TagService.getTag(tag);
				sb.tags.add(t);
			}
		}
		Tag t = TagService.getTag(course);
		sb.course =t;
		t = TagService.getTag(grade);
		sb.grade =t;
		sb.owner = UserService.getUser("admin");
		sb.status = SubjectStatus.VALID;
		sb.type = SubjectType.valueOf(subjectType);
		sb.solution = solution;
		sb.save();
		index();
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
