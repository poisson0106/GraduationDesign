<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel panel-default">
  	<div class="panel-heading text-center">Main Menu</div>
 	<div class="panel-body">
   		<ul class="nav nav-pills nav-stacked">
  			<li class="active" id="homemenu"><a style="cursor:pointer" id="home">Welcome</a></li>
  			<li id="sendemailmenu"><a style="cursor:pointer" id="sendemail">Send&nbspEmail</a></li>
  			<li id="inboxmenu"><a style="cursor:pointer" id="inbox"><c:if test="${nummail ne 0}"><span class="badge pull-right" id="inboxbage">${nummail }</span></c:if>Inbox</a></li>
  			<li id="delboxmenu"><a style="cursor:pointer" id="delbox">Deleted&nbspBox</a></li>
  			<li id="draftboxmenu"><a style="cursor:pointer" id="draftbox">Draft&nbspBox</a></li>
		</ul>
		<input type="hidden" value=${chosed } id="chosed">
  	</div>
</div>