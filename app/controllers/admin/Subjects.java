package controllers.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import models.Option;
import models.Subject;
import play.modules.morphia.Model.MorphiaQuery;
import play.mvc.Controller;
import service.EasyUIDataGridService;


public class Subjects extends  Controller{

	public static void index() {
		Subject.findAll();
		render();
    }
	
	public static void list(final int page, final int rows, final String order,
			final String sort, final String keyword) {
		Subject.findAll();
		MorphiaQuery query = Subject.find();
		
		Map result = EasyUIDataGridService.getDataGridMap(page, rows, sort, order, query);
		renderJSON(result, new play.modules.morphia.utils.ObjectIdGsonAdapter());
    }
	
	public static void add() {
        render();
    }
	
	public static void create(String title,String solution) {
		
		String[] options = params.getAll("option");
		System.out.println(options.toString());
		Subject sb = new Subject();
		sb.title =title;
		int cnt =1;
		for(String option : options){
			System.out.println(option);
			Option o = new Option();
			
			o.content=option;
			o.save();
			sb.options.add(o);
			if(cnt ==1){
				sb.answer = o;
			}
			cnt++;
		}
		sb.save();
        redirect("/admin/subject");
    }
	
	public static void update(){
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
