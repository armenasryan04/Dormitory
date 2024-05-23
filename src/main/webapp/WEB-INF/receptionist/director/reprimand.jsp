<%@ page import="dormitory.models.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.02.2024
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reprimand</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<% Student student = (Student) request.getAttribute("student"); %>
<body>
<a href="#" style="position: absolute; right: 91%" class="gradient-button" id="backLink">BACK</a>
<div class="wave"></div>
<div class="wave"></div>
<div class="wave"></div>

<div class="forming">
    <div class="title">STUDENTS LIST</div>
    <div class="container">
        <form id="searchForm" action="/reprimandStudent" method="get">
            <div class="search-box">
                <div class="input-search-background">
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
                <th>NAME</th>
                <th>SURNAME</th>
                <th>INSPECTION BOOKLET NUMBER</th>
                <th>NUMBER OF REMARKS</th>
                <th><%if (student != null && student.getRemark() < 2) {%>
                    ADD REMARK
                    <%} else if (student != null && student.getRemark() == 2) {%>
                    BLOCK
                    <%}%></th>
            </tr>
            </thead>
            <tbody>
            <% if (student != null && student.getId() != 0) { %>
            <tr>
                <div id="requestContainer" class="request-container">
                    <div id="requestMessage" class="request-message">
                        <%if (student.getRemark() < 2) {%>
                        you need to add remark?
                        <%} else if (student.getRemark() == 2) {%>
                        you need to block this student?
                        <%}%>
                        <br/>
                        <a href="/updateRemark?id=<%=student.getId()%>" style="color: red">YES</a> || <a id="cancel"
                                                                                                         href="#"
                                                                                                         style="color: orange">NO</a>
                    </div>
                </div>
                <td><%= student.getName() %>
                </td>
                <td><%= student.getSurname() %>
                </td>
                <td><%=student.getId()%>
                </td>
                <td><%= student.getRemark()%>
                </td>
                <td><%if (student.getRemark() < 2) {%>
                    <a href='#' class="gradient-button" id='remark'>+1</a>
                    <%} else if (student.getRemark() == 2) {%>
                    <a href='#' class="gradient-button" id='remark'><i class='bx bx-block'></i></a>
                    <%}%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<% if (request.getAttribute("doneMsg") != null) { %>
<div id="doneContainer" class="done-container">
    <div id="doneMessage" class="done-message">
        <%=request.getAttribute("doneMsg")%>
    </div>
</div>
<% } %>
</body>
<style>
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
        height: 80px;
        font-size: 35px;
        font-weight: 600;
        text-align: center;
        line-height: 30px;
        padding-top: 30px;
        color: #ffffff;
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
        border-top: 3px solid #ffd300;
        border-bottom: 3px solid #ffd300;
    }

    .table td {
        border: 30px;
        font-size: 16px;
        white-space: nowrap;
        text-align: center;
        padding: 2px 10px;
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

    .input-search-background:hover {
        width: 300px;
    }
</style>
<script>
    document.getElementById('searchInput').addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            document.getElementById('searchForm').submit();
        }
    });
    var backLink = document.getElementById("backLink");
    backLink.addEventListener("click", function (event) {
        event.preventDefault();
        window.location.replace('/directorControl');
    });
    var cancelElement = document.getElementById('cancel');
    var remarkElement = document.getElementById('remark')

    function handleButtonClick() {
        var doneContainer = document.getElementById('doneContainer');
        var doneMessage = document.getElementById('doneMessage');
        if (doneContainer && doneContainer.contains(event.target) && !doneMessage.contains(event.target)) {
            doneContainer.style.display = 'none';
            doneMessage.style.display = 'none'
            window.location.replace('/reprimandStudent');
        }
    };

    function handleEnterKeyPress() {
        if (event.key === 'Enter' || event.keyCode === 32) {
            var doneContainer = document.getElementById('doneContainer');
            var doneMessage = document.getElementById('doneMessage');
            doneContainer.style.display = 'none';
            doneMessage.style.display = 'none'
            window.location.replace('/reprimandStudent');
        }
    }

    document.body.addEventListener('keypress', handleEnterKeyPress)
    document.body.addEventListener('click', handleButtonClick);

    cancelElement.addEventListener('click', function () {
        document.getElementById('requestMessage').style.visibility = 'hidden';
        document.getElementById('requestContainer').style.visibility = 'hidden';
    })
    remarkElement.addEventListener('click', function () {
        document.getElementById('requestContainer').style.visibility = 'visible';
        document.getElementById('requestMessage').style.visibility = 'visible';
    });
</script>
</html>