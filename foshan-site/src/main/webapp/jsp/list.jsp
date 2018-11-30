<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath()+""; 
request.setAttribute("path",rc);
%>
<html>
<body>

	 <div class="row  border-bottom white-bg dashboard-header">
                <div class="col-sm-12">
                    <blockquote class="text-warning" style="font-size:14px">您是否需要自己做一款后台、会员中心等等的，但是又缺乏html等前端知识…
                        <br>您是否一直在苦苦寻找一款适合自己的后台主题…
                        <br>您是否想做一款自己的web应用程序…${str }${path }
                        <br>…………
                        <h4 class="text-danger">那么，现在H+来了</h4>
                    </blockquote>

                    <hr>
                </div>
                <div class="col-sm-3">
                    <h2>Hello,Guest</h2>
                    <small>移动设备访问请扫描以下二维码：</small>
                    <br>
                    <br>
                    <img src="img/qr_code.png" width="100%" style="max-width:264px;">
                    <br>
                </div>
                <div class="col-sm-5">
                    <h2>
                            素材火www.sucaihuo.com
                        </h2>
                    <p>H+是一个完全响应式，基于Bootstrap3.3.4最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.1)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如<b>网站管理后台</b>，<b>网站会员中心</b>，<b>CMS</b>，<b>CRM</b>，<b>OA</b>等等，当然，您也可以对她进行深度定制，以做出更强系统。</p>
                    <p>
                        <b>当前版本：</b>v2.0
                    </p>
                    <p>
                        <b>定价：</b><span class="label label-warning">&yen;768（不开发票）</span>
                    </p>
                    <br>
                    <p>
                        <a class="btn btn-success btn-outline" href="http://wpa.qq.com/msgrd?v=3&uin=516477188&site=qq&menu=yes" target="_blank">
                            <i class="fa fa-qq"> </i> 联系我
                        </a>
                        <a class="btn btn-white btn-bitbucket" href="http://www.zi-han.net" target="_blank">
                            <i class="fa fa-home"></i> 访问博客
                        </a>
                    </p>
                </div>
                <div class="col-sm-4">
                    <h4>H+具有以下特点：</h4>
                    <ol>
                        <li>完全响应式布局（支持电脑、平板、手机等所有主流设备）</li>
                        <li>基于最新版本的Bootstrap 3.3.4</li>
                        <li>提供4套不同风格的皮肤</li>
                        <li>支持多种布局方式</li>
                        <li>使用最流行的的扁平化设计</li>
                        <li>提供了诸多的UI组件</li>
                        <li>集成多款国内优秀插件，诚意奉献</li>
                        <li>提供盒型、全宽、响应式视图模式</li>
                        <li>采用HTML5 &amp; CSS3</li>
                        <li>拥有良好的代码结构</li>
                        <li>更多……</li>
                    </ol>
                </div>

            </div>
</body>
</html>