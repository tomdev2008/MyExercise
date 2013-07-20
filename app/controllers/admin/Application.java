package controllers.admin;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Menu;
import controllers.Public;
import controllers.wither.LogPrinter;
import play.libs.Codec;
import play.mvc.Controller;
import play.mvc.With;
import service.TreeService;

/**
 * 后台运营管理系统页面控制器.
 * @author zhaojingyu
 * @since 2012-11-08
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
}