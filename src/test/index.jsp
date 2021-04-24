<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>#{msg.welcome_title}</title>
</head>
<body>
<h:head>
    <title>#{msg.welcome_title}</title>
    <h:outputStylesheet library="css" name="index_style.css" />
</h:head>
<h:body>

<div class="main">

    <div class="img_block1">
        <p style="text-align: center;"><h:graphicImage library="img" name="1984.jpeg" width="100" vspace="10" align="center" alt="1984"/></p>
        <p style="text-align: center;"><h:graphicImage library="img" name="2033.jpg" width="100" vspace="10" align="center" alt="2033"/></p>
        <p style="text-align: center;"><h:graphicImage library="img" name="2034.jpg" width="100" vspace="10" align="center" alt="2034"/></p>
    </div>

    <div class="content">
        <p class="title"><h:graphicImage library="img" name="lib.png" /></p>
        <p class="title">#{msg.welcome_title}</p>
    </div>

    <div  class="login_div">
        <p class="title"> #{msg.input_info}</p>
        <h:form styleClass="login_form">
            #{msg.input_name}<h:inputText id="username" value="#{user.username}"/>
        </h:form>

        <div class="footer">
                #{msg.author_info}
        </div>

        <div class="img_block2">
            <p style="text-align: center;"><h:graphicImage library="img" name="dogray.jpg" width="100" vspace="10" align="middle" alt="dogray"/></p>
            <p style="text-align: center;"><h:graphicImage library="img" name="flow.jpg" width="100" vspace="10" align="middle" alt="flow"/></p>
            <p style="text-align: center;"><h:graphicImage library="img" name="hoking.jpg" width="100" vspace="10" align="middle" alt="hoking"/></p>
        </div>

    </div>
</div>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>