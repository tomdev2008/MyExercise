package controllers;

import play.mvc.Controller;

public class Students extends Controller {
	public static void index(final String userid) {
		String str = "{&quot;id&quot;:4,&quot;name&quot;:&quot;考研政治&quot;,&quot;prefix&quot;:&quot;kyzz&quot;,&quot;scannable&quot;:true,&quot;modules&quot;:[{&quot;type&quot;:&quot;instant&quot;,&quot;desc&quot;:&quot;根据你对考点的掌握程度智能出题，提升综合能力&quot;,&quot;hidden&quot;:false},{&quot;type&quot;:&quot;keypoint&quot;,&quot;desc&quot;:&quot;自主选择具体考点，各个击破&quot;,&quot;hidden&quot;:false},{&quot;type&quot;:&quot;template&quot;,&quot;desc&quot;:&quot;根据最新考研政治大纲的考查要求为你生成的模拟卷&quot;,&quot;hidden&quot;:false},{&quot;type&quot;:&quot;paper&quot;,&quot;desc&quot;:&quot;提供1994年至2013年共20年考研政治真题模考&quot;,&quot;hidden&quot;:false},{&quot;type&quot;:&quot;sprint&quot;,&quot;desc&quot;:&quot;考前提供由业界名师命制的考前密卷、时政热点及高频考点练习&quot;,&quot;hidden&quot;:false},{&quot;type&quot;:&quot;latest&quot;,&quot;desc&quot;:null,&quot;hidden&quot;:false}],&quot;properties&quot;:null}";
		str = str.replaceAll("&quot;", "&");
		System.out.println(str);
		render();
	}
}
