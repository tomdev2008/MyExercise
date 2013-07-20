package controllers.admin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import models.Tag;

import org.apache.commons.lang.StringUtils;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;

import play.Logger;
import play.modules.morphia.Model.MorphiaQuery;
import play.mvc.Controller;
import play.mvc.Util;
import service.EasyUIDataGridService;
import service.TagService;
import service.UserService;


public class Tags extends Controller {

	public static void index() {
		render();
    }
	
	public static void list(final int page, final int rows, final String order,
			final String sort, final String keyword) {
		MorphiaQuery query = Tag.find();
		Map result = EasyUIDataGridService.getDataGridMap(page, rows, sort, order, query);
		renderJSON(result, new play.modules.morphia.utils.ObjectIdGsonAdapter());
    }
	
	public static void add() {
        render();
    }
	
	public static void create(String tagName) {
		
		if(!TagService.isExist(tagName)){
			TagService.addTag(tagName);
		}
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
			Tag sl = Tag.findById(id);
			if (sl == null) {
				continue;
			}
		}
		result.put("success", "删除成功");
		render();
	}

}
