package com.kael.util;

import java.math.BigDecimal;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class JavaHtmlUnit {
	public static void main(String[] args) throws Exception{
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		
		String loginQzoneUrl = "http://xui.ptlogin2.qq.com/cgi-bin/xlogin?"
				+ "proxy_url=http%3A//qzs.qq.com/qzone/v6/portal/proxy.html&daid=5&pt_qzone_sig=1&hide_title_bar=1&"
				+ "low_login=0&qlogin_auto_login=1&no_verifyimg=1&link_target=blank&appid=549000912&style=22&target=self&"
				+ "s_url=http%3A//qzs.qq.com/qzone/v5/loginsucc.html?para=izone&pt_qr_app=%E6%89%8B%E6%9C%BAQQ%E7%A9%BA%E9%97%B4&"
				+ "pt_qr_link=http%3A//z.qzone.com/download.html&self_regurl=http%3A//qzs.qq.com/qzone/v6/reg/index.html&"
				+ "pt_qr_help_link=http%3A//z.qzone.com/download.html";
		HtmlPage page = webClient.getPage(loginQzoneUrl);
		HtmlTextInput InputUserName = (HtmlTextInput) page.getElementById("u");
		HtmlPasswordInput InputPassword = (HtmlPasswordInput) page.getElementById("p");
		InputUserName.setText("");
		InputPassword.setText("");
		HtmlSubmitInput hb = (HtmlSubmitInput) page.getElementById("login_button");
		if(Boolean.valueOf(page.executeJavaScript("window.hasOwnProperty('ownerProfileSummary')").getJavaScriptResult().toString())){
			String qqName = page.executeJavaScript("ownerProfileSummary[0]").getJavaScriptResult().toString();
			//loginUser.setQqName(qqName);
		}
		
		long g_tk = new BigDecimal(page.executeJavaScript("QZONE.FP.getACSRFToken()").getJavaScriptResult().toString()).longValue();
		System.out.println(g_tk);
	}

}
