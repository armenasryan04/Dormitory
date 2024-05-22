<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.03.2024
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dormitory.models.Student" %>
<html>
<head>
    <title>Deactivate</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>

<div class="wave"></div>
<div class="wave"></div>
<div class="wave"></div>



<br/>
<div class="wrapper">
    <span class="menu"><i style="font-size:44px; " class='bx bx-menu'></i></span>
    <div class="overlay">
        <a style="position: absolute;top:5px " class="gradient-button" href="/logout" ><i class='bx bx-log-out'></i></a>
        <ul>
            <li><a href="/control"><i class='bx bxs-home'></i></a></li>
            <li><a href="/refactorMenu" id="backLink">Back</a></li>
        </ul>
    </div>
    <div class="blurry-background"></div>
</div>
<div class="wrapper-data">
    <div class="title">Deactivation</div>
<div class="content"></div>
    <form action="/deactivation" method="post">
        <div class="field">
            <input type="text" required name="code">
            <label class="input-box">Your Control Code</label>
        </div>
        <br/>
        <p style="color: orange;font-size: 10px" >ask the director
        </p>
        <div class="field" >
            <br/>
            <input type="submit" value="confirm">
        </div>

    </form>

    <% if (request.getAttribute("errMsg") != null) { %>
    <div id="errorContainer" class="error-container">
        <div id="errorMessage" class="error-message">
            <p><%= request.getAttribute("errMsg") %>
            </p>
        </div>
        <% } %>
            <% if (request.getAttribute("reqMsg") != null && (request.getAttribute("errMsg") == null)  ) { %>
        <div id="requestContainer" class="request-container">
            <div id="requestMessage"  class="request-message" >
                <%=request.getAttribute("reqMsg")%>
                <br/>
                <a id="cancel" href="#" style="color: red">YES</a> || <a href="/refactorMenu" style="color: orange">NO</a>
            </div>
    </div>

</body>

</div>
<% } %>
<style type="text/css">
    .wave {
        background: rgb(255 255 255 / 25%);
        border-radius: 1000% 1000% 0 0;
        position: fixed;
        width: 200%;
        height: 12em;
        animation: wave 10s -3s linear infinite;
        transform: translate3d(0, 0, 0);
        opacity: 0.8;
        bottom: 0;
        left: 0;
        z-index: -1;
    }

    .wave:nth-of-type(2) {
        bottom: -1.25em;
        animation: wave 18s linear reverse infinite;
        opacity: 0.8;
    }

    .wave:nth-of-type(3) {
        bottom: -2.5em;
        animation: wave 20s -1s reverse infinite;
        opacity: 0.9;
    }

    @keyframes wave {
        2% {
            transform: translateX(0%);
        }

        25% {
            transform: translateX(-25%);
        }

        50% {
            transform: translateX(-50%);
        }

        75% {
            transform: translateX(-25%);
        }

        100% {
            transform: translateX(0%);
        }
    }
    body {
        font-family: -apple-system, BlinkMacSystemFont, sans-serif;
        overflow: auto;
        background: linear-gradient(315deg, rgba(101, 0, 94, 1) 3%, rgba(60, 132, 206, 1) 38%, rgba(48, 238, 226, 1) 68%, rgba(255, 25, 25, 1) 98%);
        animation: gradient 15s ease infinite;
        background-size: 400% 400%;
        background-attachment: fixed;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0;
    }

    @keyframes gradient {
        0% {
            background-position: 0% 0%;
        }
        50% {
            background-position: 100% 100%;
        }
        100% {
            background-position: 0% 0%;
        }
    }

    .gradient-button {
        top: 20px;
        right: 20px;
        text-decoration: none;
        color: #4907bb;
        display: inline-block;
        padding: 10px 20px;
        margin: 5px 15px;
        border-radius: 10px;
        text-transform: uppercase;
        letter-spacing: 2px;
        background-image: linear-gradient(to right, #428af6 0%, #fdd100 51%, rgb(80, 0, 241) 100%);
        background-size: 200% auto;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.31);
        transition: text-shadow 0.5s ease;
        transition: .5s;
    }

    .gradient-button:hover {
        background-position: right center;
        color: rgb(0, 0, 0);
        box-shadow: 0 0 10px #f519f5;
    }

    * {
        box-sizing: border-box;
    }

    body {
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #6259e7;
        font-family: 'Lato', sans-serif;
    }


    * {
        padding: 0;
        margin: 0;
    }

    .wrapper {
        width: 100%;
        height: 100vh;
        position: absolute;
        overflow: hidden;
    }

    .wrapper i {
        font-size: 25px;
    }

    .wrapper span {
        z-index: 999955887;
        position: absolute;
        top: 10px;
        left: 20px;
        width: 64px;
        height: 43px;
        color: #4907bb;
        display: inline-block;
        padding: 0px 9px;
        margin: auto;
        border-radius: 10px;
        cursor: pointer;
        background-image: linear-gradient(to right, #428af6 0%, #fdd100 51%, rgb(80, 0, 241) 100%);
        background-size: 200% auto;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.31);
        transition: text-shadow 0.5s ease, .5s;
    }

    .wrapper span:hover {
        background-position: right center;
        color: rgb(0, 0, 0);
        box-shadow: 0 0 10px #f519f5;
    }



    .wrapper .overlay {
        position: absolute;
        bottom: -100%;
        height: 100%;
        background: linear-gradient(rgba(0, 241, 76, 0.82), rgba(73, 7, 187, 0.84));
        left: 0;
        width: 100%;
        transition: all 0.5s ease;
    }

    .blurry-background {
        position: absolute;
        top: 0;
        right: 0;
        width: 100%;
        height: 100%;
        backdrop-filter: blur(10px);
        -webkit-backdrop-filter: blur(10px);
        z-index: 99;
        opacity: 0;
        transition: opacity 0.5s ease;
    }
    .blurry {
        z-index: 100;
        opacity: 1;
    }

    .wrapper .overlay.anim {
        left: 0;
        bottom: 0;
        animation: menu-anim 1.5s 1 ease-out forwards;
        width: 25%;
        right: 75%;
        z-index: 9955;
        transition: all 0.5s ease;

    }

    .wrapper .overlay ul {
        width: 100%;
        text-align: center;
        margin-top: 100px;
        padding-left: 0;
        margin-left: -10px;
        font-size: 1em;
        font-weight: 800;
    }

    .wrapper .overlay ul li {
        margin: 10px 0;
        transition: all 1s ease;
    }

    .wrapper .overlay ul li a {
        text-decoration: none;
        color: #000000;
        position: relative;
        display: inline-block;
        padding: 20px 0;
        overflow: hidden;
    }
    .wrapper .overlay ul li:hover{
        text-shadow:#f519f5 1px 0 10px;
    }
    .wrapper .overlay ul li a:after {
        display: block;
        border-radius: 2px;
        content: '';
        left: 0;
        bottom: -10px;
        height: 5px;
        background: #4e00ce;
        transform: translateX(-101%);
    }

    .wrapper .overlay ul li a:hover:after {
        animation: border-anim 0.5s 1 ease normal;
        transform: translateX(0);
    }

    @keyframes menu-anim {
        0% {
            left: -99.5%;
            bottom: -99%;
            width: 100%;
        }

        50% {
            left: -99.5%;
            bottom: 0;
            width: 100%;
        }

        100% {
            bottom: 0;
            left: 0;
            width: 25%;
        }
    }

    @-webkit-keyframes border-anim {
        0% {
            transform: translateX(-100%);
        }

        100% {
            transform: translateX(0);
        }
    }
    ::selection {
        background: #4158d0;
        color: #ffffff;
    }

    .wrapper-data {
        width: 380px;
        background:  linear-gradient(135deg, rgba(165, 54, 239, 0.44), #00878c);
        border-radius: 15px;
        box-shadow: 0px 15px 20px rgba(0, 0, 0, 0.1);
        z-index: 99;
    }

    .wrapper-data .title {
        font-size: 35px;
        font-weight: 600;
        text-align: center;
        line-height: 100px;
        color: #fff;
        user-select: none;
        border-radius: 15px 15px 0 0;
        background:linear-gradient(135deg, #a436ed, #36b7ef); );
    }

    .wrapper-data form {
        padding: 10px 30px 50px 30px;
    }

    .wrapper-data form .field {
        height: 50px;
        width: 100%;
        margin-top: 20px;
        position: relative;
    }

    .wrapper-data form .field input {
        height: 100%;
        width: 100%;
        outline: none;
        font-size: 17px;
        padding-left: 20px;
        border: 1px solid lightgrey;
        border-radius: 25px;
        transition: all 0.3s ease;
        background: rgba(157, 241, 217, 0.29);
    }

    .wrapper-data form .field input:focus,
    form .field input:valid {
        border-color: #c7af62;
    }

    .wrapper-data form .field label {
        position: absolute;
        top: 50%;
        left: 20px;
        color: #151b2c;
        font-weight: 400;
        font-size: 17px;
        pointer-events: none;
        transform: translateY(-50%);
        transition: all 0.3s ease;
    }

    form .field input:focus ~ label,
    form .field input:valid ~ label {
        top: 0%;
        font-size: 14px;
        color: rgba(30, 2, 166, 0.4);
        background:linear-gradient(135deg, #a436ed, #36b7ef);
        border-radius: 7px;
        transform: translateY(-50%);
    }

    form .content {
        display: flex;
        width: 100%;
        height: 50px;
        font-size: 16px;
        align-items: center;
        justify-content: space-around;
    }



    form .content input {
        width: 15px;
        height: 15px;
        background: #0e6ec7;
    }

    form .content label {
        color: #1b50a2;
        user-select: none;
        padding-left: 5px;
    }
    form .pass-link a,
    form .signup-link a {
        color: rgba(0, 59, 150, 0.76);
        text-decoration: none;
        transition: all 0.5s ease;
    }
    form .pass-link a:hover,
    form .signup-link a:hover {
        text-decoration: underline;
        color: black;
        text-shadow:#f519f5 1px 0 10px;
    }
    form .field input[type="submit"] {
        color: #4907bb;
        border: none;
        padding-left: 0;
        margin-top: -10px;
        font-size: 20px;
        font-weight: 500;
        cursor: pointer;
        background-image: linear-gradient(to right, #6ee547, #4158d0);
        background-size:200% auto;
        transition: all 0.5s ease;
    }
    form .field input[type =  "submit"]:hover{
        background-position: right center;
        color: #6ee547;
    }

    form .field input[type="submit"]:active {
        transform: scale(0.95);
    }

    .error-container {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        backdrop-filter: blur(3px);
        justify-content: center;
        align-items: center;
    }
    .error-message {
        color: white;
        height: auto;
        width: auto;
        background-color: rgb(114, 3, 3);
        padding: 20px;
        border-radius: 7px;
    }
    .request-container {
        visibility: hidden;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        padding: 500px;
        padding-top: 30%;
        backdrop-filter: blur(3px);
        justify-content: center;
        align-items: center;
        z-index: 9;
    }

    .request-message {
        color: white;
        background-color: rgb(3, 114, 110);
        text-align: center;
        padding: 20px 20px;
        line-height: 1.5;
        underline: none;
        border-radius: 7px;
    }
</style>
<script>
    $(document).ready(function () {
        $('.menu').click(function () {
            $('.overlay').toggleClass('anim');
            $(this).toggleClass('open');
            $('.blurry-background').toggleClass('blurry');
        });
        $(document).click(function (event) {
            if (!$(event.target).closest('.overlay').length && !$(event.target).closest('.menu').length) {
                $('.overlay').removeClass('anim');
                $('.menu').removeClass('open');
                $('.blurry-background').removeClass('blurry');
            }
        });
    });


    function handleButtonClick() {
        var errorContainer = document.getElementById('errorContainer');
        var errorMessage = document.getElementById('errorMessage');
        if (errorContainer && errorContainer.contains(event.target) && !errorMessage.contains(event.target) ) {
            errorContainer.style.display = 'none';
            errorMessage.style.display = 'none'
           location.replace('/studentDataFilling')
        }
    };
    function handleEnterKeyPress() {
        if (event.key === 'Enter' || event.keyCode === 32 ) {

            var errorContainer = document.getElementById('errorContainer');
            var errorMessage = document.getElementById('errorMessage');
            errorContainer.style.display = 'none';
            errorMessage.style.display = 'none'
            location.replace('/studentDataFilling')
        }
    }
    var cancelElement = document.getElementById('cancel');
    <% if (request.getAttribute("errMsg") == null && request.getAttribute("reqMsg")!=null) { %>
    document.getElementById('requestContainer').style.visibility = 'visible';
    document.getElementById('requestMessage').style.visibility = 'visible';
    <% } %>
    <% if (request.getAttribute("errMsg") != null) { %>
    document.getElementById('errorMessage').style.display = 'flex';
    document.getElementById('errorContainer').style.display = 'flex';
    <% } %>
    cancelElement.addEventListener('click', function () {
        document.getElementById('requestMessage').style.visibility = 'hidden';
        document.getElementById('requestContainer').style.visibility = 'hidden';
    })
    document.body.addEventListener('keypress',handleEnterKeyPress)
    document.body.addEventListener('click', handleButtonClick);

</script>
</html>
