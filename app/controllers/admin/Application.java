package controllers.admin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import models.Menu;
import models.Tag;
import controllers.Public;
import controllers.wither.LogPrinter;
import play.Play;
import play.libs.Codec;
import play.mvc.Controller;
import play.mvc.With;
import service.TagService;
import service.TreeService;

/**
 * 后台运营管理系统页面控制器.
 * @author jiwei
 * @since 2013-7-14
 */
@With({Secure.class, LogPrinter.class })
public class Application extends Controller {
	/**
	 * <p>首页导航页面.</p>
	 */
    public static void index() {
        render();
    }
    /**
	 * <p>首页登陆页面.</p>
	 */
    public static void login() {
        render();
    }
    /**
     * <p>首页显示的欢迎页面.</p>
     */
    @Public
    public static void welcome() {
    	render();
    }
    
	/**
	 * 生成首页菜单树.
	 */
	public static void tree() {
		List<Menu> roots = Menu.filter("parent exists", false).order("order").asList();
		List<Map> result = new ArrayList();
		TreeService.createTree(roots, result, true);
		renderJSON(result);
	}
	
	
	public static void initTag() throws IOException{
		
		
		File file = Play.getFile("/tags/english-1.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tag = null;
		while((tag =br.readLine()) != null) {
			TagService.addTag(tag);
		}
		br.close();
		reader.close();
		
		file = Play.getFile("/tags/grade-1.txt");
		reader = new FileReader(file);
		br = new BufferedReader(reader);
		
		while((tag =br.readLine()) != null) {
			TagService.addTag(tag);
		}
		br.close();
		reader.close();
		
		file = Play.getFile("/tags/math-1.txt");
		reader = new FileReader(file);
		br = new BufferedReader(reader);
		
		while((tag =br.readLine()) != null) {
			TagService.addTag(tag);
		}
		br.close();
		reader.close();
		
		file = Play.getFile("/tags/origin-1.txt");
		reader = new FileReader(file);
		br = new BufferedReader(reader);
		
		while((tag =br.readLine()) != null) {
			TagService.addTag(tag);
		}
		br.close();
		reader.close();
		
		
		file = Play.getFile("/tags/subject-1.txt");
		reader = new FileReader(file);
		br = new BufferedReader(reader);
		
		while((tag =br.readLine()) != null) {
			TagService.addTag(tag);
		}
		br.close();
		reader.close();
		
	}
	
	public static void get(){
		
		
		
	}
}