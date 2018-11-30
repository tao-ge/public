package com.ycnet.core;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.frms.bean.Users;

public class URLInterceptor extends HandlerInterceptorAdapter {
    // spring 注解
    //private MenuService menuServiceInterface;
    private Boolean urlFlag = false;
    private int depth = 0;
    private String operaPath = "";

    
    /**
     * 在请求处理前拦截URL 进行相应处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取当前请求路径
        String currentURL = request.getServletPath();
        boolean isFilter = true;
        depth = 0;
        urlFlag = false;
        
        String patchca = (String)request.getSession().getAttribute("WECHAT_PATCHCA");
        
		if (currentURL.indexOf("/m/") != -1 
				|| currentURL.indexOf("/iam/") != -1 
				|| currentURL.indexOf("/opd/") != -1
				|| currentURL.indexOf("/ocii/") != -1) {
            // 设置直接放过的控制器 不必拦截
            String[] noFilterURLs = getNoFilters();
            // 判断请求路径是否需要拦截
            for (String url : noFilterURLs) {
                if (currentURL.indexOf(url) != -1) {
                    isFilter = false;
                    break;
                }
            }
            
            if(isFilter){
                HttpSession session = request.getSession();
                Users user = (Users)session.getAttribute("users");
                Users user_gl = (Users)session.getAttribute("users_gl");
                
                if(currentURL.indexOf("/m/") != -1) {
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out;
                    if (user == null) {
                        try {
                            out = response.getWriter();
                            out.print("{\"r\":-1,\"r_content\":\"用户未登录！\",\"dt\":null,\"dtList\":[]}");
                            out.close();
                        } catch (IOException e) {
                            out=null;
                        }
                        return false;
                    }
				} else if (currentURL.indexOf("/iam/") != -1 || currentURL.indexOf("/opd/") != -1) {
					AppResponseEntity r = new AppResponseEntity();
					r.setCode("-1");
					r.setMessage("用户未登录！");
					r.setTranCode("obstruct");
					r.setDto(null);
					
					response.setContentType("application/json;charset=utf-8");
					PrintWriter out;
                    if (user == null) {
                        try {
                            out = response.getWriter();
                            out.print(r.toString());
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            out=null;
                        }
                        return false;
                    }
				} else if (currentURL.indexOf("/ocii/") != -1) {
					AppResponseEntity r = new AppResponseEntity();
					r.setCode("-1");
					r.setMessage("用户未登录！");
					r.setTranCode("obstruct");
					r.setDto(null);
					
					response.setContentType("application/json;charset=utf-8");
					PrintWriter out;
                    if (user_gl == null) {
                        try {
                            out = response.getWriter();
                            out.print(r.toString());
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            out=null;
                        }
                        return false;
                    }
				}
                
            }
        }else if(currentURL.indexOf("/wechat/") != -1) {
        	return true;
        }else if(currentURL.indexOf("/hzgc/") != -1 ){
        	//杭州干城接口，暂不处理
        }else if(currentURL.indexOf("/test/") != -1 ){
        	//测试专用
        }else{
            if(!checkManagerHtm(currentURL)){
                HttpSession session = request.getSession();
        		Users user = (Users) session.getAttribute("platformUser");
        		if(user==null){
                	try {
    					//response.sendRedirect(request.getContextPath()+"/login.htm");
    					response.getWriter().write("<script> parent.window.location.href= '"+request.getContextPath()+"/login.htm"+"' </script>");
    					
                        return false;
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        		}
            }

        }
    	return true;
    }

    /**
     * 递归路径
     * 
     * @param menuVos
     * @param menuVo
     * @param path
     * @return
     */
//    public String findOperaPath(List<MenuVo> menuVos, MenuVo menuVo, String path) {
//        for (MenuVo mv : menuVos) {
//            if (depth == 4) {
//                break;
//            }
//            if (mv.getId() != null && menuVo.getParentId() != null && menuVo.getParentId().toString().equals(mv.getId().toString())) {
//                operaPath = mv.getDesignation() + "-->" + path;
//                depth++;
//                findOperaPath(menuVos, mv, operaPath);
//            }
//        }
//        return operaPath;
//    }

    /**
     * 管理员权限验证
     * 
     * @param currnetURL
     * @return
     */
    public boolean checkManagerHtm(String currnetURL) {
        // 判断请求路径是否为管理员权限URL
        String[] urls = getManagerFilters();
        for (String url : urls) {
            if (currnetURL.indexOf(url) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置不用拦截的htm
     * 
     * @return String []
     */
    private String[] getManagerFilters() {
        return new String[] {"/initSetting.htm"
                // 以下勿动
                // "/index.htm",
                /*"/loadMenus.htm", "/initSetting.htm", "/initManager.htm", "/initAuthority.htm", "/jumpForPageView.htm", "/queryAllAuthority.htm", "/queryAuthorByManagerId.htm", "/queryAuthority.htm", "/deleteAuthority.htm", "/updateAuthority.htm", "/queryAuthorityById.htm", "/queryAuthorityByAId.htm", "/initAuthority.htm", "/addAuthority.htm", "/queryManagerById.htm", "/queryByManager.htm", "/addManager.htm", "/batchDelLack.htm", "/deleteManager.htm", "/updateManager.htm", "/queryMenuVoList.htm", "/delPage.htm", "/batchDelPage.htm", "/savePage.htm", "/updatePage.htm", "/queryPageById.htm", "/checkDelPage.htm", "/queryAllMenuVo.htm", "/queryAllMenu.htm", "/checkauthexist.htm", "/checkmanagerexist.htm",
                // "/modifymanager.htm",
                "/selectEmp.htm",*/
        		,"/login.htm","/userLogin.htm","/patchca.htm","/conectionUs.htm","/aboutUs.htm"

        };
    }

    /**
     * 设置不用拦截的htm
     * 
     * @return String []
     */
    private String[] getNoFilters() {
        return new String[] {
        		/*uLogin移动登录接口，不拦截*/
        		"uLogin.htm",
        		"appLogin.htm",
        		"validateUnlock1.htm",
        		"uploadImageCamera.htm"
        };
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//    }
//
//    public MenuService getMenuServiceInterface() {
//        return menuServiceInterface;
//    }
//
//    @Resource(name = "menuServiceInterface")
//    public void setMenuServiceInterface(MenuService menuServiceInterface) {
//        this.menuServiceInterface = menuServiceInterface;
//    }

}
