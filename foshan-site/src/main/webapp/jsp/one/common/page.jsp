<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


			<div class="pagin">
					<c:choose>
						<c:when test="${pb.pageNo-1>0}">
							<c:set var="beginNo" value="${pb.pageNo-1 }"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="beginNo" value="2"></c:set>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${pb.lastPageNo-pb.pageNo>1}">
							<c:set var="endNo" value="${pb.pageNo+1 }"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="endNo" value="${pb.pageNo}"></c:set>
						</c:otherwise>
					</c:choose>
					
					<div class="message">共<i class="blue">${pb.rows}</i>条记录，当前显示第&nbsp;<i class="blue">${pb.pageNo}</i>页 ,共 <i class="blue">${pb.totalPages}</i>页</div>
                    <!--  -->
                    <ul class="paginList">
                    	 <c:choose>
							<c:when test="${pb.pageNo==1 }">								
								<li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
							</c:when>
							<c:otherwise>
								<li class="paginItem"><a href="javascript:;" pages="${pb.prePageNo}"><span class="pagepre"></span></a></li>
							</c:otherwise>
						</c:choose>
						
						
						<c:if test="${pb.lastPageNo>0}">
							<c:choose>
								<c:when test="${1==pb.pageNo }">
									 <li class="paginItem"><a href="javascript:;">1</a></li>
								</c:when>
								<c:otherwise>
									 <li class="paginItem"><a href="javascript:;" pages="1">1</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
						
						<c:if test="${pb.pageNo>3}">
            				<li class="paginItem more"><a href="javascript:;">...</a></li>
						</c:if>
						
						<c:forEach var="item" begin="${beginNo}" end="${endNo }" varStatus="itemStatus">
						<c:if test="${item!=1 && item!=pb.lastPageNo }">
							<c:choose>
								<c:when test="${itemStatus.index==pb.pageNo }">
									<li class="paginItem"><a href="javascript:;">${item}</a></li>
								</c:when>
								<c:otherwise>									
									<li class="paginItem"><a pages="${item}" href="javascript:;" >${item}</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					
					<c:if test="${pb.pageNo<pb.lastPageNo-2}">
            			<li class="paginItem more"><a href="javascript:;">...</a></li>
					</c:if>
					
					<c:if test="${pb.lastPageNo>1}">
						<c:choose>
							<c:when test="${pb.lastPageNo==pb.pageNo }">
								<li class="paginItem"><a href="javascript:;">${pb.lastPageNo}</a></li>
							</c:when>
							<c:otherwise>
								<li class="paginItem"><a  pages="${pb.lastPageNo}" href="javascript:;">${pb.lastPageNo}</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>

					<c:choose>
						<c:when test="${pb.pageNo==pb.lastPageNo || pb.lastPageNo==0}">
							<li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a href="javascript:;" pages="${pb.nextPageNo}"><span class="pagenxt"></span></a></li>
						</c:otherwise>
					</c:choose>
						
					<!--  &nbsp;&nbsp;第<input type="text" size="5" id="inputpage"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" >页 <a class="jumppage" id="jumppage" href="javascript:;">确定</a>
                    -->
                    </ul>
				</div>
				
<script>
$(function(){
	$(".paginItem a[pages]").on("click",pageQuery);	
});
</script>