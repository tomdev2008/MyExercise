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


public class Tags extends Controller {

	public static void search(String categoryId, String name, String tag,
			final int rows, final int page, final String sort,
			final String order) {
		
	}
	
	
//	
//
//	@Util
//	public static void renderJsonText(Object o) {
//		response.contentType = "application/json; charset=" + response.encoding;
//		try {
//			renderText(new ObjectMapper().writeValueAsString(o));
//		} catch (IOException e) {
//			Logger.error(e, e.getMessage());
//			throw new RuntimeException();
//		}
//	}

}
