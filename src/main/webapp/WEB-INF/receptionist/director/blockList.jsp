<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="dormitory.models.Student" %>
<%@ page import="dormitory.models.Receptionist" %>
<html>
<head>
    <title>Block List</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<% List<Student> students = (List<Student>) request.getAttribute("students"); %>
<body>

<div class="wave"></div>
<div class="wave"></div>
<div class="wave"></div>
<div class="forming">
    <div class="title">STUDENTS LIST <p style="font-size: 15px; font-weight: 0">STUDENT'S TOTAL
        NUMBER <%=request.getAttribute("numberOfStudents")%>
    </p></div>
    <div class="container">

        <form id="searchForm" action="/directorControl" method="get">
            <div class="search-box">
                <div class="input-search-background">
                    <input type="hidden" name="status" value="<%=request.getAttribute("inArchive")%>">
                    <div class="btn-search">
                        <input type="text" name="search" class="input-search animate" placeholder="🔍 search..."
                               id="searchInput" value="${not empty param.search ? param.search : ''}">
                    </div>
                </div>
            </div>
        </form>
        <br/>
        <table class="table">
            <thead>
            <tr>
                <th>Inspection Booklet Number</th>
                <th>NAME</th>
                <th>SURNAME</th>
                <th>PHONE</th>
                <th>E-MAIL</th>
                <th>BIRTHDAY</th>
                <th>NUMBER OF PUNISHMENTS</th>
                <th>UNBLOCK</th>
            </tr>
            </thead>
            <tbody>
            <% if (students != null && !students.isEmpty()) { %>
            <% for (Student student : students) { %>
            <tr>
                <div id="requestContainer<%=student.getId()%>" class="request-container">
                    <div id="requestMessage<%=student.getId()%>" class="request-message">
                        You need to unblock this student?
                        <br/>
                        <a href="/unblockStudent?id=<%=student.getId()%>" style="color: red">YES</a> || <a id="cancel<%=student.getId()%>"
                                                                                                           href="#"
                                                                                                           style="color: orange">NO</a>
                    </div>
                </div>
                <td>
                    <%=student.getId()%>
                </td>
                <td><%= student.getName() %>
                </td>
                <td><%= student.getSurname() %>
                </td>
                <td><%=student.getPhoneNum()%>
                </td>
                <td>
                    <%=student.getEmail()%>
                </td>
                <td>
                    <%=student.getBirthday()%>
                </td>
                <td style="padding-left: 2px "><%=student.getPunishment()%>
                </td>
                <td><a href='#' class="gradient-button" id='unblock<%=student.getId()%>'><i class='bx bxs-user-x'></i></a>
                </td>

            </tr>
            <% } %>
            <% } %>
            </tbody>
        </table>
    </div>
    <% if (request.getAttribute("doneMsg") != null) { %>
    <div id="doneContainer" class="done-container">
        <div id="doneMessage" class="done-message">
            <%=request.getAttribute("doneMsg")%>
        </div>
    </div>
    <% } %>
    <% if (request.getAttribute("errMsg") != null) { %>
    <div id="errorContainer" class="error-container">
        <div id="errorMessage" class="error-message">
            <p><%= request.getAttribute("errMsg") %>
            </p>
        </div>
        <% } %>
    </div>
</div>
<div class="wrapper">

    <span class="menu"><i style="font-size:44px; " class='bx bx-menu'></i></span>

    <div class="overlay">
        <a style="position: absolute;top:5px " class="gradient-button" href="/logout"><i class='bx bx-log-out'></i></a>
        <ul>
            <li><a href="/directorControl">Back</a></li>
        </ul>
    </div>
    <div class="blurry-background"></div>
</div>
</body>
<style type="text/css">
    .container {
        max-width: 1200px;
        width: auto;
        max-height: 80%;
        background: linear-gradient(135deg, #0cffe5, #36b7ef);
        padding: 20px 35px;
        border-radius: 0px 0px 20px 20px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.15);
        backdrop-filter: blur(10);
        overflow-y: auto;
        z-index: 100;
    }

    .forming {
        max-width: 1200px;
        width: auto;
        background: transparent;
        backdrop-filter: blur(10);
        z-index: 100;
    }

    .forming .title {
        font-size: 35px;
        font-weight: 600;
        text-align: center;
        line-height: 30px;
        padding-top: 20px;
        color: darkred;
        user-select: none;
        border-radius: 15px 15px 0 0;
        background: linear-gradient(135deg, #36b7ef, #a436ed);
    }


    .container::-webkit-scrollbar {
        width: 10px;
        border-radius: 5px;
    }

    .container::-webkit-scrollbar-track {
        background: linear-gradient(135deg, #a436ed, #36b7ef);
        border-radius: 5px;
    }

    .container::-webkit-scrollbar-thumb {
        background: linear-gradient(1355deg, #00fde8, #428af6);
        background-size: 12px;
        border-radius: 5px;
        transition: background .5s;
    }

    .container::-webkit-scrollbar-thumb:hover {
        background: linear-gradient(135deg, #00fd9c, #428af6);
    }

    .table {
        width: 100%;
        margin-bottom: 50px;
        border-radius: 5px;
        border: 0px solid #ffffff;
        border-top: 0px solid #ffffff;
        border-bottom: 0px solid #fff;
        border-collapse: collapse;
        outline: 5px solid #ffd300;
        font-size: 20px;
        background: #ffffff !important;
    }

    .table th {
        font-weight: bold;
        padding: 3px 16px;
        background: #ffd300;
        border: 6px none;
        text-align: center;
        font-size: 15px;
        color: darkred;
        border-top: 3px solid #ffd300;
        border-bottom: 3px solid #ffd300;
    }

    .table td {
        border: 30px;
        font-size: 16px;
        white-space: nowrap;
        text-align: center;
        padding: 2px 10px;
        color: darkred;
        background: linear-gradient(135deg, #fdd100, #428af6);
    }

    .table tbody tr:nth-child(even) {
        background: #4c698d !important;
    }

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

    .search-box {
        position: relative;
    }


    .input-search {
        color: black;
        width: 63px;
        height: 40px;
        padding: 10px 20px;
        border: none;
        background: linear-gradient(135deg, #fdd100, #428af6);
        border-radius: 40px;
        outline: none;
        font-size: 18px;
        -webkit-background-clip: text;
        background-clip: text;
        transition: all 0.5s ease-in-out;
    }

    .input-search-background {
        width: 63px;
        height: 40px;
        border: none;
        border-radius: 40px;
        outline: none;
        font-size: 18px;
        background: linear-gradient(135deg, #fdd100, #428af6);
        transition: all 0.5s ease-in-out;

    }

    .input-search::placeholder {
        color: rgb(2, 0, 0);
        font-size: 16px;
        font-weight: 100;
    }


    .input-search-background:focus-within {
        border-radius: 10px;
        width: 300px;
    }

    .btn-search:focus + .input-search,
    .input-search:focus {
        width: 300px;
        border-radius: 10px;
        background-color: transparent;
        caret-color: #2c0248;
        border-bottom: 2px solid rgb(59, 75, 255);
        animation: textColorChange 0.5s ease-in-out forwards;
    }

    .input-search:hover {
        caret-color: #85f100;
        width: 300px;
    }

    .input-search-background:hover {
        width: 300px;
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
        transition: all 1s;
    }

    .wrapper .overlay ul li:hover {
        text-shadow: #f519f5 1px 0 10px;
    }

    .wrapper .overlay ul li a {
        text-decoration: none;
        color: #000000;
        position: relative;
        display: inline-block;
        padding: 20px 0;
        overflow: hidden;
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

    .icon {
        width: 45px;
        background: transparent;
        text-shadow: 0 0 20px rgba(0, 0, 0, 0);;
        overflow-x: hidden;
        z-index: 100;
        white-space: nowrap;
        transition: all 2.5s ease;
    }

    .icon i {
        font-size: 50px;
        color: rgba(0, 0, 0, 0.99);
        text-shadow: 0 0 20px rgba(0, 0, 0, 0);
        transition: all 0.5s ease;
    }

    .icon:hover {
        width: 370px;
    }

    .icon i:hover {
        color: #000000;
        text-shadow: #f519f5 1px 0 20px;
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

    .done-container {
    <%if(request.getAttribute("doneMsg")==null){%> display: none;
    <%}else {%> display: flex;
    <%}%> position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        backdrop-filter: blur(5px);
        justify-content: center;
        align-items: center;
        z-index: 200000000000;
    }

    .done-message {
    <%if(request.getAttribute("doneMsg")==null){%> display: none;
    <%}else {%> display: flex;
    <%}%> z-index: 200000000;
        color: white;
        height: auto;
        width: auto;
        background-color: rgb(3, 114, 110);
        padding: 20px;
        border-radius: 7px;

    }


</style>
<script>
    document.getElementById('searchInput').addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            document.getElementById('searchForm').submit();
        }
    });
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
        if (errorContainer && errorContainer.contains(event.target) && !errorMessage.contains(event.target)) {
            errorContainer.style.display = 'none';
            errorMessage.style.display = 'none'
            window.location.replace('/directorControl');
        }
    };

    function handleEnterKeyPress() {
        if (event.key === 'Enter' || event.keyCode === 32) {
            var errorContainer = document.getElementById('errorContainer');
            var errorMessage = document.getElementById('errorMessage');
            errorContainer.style.display = 'none';
            errorMessage.style.display = 'none'
            window.location.replace('/directorControl');
        }
    }

    <% if (request.getAttribute("errMsg") != null) { %>
    document.getElementById('errorMessage').style.display = 'flex';
    document.getElementById('errorContainer').style.display = 'flex';
    <% } %>
    document.body.addEventListener('keypress', handleEnterKeyPress)
    document.body.addEventListener('click', handleButtonClick);

    function handleButtonClickDone() {
        var doneContainer = document.getElementById('doneContainer');
        var doneMessage = document.getElementById('doneMessage');
        if (doneContainer && doneContainer.contains(event.target) && !doneMessage.contains(event.target)) {
            doneContainer.style.display = 'none';
            doneMessage.style.display = 'none'
            window.location.replace('/directorControl');
        }
    };

    function handleEnterKeyPressDone() {
        if (event.key === 'Enter' || event.keyCode === 32) {
            var doneContainer = document.getElementById('doneContainer');
            var doneMessage = document.getElementById('doneMessage');
            doneContainer.style.display = 'none';
            doneMessage.style.display = 'none'
            window.location.replace('/directorControl');
        }
    }

    document.body.addEventListener('keypress', handleEnterKeyPressDone)
    document.body.addEventListener('click', handleButtonClickDone);
    document.addEventListener('DOMContentLoaded', function() {
        <% if (students != null && !students.isEmpty()) { %>
        <% for (Student student : students) { %>
        (function(studentId) {
            var cancelElement = document.getElementById('cancel' + studentId);
            var deactivateElement = document.getElementById('unblock' + studentId)
            cancelElement.addEventListener('click', function (event) {
                event.preventDefault();
                document.getElementById('requestMessage' + studentId).style.visibility = 'hidden';
                document.getElementById('requestContainer' + studentId).style.visibility = 'hidden';
            });
            deactivateElement.addEventListener('click', function (event) {
                event.preventDefault();
                document.getElementById('requestContainer' + studentId).style.visibility = 'visible';
                document.getElementById('requestMessage' + studentId).style.visibility = 'visible';
            });
        })(<%= student.getId() %>);
        <% } %>
        <% } %>
    });
</script>
</html>